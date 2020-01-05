package com.chengxiaoxiao.admin.dao;

import com.chengxiaoxiao.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAll();
}