package com.tech.eclouds.xzj.packer;

import com.tech.eclouds.xzj.common.Constant;
import com.tech.eclouds.xzj.criteria.Criteria;
import com.tech.eclouds.xzj.criteria.SerializedLambda;
import com.tech.eclouds.xzj.util.MapTransformUtils;
import com.tech.eclouds.xzj.util.SFunction;
import com.tech.eclouds.xzj.util.StringTransformUtil;

import java.util.*;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/23
 */
public class UpdatePacker extends BasePacker {


    /**
     * 参数
     */
    private Map<String, Object> params = new LinkedHashMap<>();

    /**
     * 条件构造器
     */
    private Criteria criteria;

    /**
     * 查询语句
     */
    private String queryString;

    /**
     * @param indexName 表名
     * @Decription 无数据
     */
    private UpdatePacker(String indexName) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(indexName);
    }

    /**
     * 私有匿名构造器
     */
    private UpdatePacker() {
    }

    /**
     * @param indexName 表名
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 通过业务标识-下划线-类名生成表名
     * @Decription 类名驼峰转下划线
     * @Decription 反射获取属性名和属性值
     */
    private UpdatePacker(String indexName, Object object, boolean available) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(indexName);
        if (object instanceof Map) {
            Map<String, Object> newMap = new HashMap<>();
            Iterator<Map.Entry<String, Object>> it = ((Map) object).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String newKey = entry.getKey();
                if (!available || entry.getValue() != null) {
                    newMap.put(newKey, entry.getValue());
                }
            }
            set(newMap);
        } else {
            set(MapTransformUtils.objectToMap(object, available, true));
        }
    }

    /**
     * 创建构造器
     *
     * @param indexName 表名
     * @Decription 无数据
     */
    public static UpdatePacker create(String indexName) {
        return new UpdatePacker(indexName);
    }
    public static UpdatePacker create(String indexName, Map<String, Object> data) {
        return new UpdatePacker(indexName, data, false);
    }

    /**
     * 创建构造器
     *
     * @param indexName 表名
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 反射获取属性名和属性值
     */
    public static UpdatePacker create(String indexName, Object object, boolean available) {
        return new UpdatePacker(indexName, object, available);
    }

    /**
     * 创建构造器
     *
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 反射获取属性名和属性值
     */
    public static UpdatePacker create(Object object, boolean available) {
        return new UpdatePacker(object.getClass().getSimpleName(), object, available);
    }


    /**
     * 设置属性及属性值
     *
     * @param column 属性名
     * @param value  属性值
     * @Decription 追加或覆盖属性及属性值
     */
    public UpdatePacker set(String column, Object value) {
        params.put(column, value);
        return this;
    }


    /**
     * 设置属性及属性值
     *
     * @param column 通过lambda获取属性名
     * @param value  属性值
     * @Decription 追加或覆盖属性及属性值
     * @Decription 属性名驼峰转下划线
     */
    public <T> UpdatePacker set(SFunction<T, ?> column, Object value) {
        params.put(SerializedLambda.getColumnName(column), value);
        return this;
    }

    /**
     * 设置属性及属性值
     *
     * @param map 属性及属性值集合
     * @Decription 追加或覆盖属性及属性值
     */
    public UpdatePacker set(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<>();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String newKey = entry.getKey();
            newMap.put(newKey, entry.getValue());
        }
        params.putAll(newMap);
        return this;
    }

    /**
     * 设置属性及属性值
     *
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 追加或覆盖属性及属性值
     */
    public UpdatePacker set(Object object, boolean available) {
        set(MapTransformUtils.objectToMap(object, available, true));
        return this;
    }

    /**
     * 清空属性及属性值
     */
    public void clearParams() {
        params.clear();
    }

    /**
     * 不包括属性及属性值
     *
     * @param columns 属性名
     * @Decription 追加或覆盖属性及属性值
     */
    public UpdatePacker excludes(String... columns) {
        for (String column : columns) {
            params.remove(column);
        }

        return this;
    }


    /**
     * 不包括属性及属性值
     *
     * @param columns 通过lambda获取属性名
     * @Decription 追加或覆盖属性及属性值
     * @Decription 属性名驼峰转下划线
     */
    public <T> UpdatePacker excludes(SFunction<T, ?>... columns) {
        for (SFunction<T, ?> column : columns) {
            params.remove(SerializedLambda.getColumnName(column));
        }
        return this;
    }

    /**
     * 设置条件
     *
     * @param criteria 条件构造器
     */
    public UpdatePacker where(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public String getQueryString() {
        return queryString;
    }

    /**
     * queryString
     *
     * @param queryString 查询语句
     * @return this
     */
    public UpdatePacker queryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UpdatePacker{");
        sb.append("uuid='").append(getUuid()).append('\'');
        sb.append(", indexName='").append(getIndexName()).append('\'');
        sb.append(", params=").append(params);
        sb.append(", criteria=").append(criteria);
        sb.append('}');
        sb.append(", queryString='").append(queryString).append('\'');
        return sb.toString();
    }

    @Override
    public String buildMSql() {
        if (params.size() == 0) {
            return "wrong sql!";
        }
        if (criteria == null) {
            throw new RuntimeException("条件不能为空");
        }
        StringBuffer stringBuffer = new StringBuffer("update ").append(getIndexName()).append(" set ");

        Set<String> paramSet = new LinkedHashSet<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            paramSet.add(new StringBuffer(entry.getKey()).append("=").append(StringTransformUtil.getValueToSQLString(entry.getValue())).toString());
        }
        stringBuffer.append(String.join(",", paramSet));

        stringBuffer.append(" ").append("where").append(" ").append(criteria.buildCondition(Criteria.SqlType.SQL));

        return stringBuffer.toString();
    }

    @Override
    public String buildHSql() {
        if (params.size() == 0) {
            return "wrong sql!";
        }
        if (criteria == null) {
            return "wrong sql";
        }
        params.remove(Constant.OID);
        StringBuilder stringBuilder = new StringBuilder("UPSERT INTO ").append(Constant.INTIMATE_APPKEY.toUpperCase()).append(".").append(getIndexName().toUpperCase()).append(" (");
        stringBuilder.append(Constant.OID + ",");
        stringBuilder.append(String.join(",", params.keySet())).append(") ");
        List<String> values = new LinkedList<>();
        for (Object object : params.values()) {
            values.add(StringTransformUtil.getValueToSQLString(object));
        }
        StringBuilder selectSql = new StringBuilder("SELECT ");
        selectSql.append(Constant.OID + ",");
        selectSql.append(String.join(",", values));
        selectSql.append(" FROM ").append(Constant.INTIMATE_APPKEY.toUpperCase()).append(".").append(getIndexName().toUpperCase());
        selectSql.append(" ").append("WHERE").append(" ").append(criteria.buildCondition(Criteria.SqlType.HQL));
        stringBuilder.append(" ").append(selectSql);
        return stringBuilder.toString();
    }
}
