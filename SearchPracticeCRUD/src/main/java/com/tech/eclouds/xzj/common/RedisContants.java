package com.tech.eclouds.xzj.common;

/**
 * []
 *
 * @author zhangdi
 * @date 2019-09-09 15:51
 */
public class RedisContants {

    /**
     * 所有key的前缀
     */
    public static final String PREFIX = "intimate:";

    /**
     * 下拉提示key
     */
    public static final String DROP_DOWN_PROMPT = PREFIX + "dropDownPrompt:";

    /**
     * 分布式锁-key
     */
    public static final String DISTRIBUTED_LOCK = PREFIX + "lock:tophotword";

    /**
     * 分业务线的top5热词
     */
    public static final String BUSSINESS_TOP_WORD = PREFIX + "businesstype:top:";

    /**
     * 商祥页推荐商品
     */
    public static final String PRODUCT_DETSAL_RECOMMEND = PREFIX + "productDetail:recommend";

    /**
     * 分布式锁-key失效时间(毫秒）
     */
    public static final long DISTRIBUTED_LOCK_EXPIRE = 1000 * 60 * 5;

    /**
     * appkey集合
     */
    public static final String APPKEY_LIST = PREFIX + "appkey:list";

    /**
     * secretKey 对应租户信息
     */
    public static final String SECRET_KEY_MEMBER_INFO = PREFIX + "secretKey:";

    /**
     * 索引信息集合
     */
    public static final String INDEX_MODLE = PREFIX + "indexModel:";

    /**
     * 分布式任务集合
     */
    public static final String TASK_JOB = PREFIX + "taskJob:";

    /**
     * 分词器信息集合
     */
    public static final String ANALYZER_MODLE = PREFIX + "analyzerModle:";

    /**
     * 商品推荐标示 2 - 初始化中, 1 - 已经初始化 ， 0、不存在 - 未初始化
     */
    public static final String PRODUCT_RECOMMEND_INIT_FLAG = PREFIX + "recommend:initFlag";

    /**
     * 商品推荐订单标示 2 - 初始化中, 1 - 已经初始化 ， 0、不存在 - 未初始化
     */
    public static final String PRODUCT_RECOMMEND_INIT_ORDER_FLAG = PREFIX + "recommend:initOrderFlag";

    /**
     * 商品推荐 保存商品间同时购买关系
     */
    public static final String PRODUCT_RECOMMEND_MAPPING = PREFIX + "recommend:";

    /**
     * 验证码
     */
    public static final String VERIFICATION = PREFIX + "verification:";

    /**
     * es异步消息暂存
     */
    public static final String ES_HANDLE_MESSAGE = PREFIX + "esHandleMessage:";

    /**
     * 登录用户前缀
     */
    public static final String LOGIN_USER = PREFIX + "loginUser_cache:";

    /**
     * 已上传完成条数计数
     */
    public static final String UPLOAD_DATA_COUNT = PREFIX + "uploadData:doneCount:";

    /**
     * es 的 stats信息（条数、存储空间大小）
     */
    public static final String ES_STATS_INFO = PREFIX + "esInfo:stats:";

    /**
     * 定时更新索引
     */
    public static final String REBUILD_INDEX_IN_TIME = PREFIX + "rebuildIndex:";

    /**
     * 分布式锁-key -定时更新索引
     */
    public static final String DISTRIBUTED_LOCK_REBUILD_INDEX = PREFIX + "lock:rebuildIndex";
    /**
     * 分布式锁-key - 定时统计运维数据（条数、存储大小等）
     */
    public static final String DISTRIBUTED_LOCK_MONITOR = PREFIX + "lock:monitor";
    /**
     * 分布式锁-key - 定时统计运维数据（条数、存储大小等）
     */
    public static final String DISTRIBUTED_LOCK_SEARCHWORD = PREFIX + "lock:searchword";
    /**
     * 分布式锁-key - 数据补偿
     */
    public static final String DISTRIBUTED_LOCK_TASK = PREFIX + "lock:task";
    /**
     * 系统监控信息
     */
    public static final String MONITOR = PREFIX + "monitor";

    /**
     * 系统监控初始化时间
     */
    public static final String MONITOR_INIT_DATE_TIME = MONITOR + ":datetime";

    /**
     * hbase总条数
     */
    public static final String MONITOR_HBASE_TOTAL_COUNT = MONITOR + ":count:total";

    /**
     * es总条数
     */
    public static final String MONITOR_ES_TOTAL_COUNT = MONITOR + ":count:total";

    /**
     * es总大小
     */
    public static final String MONITOR_ES_TOTAL_SIZE = MONITOR + ":size:total";

    /**
     * 总搜索次数
     */
    public static final String MONITOR_SEARCH_TOTAL_COUNT = MONITOR + ":search:total";

    /**
     * 总搜索热词
     */
    public static final String MONITOR_WORD_TOTAL_COUNT = MONITOR + ":word:total";

    /**
     * 总用户数
     */
    public static final String MONITOR_USER_TOTAL = MONITOR + ":user:total";

    /**
     * hbase条数
     */
    public static final String MONITOR_HBASE_COUNT = MONITOR + ":count:hbase";

    /**
     * es条数
     */
    public static final String MONITOR_ES_COUNT = MONITOR + ":count:es";

    /**
     * es大小
     */
    public static final String MONITOR_ES_SIZE = MONITOR + ":size:es";

    /**
     * 搜索次数
     */
    public static final String MONITOR_SEARCH_COUNT = MONITOR + ":search:es";

    /**
     * 搜索热词
     */
    public static final String MONITOR_WORD = MONITOR + ":word:index";

    /**
     * 用户数
     */
    public static final String MONITOR_USER = MONITOR + ":user";

    /**
     * 防止重放
     */
    public static final String REPLAY_KEY = PREFIX + "replay_nonce";

    /**
     * 字典数据
     */
    public static final String DIC_KEY = PREFIX + "dic:";

    /**
     * 二次排序
     */
    public static final String REORDER_KEY = PREFIX + "reorder:";

    /**
     * 搜索热词衰减模型 key
     */
    public static final String SEARCH_WORD_KEY = PREFIX + "searchword:";

    /**
     * 搜索热词结果排序 key
     */
    public static final String SEARCH_WORD_RANK_KEY = PREFIX + "searchwordrank:";

    /**
     * 搜索热词初始化标记
     */
    public static final String SEARCH_WORD_INIT_FLAG_KEY = PREFIX + "searchwordinit:flag";




}
