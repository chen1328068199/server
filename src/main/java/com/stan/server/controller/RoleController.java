package com.stan.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.entity.Role;
import com.stan.server.service.RoleService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("edit")
    @ApiOperation("修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "menuIds", value = "可查看菜单Id", dataType = "String"),
            @ApiImplicitParam(name = "permissionIds", value = "关联权限ID，多个权限以逗号分隔", dataType = "String"),
    })
    public ResultVO<Object> edit(Role role,
                                 @RequestParam(value = "menuIds", required = false) String menuIds,
                                 @RequestParam(value = "permissionIds", required = false) String permissionIds) {
        return roleService.updateRole(role, menuIds, permissionIds);
    }

    @GetMapping("page")
    @ApiOperation("分页查询角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "role", value = "角色名", required = true, dataType = "String"),
    })
    public ResultVO<Page<Role>> page(@RequestParam("current") int current, @RequestParam("size") int size, @RequestParam(value = "role", required = false) String role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(role != null && !role.equals(""), "role", role);
        Page<Role> page = new Page<>(current, size);
        roleService.page(page, queryWrapper);
        return ResultVO.success(page);
    }

    @PostMapping("add")
    @ApiOperation("添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "menus", value = "可查看菜单ID，多个菜单用逗号分隔", dataType = "String"),
            @ApiImplicitParam(name = "describe", value = "角色描述", dataType = "String"),
            @ApiImplicitParam(name = "permissionIds", value = "关联权限ID，多个权限以逗号分隔", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "角色状态", dataType = "0-不启用"),
    })
    public ResultVO<Object> add(Role role,
                                @RequestParam(value = "menus", required = false) String menus,
                                @RequestParam(value = "permissions", required = false) String permissionIds) {
        return roleService.addRole(role, menus, permissionIds);
    }

    @GetMapping("delete")
    @ApiOperation("删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID"),
    })
    public ResultVO<Object> delete(@RequestParam("roleId") String roleId) {
        roleService.removeById(roleId);
        return ResultVO.success();
    }

    @GetMapping("listPermissionsByRole")
    @ApiOperation("获得角色拥有的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID"),
    })
    public ResultVO<List<String>> listPermissionsByRole(@RequestParam("id") Integer id) {
        List<String> ids = roleService.listPermissionsByRole(id);
        return ResultVO.success(ids);
    }

}

