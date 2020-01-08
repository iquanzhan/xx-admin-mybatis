package com.chengxiaoxiao.admin.exception;

import com.chengxiaoxiao.admin.result.CodeMsg;
import com.chengxiaoxiao.admin.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * GlobleException处理类
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:32
 */
@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> execptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobleException) {
            GlobleException ex = (GlobleException) e;
            return Result.error(ex.getCm());
        } else {
            return Result.error(CodeMsg.ERROR);
        }
    }
}
