package com.chengxiaoxiao.admin.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/5 8:47 下午
 * @Description:
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
}
