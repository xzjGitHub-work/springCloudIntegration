package com.yunqi.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
@Getter
@Setter
public class UserModel implements Serializable {

    @TableId(type = IdType.UUID)
    private String id;

    private String account;

    private String password;
}