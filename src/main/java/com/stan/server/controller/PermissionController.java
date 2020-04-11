package com.stan.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stan.server.bean.Permission;
import com.stan.server.service.PermissionService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("getByMenu")
    @ApiOperation("根据菜单查询权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuId", value = "权限所属页面ID", required = true, dataType = "Integer"),
    })
    public ResultVO<List<Permission>> page(@RequestParam("menuId") int menuId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menu_id", menuId);
        return ResultVO.success(permissionService.list(queryWrapper));
    }

    @PostMapping("add")
    @ApiOperation("添加权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "permission", value = "权限名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "权限描述", dataType = "String"),
            @ApiImplicitParam(name = "menuId", value = "角色名", required = true, dataType = "String"),
    })
    public ResultVO<Object> add(@RequestParam("permission") Permission permission) {
        permissionService.save(permission);
        return ResultVO.success();
    }

    @PostMapping("edit")
    @ApiOperation("修改权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "permission", value = "权限名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "权限描述", dataType = "String"),
            @ApiImplicitParam(name = "menuId", value = "角色名", required = true, dataType = "String"),
    })
    public ResultVO<Object> edit(@RequestParam("permission") Permission permission) {
        return permissionService.updatePermission(permission);
    }
}

