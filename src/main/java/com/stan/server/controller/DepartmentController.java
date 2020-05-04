package com.stan.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.entity.Department;
import com.stan.server.model.User;
import com.stan.server.model.vo.UserVO;
import com.stan.server.service.DepartmentService;
import com.stan.server.service.UserService;
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
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private UserService userService;

    @GetMapping("page")
    @ApiOperation("分页查询部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页条数", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "department", value = "部门名", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "部门负责人"),
    })
    public ResultVO<Page<Department>> page(@RequestParam("current") int current,
                                           @RequestParam("size") int size,
                                           @RequestParam(value = "department", required = false) String department,
                                           @RequestParam(value = "userId", required = false) Integer userId) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(department != null && !department.equals(""), "name", department);
        queryWrapper.like(userId != null, "user_id", userId);
        Page<Department> page = new Page<>(current, size);
        departmentService.page(page, queryWrapper);
        List<Department> records = page.getRecords();
        if (records != null)
            for (Department record : records) {
                Integer recordUserId = record.getUserId();
                User user = userService.getById(recordUserId);
                if (user != null)
                    record.setUser(user.getUserName());
            }
        return ResultVO.success(page);
    }

    @PostMapping("add")
    @ApiOperation("新增部门")
    public ResultVO<Object> add(Department department) {
        departmentService.save(department);
        return ResultVO.success();
    }

    @PostMapping("update")
    @ApiOperation("更新部门信息")
    public ResultVO<Object> updateDepartment(Department department) {
        departmentService.updateById(department);
        return ResultVO.success();
    }

    @GetMapping("delete")
    @ApiOperation("删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门id", required = true),
    })
    public ResultVO<UserVO> deleteById(@RequestParam("id") Integer id) {
        departmentService.delete(id);
        return ResultVO.success();
    }
}

