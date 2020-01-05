package com.chengxiaoxiao.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.chengxiaoxiao.admin.dao")
public class XxAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(XxAdminApplication.class, args);
    }

}
