package com.tech.eclouds.xzj.dao;

import com.tech.eclouds.xzj.packer.InsertPacker;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: xzj
 * @CreateDate: 2021/3/15 9:53
 */
public interface PracticeDao {
    void save(List<InsertPacker> packerList);
}
