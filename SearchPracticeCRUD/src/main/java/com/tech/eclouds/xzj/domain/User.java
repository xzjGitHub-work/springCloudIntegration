package com.tech.eclouds.xzj.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 13:54
 */
@Getter
@Setter
public class User implements Serializable {
    private String id;
    private String account;
    private String password;
    private String name;

}
