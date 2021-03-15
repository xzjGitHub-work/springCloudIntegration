package com.tech.eclouds.xzj.mysql.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: 孙博
 * @Version: 1.0
 */
@Repository
public class BaseServiceMapper {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 执行sql语句
     *
     * @param sql
     */
    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }

    /**
     * 执行 insert update delete 语句
     *
     * @param sql
     * @return
     */
    public int update(String sql) {
        return jdbcTemplate.update(sql);
    }

    /**
     * 批量执行 insert update delete 语句
     *
     * @param sql
     * @return
     */
    public int[] batchUpdate(String... sql) {
        return jdbcTemplate.batchUpdate(sql);
    }

    /**
     * 查询返回list
     *
     * @param sql
     * @return
     */
    public List<Map<String, Object>> queryForList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 查询返回Map
     *
     * @param sql
     * @return
     */
    public Map<String, Object> queryForMap(String sql) {
        try {
            return jdbcTemplate.queryForMap(sql);
        } catch (Exception e) {
            return new HashMap();
        }

    }


}
