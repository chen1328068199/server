package com.stan.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.entity.Department;
import com.stan.server.entity.Role;
import com.stan.server.model.User;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.DepartmentService;
import com.stan.server.service.RoleService;
import com.stan.server.service.UserService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
@Slf4j
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;

    @GetMapping("page")
    @ApiOperation("分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "员工编号"),
            @ApiImplicitParam(name = "phoneNumber", value = "手机号"),
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "用户名", dataType = "String"),
    })
    public ResultVO<Page<UserVO>> page(@RequestParam("current") int current,
                                     @RequestParam("size") int size,
                                     @RequestParam(value = "userName", required = false) String userCode,
                                     @RequestParam(value = "userName", required = false) String userName,
                                     @RequestParam(value = "phoneNumber", required = false) Integer phoneNumber) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(userName != null && !userName.trim().equals(""), "user_name", userName);
        queryWrapper.like(userCode != null && !userCode.trim().equals(""), "user_code", userCode);
        queryWrapper.eq(phoneNumber != null, "phone_number", phoneNumber);
        Page<User> page = new Page<>(current, size);
        userService.page(page, queryWrapper);
        List<User> records = page.getRecords();
        Page<UserVO> page1 = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        if (records != null) {
            List<UserVO> list = new ArrayList<>(records.size());
            for (User record : records) {
                UserVO vo = new UserVO();
                vo.setId(record.getId());
                vo.setUserName(record.getUserName());
                vo.setName(record.getName());
                vo.setOpenId(record.getOpenId());
                Integer departmentId = record.getDepartmentId();
                vo.setDepartmentId(departmentId);
                Department d = departmentService.getById(departmentId);
                if (d != null)
                    vo.setDepartment(d.getName());
                vo.setPhoneNumber(record.getPhoneNumber());
                vo.setUserCode(record.getUserCode());
                list.add(vo);
            }
            page1.setRecords(list);
        }
        return ResultVO.success(page1);
    }

    @PostMapping("add")
    @ApiOperation("新增员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleIds", value = "角色", dataType = "String", required = true),
    })
    public ResultVO<Object> add(User user, String roleIds) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        int count = userService.count(queryWrapper);
        if (count > 0)
            return ResultVO.fail("该员工已存在");
        userService.addUser(user, roleIds);
        return ResultVO.success();
    }

    @GetMapping("delete")
    @ApiOperation("删除员工")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "员工标识", required = true),
    })
    public ResultVO<UserVO> deleteById(@RequestParam("id") Integer userId) {
        userService.delete(userId);
        return ResultVO.success();
    }

    @GetMapping("get")
    @ApiOperation("获得员工信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "员工标识", required = true),
    })
    public ResultVO<UserVO> getById(@RequestParam("id") Integer id) {
        User user = userService.getById(id);
        if (user == null)
            return ResultVO.fail("该员工不存在");
        UserVO vo = new UserVO();
        vo.setId(user.getId());
        vo.setUserName(user.getUserName());
        vo.setUserPassword(user.getUserPassword());
        vo.setOpenId(user.getOpenId());
        Integer departmentId = user.getDepartmentId();
        vo.setDepartmentId(departmentId);
        Department department = departmentService.getById(departmentId);
        if (department != null)
            vo.setDepartment(department.getName());
        vo.setUserCode(user.getUserCode());
        vo.setPhoneNumber(user.getPhoneNumber());
        vo.setName(user.getName());
        List<Role> roles = roleService.listSysRolesFromUser(vo.getId());
        List<Integer> collect = roles.stream().map(Role::getId).collect(Collectors.toList());
        vo.setRoles(collect);
        return ResultVO.success(vo);
    }

    @PostMapping("update")
    @ApiOperation("更新员工信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleIds", value = "角色", dataType = "String"),
    })
    public ResultVO<Object> updateUser(User user, String roleIds) {
        userService.updateUser(user, roleIds);
        return ResultVO.success();
    }

    @PostMapping("changePassword")
    @ApiOperation("修改本人密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public ResultVO<Object> changeCurrentUserPassword(@RequestParam("oldPassword") String oldPassword,
                                                               @RequestParam("newPassword") String newPassword) {
        return userService.updateCurrentUserPassword(oldPassword, newPassword);
    }
}
