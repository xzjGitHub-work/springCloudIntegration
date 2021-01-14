package com.yunqi.servcice.user;

import com.yunqi.bean.UserModel;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:34
 */
public interface UserService  {

    UserModel findOne(String id);
}
