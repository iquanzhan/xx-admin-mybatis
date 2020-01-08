package com.chengxiaoxiao.admin.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Result
 *
 * @Description: 结果返回类
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:19
 */
@Getter
@Setter
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    private Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功时的回调
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    private Result(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    //错误的回调
    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<>(cm);
    }
}
