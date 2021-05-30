package com.ecloude.moniter;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ZkMonitorClient {

    private static Logger LOGGER = LoggerFactory.getLogger(ZkMonitorClient.class);

    public static ZooKeeper zooKeeper;

//    static  {
//        //注册监听
//        try {
//            zooKeeper.getChildren(ZkConstant.PATH_NODES, new ZkNodesWatcher(zooKeeper));
//        } catch (Exception e) {
//            LOGGER.error("获取节点数据异常,节点路径:" + ZkConstant.PATH_NODES, e);
//        }
//    }

    @Bean
    public ZooKeeper getZooKeeper(){
        // 创建zookeeper 连接时, 设置全局监听器
        try {
            zooKeeper = new ZooKeeper(ZkConstant.SERVER, ZkConstant.SESSION_TIMEOUT, new MonitorGlobalWatcher());
        } catch (Exception e) {
            LOGGER.error("连接zookeeper 异常", e);
        }
        return  zooKeeper;
    }
}
