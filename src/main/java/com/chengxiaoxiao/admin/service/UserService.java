package com.chengxiaoxiao.admin.service;

import com.chengxiaoxiao.admin.dao.UserDao;
import com.chengxiaoxiao.admin.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/5 8:51 下午
 * @Description:
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> getUserInfo(){
        return userDao.getAll();
    }
}
