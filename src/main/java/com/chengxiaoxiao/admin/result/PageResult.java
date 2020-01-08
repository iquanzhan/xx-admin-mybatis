package com.chengxiaoxiao.admin.result;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页Model封装
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020-01-08 22:20
 */
@Data
public class PageResult<T> implements Serializable {
    private Integer total;
    private Integer pageSize;
    private Integer pageNumber;
    private List<T> rows;
}
