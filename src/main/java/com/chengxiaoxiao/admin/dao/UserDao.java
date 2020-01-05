package com.chengxiaoxiao.admin.dao;

import com.chengxiaoxiao.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/5 8:48 下午
 * @Description:
 */
@Mapper
public interface UserDao {
    List<User> getAll();
}
