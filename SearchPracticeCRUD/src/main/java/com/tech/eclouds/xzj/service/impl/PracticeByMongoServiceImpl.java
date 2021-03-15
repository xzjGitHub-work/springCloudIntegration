package com.tech.eclouds.xzj.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.dao.PracticeDao;
import com.tech.eclouds.xzj.packer.InsertPacker;
import com.tech.eclouds.xzj.service.PracticeByMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 9:37
 */
@Service
public class PracticeByMongoServiceImpl implements PracticeByMongoService {
    @Autowired
    private PracticeDao practiceDaol;

    @Override
    public void save(Map<String, String> map) {
        List<InsertPacker> list = new LinkedList<>();
        InsertPacker insertPacker = InsertPacker.create(map.get("name"), JSONObject.parseObject(JSONObject.toJSONString(map), Map.class), false);
        list.add(insertPacker);

        practiceDaol.save(list);
    }
}
