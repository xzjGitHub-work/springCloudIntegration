package com.ecloude.moniter;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Component;

import java.util.List;

public class ZkNodesWatcher implements Watcher {

    private static Logger LOGGER = LoggerFactory.getLogger(ZkMonitorClient.class);

    private ZooKeeper zooKeeper;

    public ZkNodesWatcher(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;

        // 初始化数据
        reInitData();
    }

    /**
     * @Description: 重新初始化数据
     * @author: zongf
     * @time:  18:08:49
     */
    public void reInitData() {
        try {
            // 获取所有子节点. 并再次注册监听, 监听器为自身.
            List<String> children = zooKeeper.getChildren(ZkConstant.PATH_NODES, this);

            // 提取所有子节点数据
            for (String child : children) {
                String childPath = ZkConstant.PATH_NODES + "/" + child;
                byte[] data = zooKeeper.getData(childPath, false, null);

                ZkConstant.availableNodes.clear();
                ZkConstant.availableNodes.put(childPath, new String(data));
            }
            // 打印当前存活节点信息
            printNodes();
        } catch (Exception e) {
            LOGGER.error("重新初始化队列异常", e);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        // 监听子节点发生变化事件(新增子节点和删除子节点时触发)
        if (Event.EventType.NodeChildrenChanged == event.getType()) {
            reInitData();
        }
    }

    /**
     * @Description: 输出可用节点列表
     * @author: zongf
     * @time:  18:08:27
     */
    private static void printNodes() {
        StringBuffer sb = new StringBuffer();
        sb.append("\n********** 当前可用节点列表: **********\n");
        ZkConstant.availableNodes.forEach((key,value) ->sb.append(key + " --> " + value));
        sb.append("\n");
        LOGGER.info(sb.toString());
    }
}

