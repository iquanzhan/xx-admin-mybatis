package com.chengxiaoxiao.web.controller.v1;

import com.chengxiaoxiao.api.user.SysResourceControllerApi;
import com.chengxiaoxiao.model.common.dtos.query.PageQueryDtos;
import com.chengxiaoxiao.model.common.dtos.result.PageResult;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysresource.SysResourceSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysResourceTreeDto;
import com.chengxiaoxiao.model.web.pojos.SysResource;
import com.chengxiaoxiao.model.web.pojos.SysRole;
import com.chengxiaoxiao.model.web.pojos.SysRoleResource;
import com.chengxiaoxiao.web.controller.BaseController;
import com.chengxiaoxiao.web.service.SysResourceService;
import com.chengxiaoxiao.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色信息接口
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:24 下午
 * @Description:
 */
@RestController
@RequestMapping("/v1/sys-resource")
public class SysResourceController extends BaseController implements SysResourceControllerApi {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("")
    @Override
    public Result<PageResult<SysResource>> search(SysResourceSearchDto sysRoleSearchDto, PageQueryDtos dtos) {
        Page<SysResource> search = sysResourceService.search(sysRoleSearchDto, getPageRequest());
        return Result.success(new PageResult<>(search));
    }

    @GetMapping("/{id}")
    @Override
    public Result<SysResource> find(@PathVariable String id) {
        return Result.success(sysResourceService.find(id));
    }

    @PostMapping("")
    @Override
    public Result<SysResource> insert(@Valid @RequestBody SysResourceModelDto sysRole) {
        return Result.success(sysResourceService.insert(sysRole));
    }

    @PutMapping("/{id}")
    @Override
    public Result<SysResource> update(@PathVariable String id, @Valid @RequestBody SysResourceModelDto sysRole) {
        return Result.success(sysResourceService.update(id, sysRole));
    }

    @Override
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        sysResourceService.delete(id);
        return Result.success(null);
    }

    @Override
    @GetMapping("/tree/{parentId}")
    public Result<SysResourceTreeDto> tree(@PathVariable String parentId) {
        return Result.success(sysResourceService.treeResourcesByParentId(parentId));
    }

    @Override
    @GetMapping("/role/{resourceId}")
    public Result<List<SysRole>> getRolesByResourceId(@PathVariable String resourceId) {
        return Result.success(sysRoleService.getRolesByResourceId(resourceId));
    }

    @Override
    @PostMapping("/role/{resourceId}")
    public Result dispathchRoleByResourceId(@PathVariable String resourceId, @RequestBody String[] roleIds) {
        sysResourceService.dispatchRoleByResourceId(resourceId,roleIds);
        return Result.success(null);
    }


}
