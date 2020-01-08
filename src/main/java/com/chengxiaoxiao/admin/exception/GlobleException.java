package com.chengxiaoxiao.admin.exception;

import com.chengxiaoxiao.admin.result.CodeMsg;
import lombok.Getter;

/**
 * GlobleException
 *
 * @Description: 定义全局异常，与CodeMsg适配，  异常时可使用throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);  进行抛出。
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:17
 */
public class GlobleException extends RuntimeException{

    @Getter
    private CodeMsg cm;

    public GlobleException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }
}
