package com.yunqi.servcice.user.impl;

import com.yunqi.bean.UserModel;
import com.yunqi.dao.UserDao;
import com.yunqi.servcice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserModel findOne(String id) {
        return userDao.selectById(id);
    }
}