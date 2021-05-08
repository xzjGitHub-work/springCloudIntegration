package com.yunqi.xzj.server.servcice.user.impl;

import com.yunqi.xzj.bean.UserModel;
import com.yunqi.xzj.dao.UserDao;
import com.yunqi.xzj.server.servcice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @describe:mybaties-puls
     *
     * @author:xzj
     * @createDate:2021/1/14 16:42
     * @param:[id]
     * @return:com.yunqi.bean.UserModel
     */
    @Override
    public UserModel findOne(String id) {
        return userDao.selectById(id);
    }

    /**
     * @describe:查询所有的数据
     *
     * @author:xzj
     * @createDate:2021/1/18 10:54
     * @param:[]
     * @return:com.yunqi.bean.UserModel
     */
    @Override
    public List<UserModel> findList() {
        return userDao.selectList(null);
    }
}