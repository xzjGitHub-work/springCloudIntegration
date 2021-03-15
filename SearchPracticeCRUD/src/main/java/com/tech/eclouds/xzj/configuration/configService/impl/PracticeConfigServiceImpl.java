package com.tech.eclouds.xzj.configuration.configService.impl;

import com.alibaba.fastjson.JSONObject;
import com.tech.eclouds.xzj.common.RedisContants;
import com.tech.eclouds.xzj.criteria.Criteria;
import com.tech.eclouds.xzj.domain.Account;
import com.tech.eclouds.xzj.domain.PageModel;
import com.tech.eclouds.xzj.domain.User;
import com.tech.eclouds.xzj.mysql.IMySqlTemplate;
import com.tech.eclouds.xzj.packer.QueryPacker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 9:56
 */
@Slf4j
@Service
public class PracticeConfigServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IMySqlTemplate mySqlTemplate;

    public JSONObject getTable(String indexName) {
        if (StringUtils.isBlank(indexName)) {
            //todo 判断当前要操作的数据库是否存在
        }
        JSONObject json = new JSONObject();
        System.out.println(redisTemplate.boundHashOps(RedisContants.INDEX_MODLE + "wlzx").get("xzj"));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(redisTemplate.boundHashOps(RedisContants.INDEX_MODLE + "wlzx").get(indexName)));
        if (jsonObject == null) {
            try {
                PageModel<User> pageModel =
                        mySqlTemplate.select(QueryPacker.create(User.class).where(Criteria.field(User::getName).eq("root")));
                if (pageModel == null || CollectionUtils.isEmpty(pageModel.getResultList())) {

                }
                User user = pageModel.getResultList().stream().findFirst().get();
                PageModel<Account> fieldPageModel = mySqlTemplate.select(QueryPacker.create(User.class).where(Criteria.field(Account::getUserId).eq(user.getId())));
                redisTemplate.boundHashOps("wlzx").put("xzj", fieldPageModel);
                json.put("result",true);
                json.put("msg","success");
                return json;
            } catch (Exception e) {
                log.error("初始化数据模型异常", e);
                json.put("result",false);
                json.put("msg","初始化数据模型异常");
                return json;
            }
        }
        json.put("result",false);
        json.put("msg","failed");
        return json;
    }
}
