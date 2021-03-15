package com.tech.eclouds.xzj.packer;


import com.tech.eclouds.xzj.common.Constant;
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
public class InsertPacker extends BasePacker {

    /**
     * 参数
     */
    private Map<String, Object> params = new LinkedHashMap<>();

    /**
     * 私有匿名构造器
     */
    private InsertPacker() {
    }

    /**
     * @param indexName 表名
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 类名驼峰转下划线
     * @Decription 反射获取属性名和属性值
     */
    private InsertPacker(String indexName, Object object, boolean available) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(indexName);
        if (object instanceof Map) {
            Map<String, Object> newMap = new HashMap<>();
            Iterator<Map.Entry<String, Object>> it = ((Map) object).entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String newKey = entry.getKey();
                newMap.put(newKey, entry.getValue());
            }
            set(newMap);
        } else {
            set(MapTransformUtils.objectToMap(object, available, true));
        }
    }

    /**
     * @param indexName 表名
     * @Decription 无数据
     */
    private InsertPacker(String indexName) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(indexName);
    }

    /**
     * 创建构造器
     *
     * @param indexName 表名
     * @Decription 无数据
     */
    public static InsertPacker create(String indexName) {
        return new InsertPacker(indexName);
    }

    /**
     * 创建构造器
     *
     * @param indexName 表名
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 反射获取属性名和属性值
     */
    public static InsertPacker create(String indexName, Object object, boolean available) {
        return new InsertPacker(indexName, object, available);
    }

    public static InsertPacker create(String indexName, Map<String, Object> data){
        return new InsertPacker(indexName, data, false);
    }

    /**
     * 创建构造器
     *
     * @param object    业务数据
     * @param available true：不包含非空字段,false:包含全字段
     * @Decription 反射获取属性名和属性值
     */
    public static InsertPacker create(Object object, boolean available) {
        return new InsertPacker(object.getClass().getSimpleName(), object, available);
    }


    /**
     * 设置属性及属性值
     *
     * @param column 属性名
     * @param value  属性值
     * @Decription 追加或覆盖属性及属性值
     */
    public InsertPacker set(String column, Object value) {
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
    public <T> InsertPacker set(SFunction<T, ?> column, Object value) {
        params.put(SerializedLambda.getColumnName(column), value);
        return this;
    }

    /**
     * 设置属性及属性值
     *
     * @param map 属性及属性值集合
     * @Decription 追加或覆盖属性及属性值
     */
    public InsertPacker set(Map<String, Object> map) {
        Map<String, Object> newMap = new HashMap<String, Object>();
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
    public InsertPacker set(Object object, boolean available) {
        set(MapTransformUtils.objectToMap(object, available, true));
        return this;
    }


    /**
     * 不包括属性及属性值
     *
     * @param columns 属性名
     * @Decription 追加或覆盖属性及属性值
     */
    public InsertPacker excludes(String... columns) {
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
    public <T> InsertPacker excludes(SFunction<T, ?>... columns) {
        for (SFunction<T, ?> column : columns) {
            params.remove(SerializedLambda.getColumnName(column));
        }
        return this;
    }

    /**
     * 清空属性及属性值
     */
    public void clearParams() {
        params.clear();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("InsertPacker{");
        sb.append("uuid='").append(getUuid()).append('\'');
        sb.append(", indexName='").append(getIndexName()).append('\'');
        sb.append(", params=").append(params);
        sb.append('}');
        return sb.toString();
    }

    public Map<String, Object> getParams() {
        return params;
    }


    @Override
    public String buildMSql() {
        if (params.size() == 0) {
            return "wrong sql!";
        }
        StringBuffer stringBuffer = new StringBuffer("insert into ").append(getIndexName()).append(" (");

        stringBuffer.append(String.join(",", params.keySet())).append(") values (");

        List<String> values = new LinkedList<>();
        for (Object object : params.values()) {
            values.add(StringTransformUtil.getValueToSQLString(object));
        }

        stringBuffer.append(String.join(",", values)).append(")");
        return stringBuffer.toString();
    }


    @Override
    public String buildHSql() {
        if (params.size() == 0) {
            return "wrong sql!";
        }

        StringBuffer stringBuffer = new StringBuffer("UPSERT INTO ").append(Constant.INTIMATE_APPKEY.toUpperCase()).append(".").append(getIndexName().toUpperCase()).append(" (");

        stringBuffer.append(String.join(",", params.keySet())).append(") VALUES (");

        List<String> values = new LinkedList<>();
        for (Object object : params.values()) {
            values.add(StringTransformUtil.getValueToSQLString(object));
        }

        stringBuffer.append(String.join(",", values)).append(")");
        return stringBuffer.toString();
    }
}
