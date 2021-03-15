package com.tech.eclouds.xzj.mysql;


import com.tech.eclouds.xzj.domain.PageModel;
import com.tech.eclouds.xzj.packer.DeletePacker;
import com.tech.eclouds.xzj.packer.InsertPacker;
import com.tech.eclouds.xzj.packer.QueryPacker;
import com.tech.eclouds.xzj.packer.UpdatePacker;

import java.util.List;
import java.util.Map;

/**
 * MySql接口
 *
 * @author: 孙博
 * @version: 1.0
 */
public interface IMySqlTemplate {

    int insert(InsertPacker insertPacker);

    int[] insertBatch(List<InsertPacker> insertPackers);

    int[] insertBatch(List<InsertPacker> insertPackers, boolean isTransaction);

    int update(UpdatePacker updatePacker);

    int[] updateBatch(List<UpdatePacker> updatePackers);

    int[] updateBatch(List<UpdatePacker> updatePackers, boolean isTransaction);

    int delete(DeletePacker deletePacker);

    int[] deleteBatch(List<DeletePacker> deletePackers);

    int[] deleteBatch(List<DeletePacker> deletePackers, boolean isTransaction);

    PageModel select(QueryPacker queryPacker);

    Object getMysql(String sql);

    Map<String, Object> selectOne(QueryPacker queryPacker);

    int count(QueryPacker queryPacker);

    /**
     * 判断表是否存在
     *
     * @param indexName
     */

    boolean tableExists(String indexName);

    /**
     * 清空表数据
     *
     * @param indexName
     */
    boolean truncateTable(String indexName);

    /**
     * 删除表
     *
     * @param indexName
     */
    boolean dropTable(String indexName);


    /**
     * 删除表对应字段
     *
     * @param indexName
     * @param field
     */
    boolean deleteColumn(String indexName, String field);

    boolean deleteColumn(String indexName, List<String> field);

}
