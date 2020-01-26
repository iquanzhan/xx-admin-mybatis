package com.chengxiaoxiao.web.repository;

import com.chengxiaoxiao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:39 下午
 * @Description:
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}