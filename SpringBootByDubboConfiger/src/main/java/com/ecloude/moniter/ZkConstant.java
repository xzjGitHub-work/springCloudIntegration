package com.ecloude.moniter;

import java.util.HashMap;
import java.util.Map;

public  class ZkConstant {

    // zookeeper 服务地址
    public static final String SERVER = "127.0.0.1:2181";

    // zookeeper 连接超时时间
    public static int SESSION_TIMEOUT = 3000;

    // 节点路径
    public static String PATH_NODES = "/nodes";

    // 存储存活节点数据
    public static Map<String,String> availableNodes = new HashMap<>();

}
