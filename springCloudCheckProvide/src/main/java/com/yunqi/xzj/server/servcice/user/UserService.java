package com.yunqi.xzj.server.servcice.user;

import com.yunqi.xzj.bean.UserModel;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/1/14 11:34
 */
public interface UserService  {

    /**
     * @describe:mybaties-puls
     *
     * @author:xzj
     * @createDate:2021/1/14 16:42
     * @param:[id]
     * @return:com.yunqi.bean.UserModel
     */
    UserModel findOne(String id);
    /**
     * @describe:查询所有的数据
     *
     * @author:xzj
     * @createDate:2021/1/18 10:54
     * @param:[]
     * @return:com.yunqi.bean.UserModel
     */
    List<UserModel> findList();
}
