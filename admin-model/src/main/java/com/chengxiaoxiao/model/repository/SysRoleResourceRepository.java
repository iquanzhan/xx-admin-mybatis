package com.chengxiaoxiao.model.repository;

import com.chengxiaoxiao.model.web.pojos.SysRoleResource;
import com.chengxiaoxiao.model.web.pojos.SysUserRole;

/**
 * 角色资源关联表
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:04 下午
 * @Description:
 */
public interface SysRoleResourceRepository extends BaseDao<SysRoleResource, String> {
    /**
     * 根据角色Id删除信息
     *
     * @param userId
     */
    void deleteByRoleId(String userId);

    /**
     * 根据资源Id删除信息
     *
     * @param resourceId
     */
    void deleteByResourceId(String resourceId);
}
