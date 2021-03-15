package com.tech.eclouds.xzj.mysql.service;

import com.tech.eclouds.xzj.criteria.Criteria;
import com.tech.eclouds.xzj.domain.PageModel;
import com.tech.eclouds.xzj.mysql.IMySqlTemplate;
import com.tech.eclouds.xzj.mysql.mapper.BaseServiceMapper;
import com.tech.eclouds.xzj.packer.DeletePacker;
import com.tech.eclouds.xzj.packer.InsertPacker;
import com.tech.eclouds.xzj.packer.QueryPacker;
import com.tech.eclouds.xzj.packer.UpdatePacker;
import com.tech.eclouds.xzj.util.MapTransformUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/10/8
 */
@Service
public class MySqlTemplateImpl implements IMySqlTemplate {

    @Autowired
    private BaseServiceMapper baseServiceMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(InsertPacker insertPacker) {
        return baseServiceMapper.update(insertPacker.buildMSql());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] insertBatch(List<InsertPacker> insertPackers) {
        return insertBatch(insertPackers, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] insertBatch(List<InsertPacker> insertPackers, boolean isTransaction) {
        if (isTransaction) {
            String[] sqls = insertPackers.stream().map(insertPacker -> insertPacker.buildMSql()).toArray(String[]::new);
            try {
                return baseServiceMapper.batchUpdate(sqls);
            } catch (Exception e) {
                logger.error("批量新增异常：", e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new int[sqls.length];
            }
        } else {
            return insertPackers.stream().mapToInt(insertPacker -> {
                String sql = insertPacker.buildMSql();
                try {
                    return baseServiceMapper.update(sql);
                } catch (Exception e) {
                    logger.error("批量新增异常：", e);
                    return 0;
                }
            }).toArray();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(UpdatePacker updatePacker) {
        return baseServiceMapper.update(updatePacker.buildMSql());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] updateBatch(List<UpdatePacker> updatePackers) {
        return updateBatch(updatePackers, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] updateBatch(List<UpdatePacker> updatePackers, boolean isTransaction) {
        if (isTransaction) {
            String[] sqls = updatePackers.stream().map(updatePacker -> updatePacker.buildMSql()).toArray(String[]::new);

            try {
                return baseServiceMapper.batchUpdate(sqls);
            } catch (Exception e) {
                logger.error("批量新增异常：", e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new int[sqls.length];
            }
        } else {
            return updatePackers.stream().mapToInt(updatePacker -> {
                String sql = updatePacker.buildMSql();
                try {
                    return baseServiceMapper.update(sql);
                } catch (Exception e) {
                    logger.error("批量新增异常：", e);
                    return 0;
                }
            }).toArray();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(DeletePacker deletePacker) {
        return baseServiceMapper.update(deletePacker.buildMSql());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] deleteBatch(List<DeletePacker> deletePackers) {
        return deleteBatch(deletePackers, true);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int[] deleteBatch(List<DeletePacker> deletePackers, boolean isTransaction) {
        if (isTransaction) {
            String[] sqls = deletePackers.stream().map(deletePacker -> deletePacker.buildMSql()).toArray(String[]::new);

            try {
                return baseServiceMapper.batchUpdate(sqls);
            } catch (Exception e) {
                logger.error("批量新增异常：", e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return new int[sqls.length];
            }
        } else {
            return deletePackers.stream().mapToInt(deletePacker -> {
                String sql = deletePacker.buildMSql();
                try {
                    return baseServiceMapper.update(sql);
                } catch (Exception e) {
                    logger.error("批量新增异常：", e);
                    return 0;
                }
            }).toArray();
        }
    }

    @Override
    public PageModel select(QueryPacker queryPacker) {

        String sql = queryPacker.buildMSql();

        String countSql = sql.split("limit")[0];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" select count(1) CNT from (").append(countSql).append(") result");
        Map<String, Object> map = baseServiceMapper.queryForMap(stringBuilder.toString());

        Integer rows = MapUtils.getInteger(map, "CNT", 0);
        PageModel pageModel = new PageModel();
        pageModel.setForm(queryPacker.getFrom());
        pageModel.setSize(queryPacker.getSize());
        pageModel.setTotal(rows);
        if (rows > 0) {
            List<Map<String, Object>> list = baseServiceMapper.queryForList(sql);
            if (queryPacker.getQueryClazz() != null) {
                pageModel.setResultList((List) list.stream().map(m -> MapTransformUtils.mapToObject(m, queryPacker.getQueryClazz(), true)).collect(Collectors.toList()));
            } else {
                pageModel.setResultList(list);
            }
            pageModel.setTotalPages(pageModel.getSize() == 0 ? 1 : (int) Math.ceil((double) rows / (double) pageModel.getSize()));
        }

        return pageModel;
    }

    @Override
    public Object getMysql(String sql) {
        return baseServiceMapper.queryForList(sql);
    }

    @Override
    public Map<String, Object> selectOne(QueryPacker queryPacker) {
        return baseServiceMapper.queryForMap(queryPacker.buildMSql());
    }

    @Override
    public int count(QueryPacker queryPacker) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" select count(1) cnt from").append(" ").append(queryPacker.getIndexName());
        stringBuilder.append(" ").append("where").append(" ").append(queryPacker.getCriteria().buildCondition(Criteria.SqlType.SQL));
        Map<String, Object> objectMap = baseServiceMapper.queryForMap(stringBuilder.toString());
        return Integer.parseInt(objectMap.getOrDefault("cnt", 0).toString());
    }

    @Override
    public boolean tableExists(String indexName) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SHOW TABLES LIKE '").append(indexName).append("'");
            String sql = stringBuilder.toString();
            Map<String, Object> map = baseServiceMapper.queryForMap(sql);
            if (!map.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            logger.error("查询表是否存在失败", e);
        }
        return false;
    }

    @Override
    public boolean truncateTable(String indexName) {
        try {
            if (tableExists(indexName)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("truncate table ");
                stringBuilder.append(indexName);
                String sql = stringBuilder.toString();
                baseServiceMapper.execute(sql);
                return true;
            }

        } catch (Exception e) {
            logger.error("清空表失败", e);
        }
        return false;
    }

    @Override
    public boolean dropTable(String indexName) {
        try {
            if (tableExists(indexName)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("drop table ");
                stringBuilder.append(indexName);
                String sql = stringBuilder.toString();
                baseServiceMapper.execute(sql);
                return true;
            }
        } catch (Exception e) {
            logger.error("删除表失败", e);
        }
        return false;
    }



    @Override
    public boolean deleteColumn(String indexName, String field) {
        return deleteColumn(indexName, Arrays.asList(field));
    }

    @Override
    public boolean deleteColumn(String indexName, List<String> fields) {
        if (!tableExists(indexName)) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("alter table ").append(indexName);

        if (!fields.isEmpty()) {
            stringBuilder.append(" DROP ").append(String.join(", DROP ", fields));
        }

        baseServiceMapper.execute(stringBuilder.toString());
        return true;
    }
}
