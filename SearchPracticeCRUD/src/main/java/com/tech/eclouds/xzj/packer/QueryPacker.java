package com.tech.eclouds.xzj.packer;


import com.tech.eclouds.xzj.common.Constant;
import com.tech.eclouds.xzj.criteria.Criteria;
import com.tech.eclouds.xzj.criteria.SerializedLambda;
import com.tech.eclouds.xzj.util.SFunction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/19
 */
public class QueryPacker extends BasePacker {

    public enum Sort {
        ASC,
        DESC;
    }

    public enum AggsType {
        COUNT,
        SUM,
        MIN,
        MAX,
        AVG;
    }

    /**
     * 起始偏移量
     */
    private int from = 0;

    /**
     * 查询条数
     */
    private int size = 100;

    /**
     * 聚合条数
     */
    private int aggSize = 10;

    /**
     * 查询语句
     */
    private String queryString;

    /**
     * 过滤语句
     */
    private String filterString;


    /**
     * 包含字段
     */
    private Set<String> includes = new LinkedHashSet<>();
    /**
     * 不包含字段
     */
    private Set<String> excludes = new LinkedHashSet<>();
    /**
     * 聚合
     */
    private Map<String, Object> aggregations = new IdentityHashMap<>();
    /**
     * 分组
     */
    private List<String> groups = new ArrayList<>();
    /**
     * 排序
     */
    private Map<String, String> orders = new LinkedHashMap<>();
    /**
     * 条件构造器
     */
    private Criteria criteria;
    /**
     * 实体类
     */
    private Class queryClazz;

    private QueryPacker() {

    }

    /**
     * @param indexName 表名
     * @Decription 无检索列
     */
    private QueryPacker(String indexName) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(indexName);
        this.criteria = Criteria.ALL();
    }

    /**
     * @param clazz 实体类
     * @Decription 无检索列
     */
    private QueryPacker(Class clazz) {
        setUuid(UUID.randomUUID().toString());
        queryClazz = clazz;
        setIndexName(clazz.getSimpleName());
        this.criteria = Criteria.ALL();
    }


    /**
     * 创建构造器
     *
     * @param clazz 类
     * @Decription 无检索列
     */
    public static QueryPacker create(Class clazz) {
        return new QueryPacker(clazz);
    }

    /**
     * 创建构造器
     *
     * @param indexName 表名
     * @Decription 无检索列
     */
    public static QueryPacker create(String indexName) {
        return new QueryPacker(indexName.toLowerCase());
    }

    /**
     * 指定包含字段
     *
     * @param columns 列名
     * @Decription 追加或覆盖列名
     */
    public QueryPacker includes(String... columns) {
        this.includes = Arrays.asList(columns).stream().distinct().collect(Collectors.toSet());
        return this;
    }

    /**
     * 指定包含字段
     *
     * @param columns 通过lambda获取列名
     * @Decription 追加或覆盖列名
     */
    public <T> QueryPacker includes(SFunction<T, ?>... columns) {
        for (SFunction<T, ?> column : columns) {
            this.includes.add(SerializedLambda.getColumnName(column));
        }
        return this;
    }

    /**
     * 指定不包含字段
     *
     * @param columns 列名集合
     * @Decription 追加或覆盖列名
     */
    public QueryPacker includes(Set<String> columns) {
        this.includes.addAll(columns.stream().distinct().collect(Collectors.toSet()));
        return this;
    }

    /**
     * 指定不包含字段
     *
     * @param columns 列名
     * @Decription 追加或覆盖列名
     */
    public QueryPacker excludes(String... columns) {
        this.excludes = Arrays.asList(columns).stream().distinct().collect(Collectors.toSet());
        return this;
    }

    /**
     * 指定不包含字段
     *
     * @param columns 列名集合
     * @Decription 追加或覆盖列名
     */
    public QueryPacker excludes(List<String> columns) {
        this.excludes.addAll(columns.stream().distinct().collect(Collectors.toSet()));
        return this;
    }

    /**
     * 指定不包含字段
     *
     * @param columns 通过lambda获取列名
     * @Decription 追加或覆盖列名
     */
    public <T> QueryPacker excludes(SFunction<T, ?>... columns) {
        for (SFunction<T, ?> column : columns) {
            this.excludes.add(SerializedLambda.getColumnName(column));
        }
        return this;
    }

    /**
     * 指定不包含字段
     *
     * @param columns 列名集合
     * @Decription 追加或覆盖列名
     */
    public QueryPacker excludes(Set<String> columns) {
        this.excludes.addAll(columns.stream().distinct().collect(Collectors.toSet()));
        return this;
    }

    /**
     * 清空包含字段
     */
    public QueryPacker clearIncludes() {
        includes.clear();
        return this;
    }

    /**
     * 清空不包含字段
     */
    public QueryPacker clearExcludes() {
        excludes.clear();
        return this;
    }

    /**
     * 设置聚合属性
     *
     * @param column   列名
     * @param aggsType 聚合类型
     * @Decription 追加聚合属性
     */
    public QueryPacker aggs(String column, AggsType aggsType) {
        this.aggregations.put(column, aggsType);
        return this;
    }

    /**
     * 设置聚合属性
     *
     * @param column   通过lambda获取列名
     * @param aggsType 聚合类型
     * @Decription 追加聚合属性
     */
    public <T> QueryPacker aggs(SFunction<T, ?> column, AggsType aggsType) {
        this.aggregations.put(SerializedLambda.getColumnName(column), aggsType);
        return this;
    }

    /**
     * 清空聚合属性
     */
    public QueryPacker clearAggs() {
        this.aggregations.clear();
        return this;
    }

    /**
     * 设置分组属性
     *
     * @param columns 列名
     * @Decription 追加或覆盖分组属性
     */
    public QueryPacker groupBy(String... columns) {
        for (String column : columns) {
            this.groups.add(column);
        }
        return this;
    }

    /**
     * 设置分组属性
     *
     * @param columns 通过lambda获取列名
     * @Decription 追加或覆盖分组属性
     */
    public <T> QueryPacker groupBy(SFunction<T, ?>... columns) {
        for (SFunction<T, ?> column : columns) {
            this.groups.add(SerializedLambda.getColumnName(column));
        }
        return this;
    }

    /**
     * 清空分组属性
     */
    public QueryPacker clearGroupBy() {
        this.groups.clear();
        return this;
    }

    /**
     * 设置排序属性
     *
     * @param column 列名
     * @param sort   ASC正序，DESC倒序
     * @Decription 追加或覆盖排序属性
     */
    public QueryPacker order(String column, Sort sort) {
        this.orders.put(column, sort.name());
        return this;
    }

    /**
     * 设置排序属性
     *
     * @param column 通过lambda获取列名
     * @param sort   ASC正序，DESC倒序
     * @Decription 追加或覆盖排序属性
     */
    public <T> QueryPacker order(SFunction<T, ?> column, Sort sort) {
        this.orders.put(SerializedLambda.getColumnName(column), sort.name());
        return this;
    }

    /**
     * 清空排序属性
     */
    public QueryPacker clearOrder() {
        this.orders.clear();
        return this;
    }


    /**
     * 设置条件
     *
     * @param criteria 条件构造器
     */
    public QueryPacker where(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    /**
     * 设置偏移量
     *
     * @param from 起始偏移量
     */
    public QueryPacker from(int from) {
        this.from = from;
        return this;
    }

    /**
     * 设置查询条数
     *
     * @param size 查询条数
     */
    public QueryPacker size(int size) {
        if (size < 0) {
            size = 0;
        }
        if (size > Constant.MAX_SIZE) {
            size = Constant.MAX_SIZE;
        }
        this.size = size;
        return this;
    }

    /**
     * queryString
     *
     * @param queryString 查询语句
     * @return this
     */
    public QueryPacker QueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    /**
     * filterQuery
     *
     * @param filterString 过滤语句
     * @return this
     */
    public QueryPacker FilterString(String filterString) {
        this.filterString = filterString;
        return this;
    }

    public int getFrom() {
        return from;
    }

    public int getSize() {
        return size;
    }

    public Set<String> getIncludes() {
        return includes;
    }

    public Set<String> getExcludes() {
        return excludes;
    }

    public Map<String, Object> getAggregations() {
        return aggregations;
    }

    public List<String> getGroups() {
        return groups;
    }

    public Map<String, String> getOrders() {
        return orders;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getFilterString() {
        return filterString;
    }

    public Class getQueryClazz() {
        return queryClazz;
    }

    public int getAggSize() {
        return aggSize;
    }

    public void setAggSize(int aggSize) {
        this.aggSize = aggSize;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryPacker{");
        sb.append("from=").append(from);
        sb.append(", size=").append(size);
        sb.append(", queryString='").append(queryString).append('\'');
        sb.append(", filterString='").append(filterString).append('\'');
        sb.append(", includes=").append(includes);
        sb.append(", excludes=").append(excludes);
        sb.append(", aggregations=").append(aggregations);
        sb.append(", groups=").append(groups);
        sb.append(", orders=").append(orders);
        sb.append(", criteria=").append(criteria);
        sb.append(", queryClazz=").append(queryClazz);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String buildMSql() {
        StringBuilder stringBuilder = new StringBuilder("select ");

        if (includes.size() != 0) {
            stringBuilder.append(String.join(",", new ArrayList<>(includes)));
        }

        if (aggregations.size() != 0) {
            if (includes.size() != 0) {
                stringBuilder.append(",");
            }
            Set<String> aggSet = new LinkedHashSet<>();
            for (String key : aggregations.keySet()) {
                aggSet.add(new StringBuffer(aggregations.get(key).toString()).append("(").append(key).append(")").toString());
            }
            stringBuilder.append(String.join(",", aggSet));
        }

        if (includes.size() == 0 && aggregations.size() == 0) {
            stringBuilder.append("*");
        }

        stringBuilder.append(" ").append("from").append(" ").append(getIndexName());

        if (criteria != null) {
            stringBuilder.append(" ").append("where").append(" ").append(criteria.buildCondition(Criteria.SqlType.SQL));
        }
        if (groups.size() != 0) {
            stringBuilder.append(" ").append("group by").append(" ").append(String.join(",", groups));
        }

        if (orders.size() != 0) {
            stringBuilder.append(" order by ");
            Set<String> orderSet = new LinkedHashSet<>();
            for (Map.Entry<String, String> entry : orders.entrySet()) {
                orderSet.add(new StringBuffer(entry.getKey()).append(" ").append(entry.getValue()).toString());
            }
            stringBuilder.append(String.join(",", orderSet));
        }

        if (size >= 0) {
            stringBuilder.append(" limit ").append(from).append(",").append(size);
        }
        return stringBuilder.toString();
    }

    @Override
    public String buildHSql() {
        StringBuilder stringBuffer = new StringBuilder("SELECT ");

        StringBuilder colnums = new StringBuilder();
        //phoenix 严格group，当有group分组时，只能查询分组的字段
        if (groups.size() > 0) {
            colnums.append(String.join(",", new ArrayList<>(groups)));
        } else {
            if (includes.size() != 0) {
                colnums.append(String.join(",", new ArrayList<>(includes)));
            }
        }
        if (aggregations.size() > 0) {
            if (colnums.length() > 0) {
                colnums.append(",");
            }
            Set<String> aggSet = new LinkedHashSet<>();
            for (String key : aggregations.keySet()) {
                aggSet.add(new StringBuffer(aggregations.get(key).toString()).append("(").append(key).append(")").toString());
            }
            colnums.append(String.join(",", aggSet));
        }
        if (colnums.length() == 0) {
            stringBuffer.append("*");
        } else {
            stringBuffer.append(colnums);
        }

        stringBuffer.append(" ").append("FROM").append(" ").append(Constant.INTIMATE_APPKEY.toUpperCase()).append(".").append(getIndexName().toUpperCase());

        if (criteria != null) {
            stringBuffer.append(" ").append("WHERE").append(" ").append(criteria.buildCondition(Criteria.SqlType.HQL));
        }
        if (groups.size() != 0) {
            stringBuffer.append(" ").append("GROUP BY").append(" ").append(String.join(",", groups));
        }

        if (orders.size() != 0) {
            stringBuffer.append(" ORDER BY ");
            Set<String> orderSet = new LinkedHashSet<>();
            for (Map.Entry<String, String> entry : orders.entrySet()) {
                orderSet.add(new StringBuffer(entry.getKey()).append(" ").append(entry.getValue()).toString());
            }
            stringBuffer.append(String.join(",", orderSet));
        }

        if (size >= 0) {
            stringBuffer.append(" LIMIT ").append(size).append(" OFFSET ").append(from);
        }
        return stringBuffer.toString();
    }

}
