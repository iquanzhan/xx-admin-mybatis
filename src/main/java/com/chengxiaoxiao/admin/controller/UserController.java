package com.chengxiaoxiao.admin.controller;

import com.chengxiaoxiao.admin.domain.User;
import com.chengxiaoxiao.admin.result.Result;
import com.chengxiaoxiao.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/5 8:52 下午
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Api("用户管理API")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询单个用户信息", notes = "根据用户Id获取用户信息")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户Id", dataType = "String", required = true)
    public Result findById(@PathVariable("id") String id) {

        return Result.success(id);
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody User user) {
        return Result.success(user);
    }

    @ApiOperation(value = "修改用户信息", notes = "根据用户Id修改用户信息")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Result update(@PathVariable("id") String id, @RequestBody User user) {
        return Result.success(user);
    }

    @ApiOperation(value = "删除用户信息", notes = "根据用户的Id删除用户")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {

    }


}
