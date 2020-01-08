package com.chengxiaoxiao.admin.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private String id;
    private String userName;
    private String nickName;
    private String realName;
    private Date birthday;
    private String email;
    private String phone;
    private String userPassword;
    private String descript;
    private Integer delete_status;
    private Date create_time;
    private Date update_time;
}