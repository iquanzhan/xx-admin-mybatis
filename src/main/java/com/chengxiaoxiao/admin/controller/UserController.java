package com.chengxiaoxiao.admin.controller;

import com.chengxiaoxiao.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/5 8:52 下午
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public List getAll(){
        return userService.getUserInfo();
    }
}
