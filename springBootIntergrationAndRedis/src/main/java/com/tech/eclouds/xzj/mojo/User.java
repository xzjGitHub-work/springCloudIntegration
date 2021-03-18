package com.tech.eclouds.xzj.mojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/18 16:42
 */
@Getter
@Setter
public class User implements Serializable {
    private String userName;
    private String age;
    private String gender;

    public User(String userName, String age, String gender) {
        this.userName = userName;
        this.age = age;
        this.gender = gender;
    }
    public User(){}
}
