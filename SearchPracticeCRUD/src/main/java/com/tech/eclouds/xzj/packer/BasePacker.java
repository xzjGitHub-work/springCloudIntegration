package com.tech.eclouds.xzj.packer;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/23
 */
public abstract class BasePacker implements Serializable, Comparable, Cloneable {

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    /**
     * 构建器唯一标识
     */
    private String uuid;
    /**
     * 表名
     */
    private String indexName;

    /**
     * 业务标识
     */
    private String appKey;

    public String getIndexName() {
        return indexName;
    }

    protected void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getUuid() {
        return uuid;
    }

    protected void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BasePacker)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        return StringUtils.equals(uuid, ((BasePacker) obj).getUuid());
    }

    public abstract String buildMSql();

    public abstract String buildHSql();

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
