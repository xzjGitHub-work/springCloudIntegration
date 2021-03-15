package com.tech.eclouds.xzj.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 14:07
 */
@Getter
@Setter
public class Account implements Serializable {
    private String id;
    private String name;
    private String balance;
    private String UserId;
}
