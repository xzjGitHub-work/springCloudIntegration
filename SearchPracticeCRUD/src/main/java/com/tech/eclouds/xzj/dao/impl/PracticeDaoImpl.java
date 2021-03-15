package com.tech.eclouds.xzj.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.common.Constant;
import com.tech.eclouds.xzj.configuration.configService.impl.PracticeConfigServiceImpl;
import com.tech.eclouds.xzj.dao.PracticeDao;
import com.tech.eclouds.xzj.packer.InsertPacker;
import com.tech.eclouds.xzj.util.ValidateParamsUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 9:52
 */
@Service
public class PracticeDaoImpl implements PracticeDao {
    @Autowired
    private PracticeConfigServiceImpl practiceConfigServiceImpl;
    @Override
    public void save(List<InsertPacker> packerList) {
        if (ValidateParamsUtil.isEmpty(packerList)) {
            //todo 判断参数是否是空的
        }
        List<InsertPacker> originInsertPackers = new LinkedList();
        List<InsertPacker> indexInsertPackers = new LinkedList();
        for (InsertPacker packer : packerList) {

            JSONObject jsonObject = practiceConfigServiceImpl.getTable(packer.getIndexName());

            if (jsonObject.getBoolean("result") == null) {
                continue;
            }

//                //原始数据
//                Map<String, Object> originData = Maps.newLinkedHashMap();
//                //索引数据
//                Map<String, Object> indexData = Maps.newLinkedHashMap();
//
//                originData.putAll(packer.getParams());
//
//                indexModel.getFieldModels()
//                        .stream()
//                        .filter(fieldModel -> fieldModel.isEsIndex())
//                        .forEach(fieldModel -> indexData.put(fieldModel.getFieldName(), MapUtils.getObject(packer.getParams(), fieldModel.getFieldName(), fieldModel.getDefaultValue())));
//
//                String id = getPrimaryKey(originData);
//
//                originData.putIfAbsent(Constant.INS_MODIFY_TIME, System.currentTimeMillis());
//                originData.putIfAbsent(Constant.INS_CREATE_TIME, System.currentTimeMillis());
//                originData.put(Constant.INS_INDEX_STATUS, 1);
//
//                indexData.put(Constant.OID, id);
//
//                originInsertPackers.add(InsertPacker.create(packer.getIndexName(), originData));
//                indexInsertPackers.add(InsertPacker.create(packer.getIndexName(), indexData));
//            }
//
//            if (CollectionUtils.isNotEmpty(originInsertPackers)) {
//                Map<String, Object> originMessageData = Maps.newLinkedHashMap();
//                originMessageData.put("insertPackers", originInsertPackers);
//                mqTemplate.sendSysMessage(appKey + "." + MqContants.INTIMATE_MONGO_HANDLE_QUEUE,
//                        GZIPUtils.compress(MessageUtil.buildJSON("batchSave", originMessageData)));
//            }
//
//            if (CollectionUtils.isNotEmpty(indexInsertPackers)) {
//                Map<String, Object> indexMessageData = Maps.newLinkedHashMap();
//                indexMessageData.put("insertPackers", indexInsertPackers);
//                mqTemplate.sendSysMessage(appKey + "." + MqContants.INTIMATE_ES_HANDLE_QUEUE,
//                        GZIPUtils.compress(MessageUtil.buildJSON("batchSave", indexMessageData)));
//
//                //发送以图搜图数据消息
////                mqTemplate.sendSysMessage(appKey + "." + MqContants.INTIMATE_IMAGE_SEARCH_HANDLE_QUEUE,
////                        GZIPUtils.compress(MessageUtil.buildJSON("batchSave", indexMessageData)));
//            }
//
//        } catch (Exception e) {
//            logger.error("批量插入数据错误", e);
//            return ResponseModel.fail(ExceptionEnum.COMMON_ERROR_1a, "批量插入数据错误");
//        } finally {
//            originInsertPackers.clear();
//            indexInsertPackers.clear();
//        }
//        return ResponseModel.success();

        }

    }
}
