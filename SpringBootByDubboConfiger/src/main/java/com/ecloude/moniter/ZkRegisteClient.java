package com.ecloude.moniter;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZkRegisteClient {

    private static Logger logger = LoggerFactory.getLogger(ZkRegisteClient.class);

    private static ZooKeeper zooKeeper;

    // 使用static 方式, 在类加载时, 只执行一次
    static {
        // 创建zookeeper 连接时, 设置全局监听器
        try {
            zooKeeper = new ZooKeeper(ZkConstant.SERVER, ZkConstant.SESSION_TIMEOUT, new RegisterGlobalWatcher());
        } catch (IOException e) {
            throw new RuntimeException("创建连接失败");
        }

        //注册节点
        rigeste();
    }

    /**
     * @Description: 注册节点
     * @author: zongf
     * @time:  17:30:07
     */
    private static void rigeste(){

        try {
            // 如果nodes 节点不存在, 则创建nodes 节点
            if(null == zooKeeper.exists(ZkConstant.PATH_NODES, false)){
                zooKeeper.create(ZkConstant.PATH_NODES, "集群节点列表".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            // 获取本机主机ip
            String address = InetAddressUtil.getHostIp();

            // 节点路径 TODO 根据自居需要设置在数据内容
            String nodePath = ZkConstant.PATH_NODES + "/" + address + "-";

            // 节点数据 TODO 根据自居需要设置在数据内容
            String data = "{\"host\":\"" + address + "\", time:\"" + getDateTime() + "\"}";

            // 如果节点存在则先删除
            Stat stat = zooKeeper.exists(nodePath, false);
            if(stat != null){
                zooKeeper.delete(nodePath, stat.getVersion());
            }

            // 创建节点(由于笔者是一台机器,所以ip相同,因此采用了临时有序节点)
            String nodeName = zooKeeper.create(nodePath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            //logger.info("zookeeper 创建节点成功, 节点路径:{}, 节点名称:{}", nodePath, nodeName);
        } catch (Exception e) {
            logger.error("Zookeeper 创建nodes节点异常", e);
        }
    }

    /**
     * @Description: 获取当前时间字符串.
     * @return: String
     * @author: zongf
     * @time:  17:37:04
     */
    private static String getDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        return dtf.format(now);
    }

}
