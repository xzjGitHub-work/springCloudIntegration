package com.tech.eclouds.xzj.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.tech.eclouds.xzj.common.Constant;
import com.tech.eclouds.xzj.configuration.configService.impl.PracticeConfigServiceImpl;
import com.tech.eclouds.xzj.dao.PracticeDao;
import com.tech.eclouds.xzj.domain.User;
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
            User user = (User)jsonObject.get("data");
            if (jsonObject.getBoolean("result") == null) {
                continue;
            }



        }

    }
}
