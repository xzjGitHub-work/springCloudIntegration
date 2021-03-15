package com.tech.eclouds.xzj.packer;


import com.tech.eclouds.xzj.common.Constant;
import com.tech.eclouds.xzj.criteria.Criteria;

import java.util.UUID;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/23
 */
public class DeletePacker extends BasePacker {

    /**
     * 私有匿名构造器
     */
    private DeletePacker() {
    }

    /**
     * 条件构造器
     */
    private Criteria criteria;

    /**
     * 查询语句
     */
    private String queryString;

    /**
     * queryString
     *
     * @param queryString 查询语句
     * @return this
     */
    public DeletePacker QueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    /**
     * 创建构造器
     *
     * @param tableName 表名
     */
    private DeletePacker(String tableName) {
        setUuid(UUID.randomUUID().toString());
        setIndexName(tableName);
    }

    /**
     * 创建构造器
     *
     * @param tableName 表名
     */
    public static DeletePacker create(String tableName) {
        return new DeletePacker(tableName);
    }

    /**
     * 设置条件
     *
     * @param criteria 条件构造器
     */
    public DeletePacker where(Criteria criteria) {
        this.criteria = criteria;
        return this;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DeletePacker{");
        sb.append("uuid='").append(getUuid()).append('\'');
        sb.append(", queryString='").append(queryString).append('\'');
        sb.append(", indexName='").append(getIndexName()).append('\'');
        sb.append(", criteria=").append(criteria);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String buildMSql() {
        if (criteria == null) {
            throw new RuntimeException("条件不能为空");
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" delete from ").append(getIndexName());
        stringBuffer.append(" where ").append(" ").append(criteria.buildCondition(Criteria.SqlType.SQL));

        return stringBuffer.toString();
    }

    @Override
    public String buildHSql() {
        if (criteria == null) {
            throw new RuntimeException("条件不能为空");
        }
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(Constant.INTIMATE_APPKEY.toUpperCase()).append(".").append(getIndexName().toUpperCase());
        sb.append(" WHERE ").append(criteria.buildCondition(Criteria.SqlType.HQL));

        return sb.toString();
    }
}
