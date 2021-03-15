package com.tech.eclouds.xzj.common;


/**
 * @author SunBo
 * @version v1.0
 * @since 2019/10/28
 */
public class MqContants {


    /**
     * 所有队列的前缀
     */
    public static final String PREFIX = "intimate.";

    /**
     * 所有搜索中台交换机
     */
    public static final String INTIMATE_EXCHANGE = PREFIX + "data.exchange";

    /**
     * 搜索中台订阅交换机
     */
    public static final String INTIMATE_FANOUT_EXCHANGE = PREFIX + "bus.exchange";

    /**
     * HBase处理队列
     */
    public static final String INTIMATE_HBASE_HANDLE_QUEUE = PREFIX + "hbase.handle.queue";

    /**
     * HBASE死信处理交换机
     */
    public static final String INTIMATE_HBASE_DLX_HANDLE_EXCHANGE = PREFIX + "hbase.dlx.handle.exchange";
    /**
     * HBASE死信处理队列
     */
    public static final String INTIMATE_HBASE_DLX_HANDLE_QUEUE = PREFIX + "hbase.dlx.handle.queue";


    /**
     * ES处理队列
     */
    public static final String INTIMATE_ES_HANDLE_QUEUE = PREFIX + "es.handle.queue";

    /**
     * MONGO处理队列
     */
    public static final String INTIMATE_MONGO_HANDLE_QUEUE = PREFIX + "mongo.handle.queue";

    /**
     * 以图搜图处理队列
     */
    public static final String INTIMATE_IMAGE_SEARCH_HANDLE_QUEUE = PREFIX + "image.search.handle.queue";


    /**
     * 编织任务操作队列
     */
//    public static final String INTIMATE_BUS_DYNAMIC_QUEUE = PREFIX +"bus.dynamic.queue" + "_" + LocalHostUtil.getLocalHostLANAddress()+"@"+LocalHostUtil.getProcessID();

}
