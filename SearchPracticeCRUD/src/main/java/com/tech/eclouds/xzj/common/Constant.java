package com.tech.eclouds.xzj.common;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/16
 */
public class Constant {

    /**
     * 搜索中台APPKEY
     */
    public static String INTIMATE_APPKEY = "intimate";

    /**
     * 主分片数，默认为5.只能在创建索引时设置，不能修改
     */
    public static int INDEX_NUMBER_OF_SHARDS = 5;
    /**
     * 每个主分片的副本数。默认为 1。
     */
    public static int INDEX_NUMBER_OF_REPLICAS = 1;
    /**
     * 执行刷新操作的频率，这使得索引的最近更改可以被搜索。默认为 1s。可以设置为 -1 以禁用刷新。
     */
    public static String INDEX_REFRESH_INTERVAL = "5s";
    /**
     * 用于索引搜索的 from+size 的最大值。默认为 10000
     */
    public static int INDEX_MAX_RESULT_WINDOW = 200000000;
    /**
     * 在搜索此索引中 rescore 的 window_size 的最大值
     */
    public static int INDEX_MAX_RESCORE_WINDOW = 20000000;
    /**
     * 允许执行的最大正则表达式长度
     */
    public static int INDEX_MAX_REGEX_LENGTH = 100000;
    /**
     * translog多久被同步到磁盘并提交一次。默认5秒。
     */
    public static String INDEX_TRANSLOG_SYNC_INTERVAL = "5s";
    /**
     * 请求之后立即同步并提交translog。
     */
    public static String INDEX_TRANSLOG_DURABILITY = "async";
    /**
     * 索引默认type。
     */
    public static String DEFAULT_TYPE = "_doc";
    /**
     * 非分页，默认的查询条数
     */
    public static int DEFALT_PAGE_SIZE = 100;
    /**
     * 搜索建议默认条数
     */
    public static int COMPLETION_SUGGESTION_SIZE = 5;
    /**
     * SCROLL查询 30分钟
     */
    public static long DEFAULT_SCROLL_TIME = 30 * 60 * 1000;
    /**
     * 容错个数
     */
    public static int COMPLETION_SUGGESTION_FUZZINESS = 2;
    /**
     * 指定什么长度的输入文本可以开启模糊查询
     */
    public static int COMPLETION_SUGGESTION_FUZZY_MIN_LENGTH = 10;
    /**
     * 开始的字符是正确的数量
     */
    public static int COMPLETION_SUGGESTION_FUZZY_PREFIX_LENGTH = 3;
    /**
     * 自动补全衍生字段名
     */
    public static String FIELD_COMPLETION = "completion";
    /**
     * keyword衍生字段名
     */
    public static String FIELD_KEYWORD = "keyword";

    /**
     * 总线处理标识
     */
    public static String BUS_MESSAGE_HANDLE = "handle";

    /**
     * 总线处理标识-租户信息刷新
     */
    public final static String BUS_MESSAGE_HANDLE_REFRESH_APPKEY = "refresh_appkey";

    /**
     * 保留字段
     */
    public static String ID = "_id";
    public static String OID = "oid";

    /**
     * 原始数据表后缀
     */

//    public static final String ORIGIN_INDEX = "_origin";

    /**
     * 0 禁用 1 启用
     */
    public static String INS_INDEX_STATUS = "ins_index_status";

    public static String INS_CREATE_TIME = "ins_create_time";

    public static String INS_MODIFY_TIME = "ins_modify_time";

    /**
     * 老搜索
     */
    public static final String OLD_INTIMATE_AUTHORIZATION = "www.infcn.com.cn";
    /**
     * 老搜索专用appKey
     */
    public static final String OLD_INTIMATE_APPKEY = "oldIntimate";

    public static String ES_MESSAGE_ID = "esMessageId";

    /**
     * 用户密码默认 盐
     */
    public static final String USER_SALT = "3k45jl2k";

    /**
     * token失效时间
     */
    public static final long TOKEN_EXPIRE_TIME = 60 * 30;

    /**
     * 单次最大查询条数
     */
    public static final int MAX_SIZE = 10000;

    /**
     * 分词器测试用的索引
     */
    public static final String ANALYZER_TEST_INDEX = "analyzer_test_index";


    /**
     * 数据操作行为
     */
    public interface Action{
        String SAVE = "save";
        String BATCH_SAVE = "batchSave";
        String INSERT = "insert";
        String BATCH_INSERT = "batchInsert";
        String UPDATE = "update";
        String BATCH_UPDATE = "batchUpdate";
        String DELETE = "delete";
        String BATCH_DELETE = "batchDelete";
        String UPDATE_BY_QUERY = "updateByQuery";
        String DELETE_BY_QUERY = "deleteByQuery";
    }
}
