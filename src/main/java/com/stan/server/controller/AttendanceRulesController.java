package com.stan.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.entity.AttendanceRules;
import com.stan.server.service.AttendanceRulesService;
import com.stan.server.service.AttendanceRulesTimeService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/attendanceRules")
public class AttendanceRulesController {

    @Autowired
    private AttendanceRulesService attendanceRulesService;
    @Autowired
    private AttendanceRulesTimeService attendanceRulesTimeService;

    @GetMapping("page")
    @ApiOperation("根据考勤方式获得考勤规则")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "current", value = "当前页"),
//            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true),
            @ApiImplicitParam(name = "attendanceWay", value = "考勤方式", required = true),
    })
    public ResultVO<IPage<AttendanceRules>> page(@RequestParam(value = "current", defaultValue = "1") int current,
                                                 @RequestParam(value = "size", defaultValue = "1") int size,
                                                 @RequestParam("attendanceWay") Integer attendanceWay) {
        Page<AttendanceRules> page = new Page<>(current, size);
        QueryWrapper<AttendanceRules> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(attendanceWay != null, "way", attendanceWay);
        attendanceRulesService.page(page, queryWrapper);
        return ResultVO.success(page);
    }

    @GetMapping("setWorkingDay")
    @ApiOperation("设置日期是否需要考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", required = true),
            @ApiImplicitParam(name = "needWorking", value = "是否需要工作 1：需要 0：不需要", required = true),
    })
    public ResultVO<IPage<AttendanceRules>> set(@RequestParam("date")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate date,
                                                @RequestParam("needWorking") Integer needWorking) {
        attendanceRulesTimeService.setDay(date, needWorking);
        return ResultVO.success();
    }

    @GetMapping("getDay")
    @ApiOperation("查看日期是否需要考勤")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "日期", required = true),
            @ApiImplicitParam(name = "needWorking", value = "是否需要工作 1：需要 0：不需要", required = true),
    })
    public ResultVO<Integer> getDay(@RequestParam("date")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate date) {
        boolean needWorking = attendanceRulesTimeService.needWorking(date);
        return ResultVO.success(needWorking ? 1 : 0);
    }

//    @GetMapping("add")
//    @ApiOperation("新增考勤规则")
//    public ResultVO<IPage<AttendanceRules>> add(AttendanceRules rule) {
//        attendanceRulesService.save(rule);
//        return ResultVO.success();
//    }

    @PostMapping("update")
    @ApiOperation("修改考勤, 除了考勤状态和考勤")
    public ResultVO<Object> update(AttendanceRules rule) {
        rule.setStatus(null);
        rule.setWay(null);
        rule.setId(null);
        UpdateWrapper<AttendanceRules> updateWrapper = new UpdateWrapper<>();
        attendanceRulesService.update(rule, updateWrapper);
        return ResultVO.success();
    }

    @PostMapping("updateStatusByWay")
    @ApiOperation("启用或关闭指定考勤方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "way", value = "考勤方式", required = true),
            @ApiImplicitParam(name = "status", value = "1:启用 0:关闭", required = true),
    })
    public ResultVO<Object> updateStatusByWay(@RequestParam("way") Integer way, @RequestParam("status") Integer status) {
        UpdateWrapper<AttendanceRules> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("way", way);
        updateWrapper.set("status", status);
        attendanceRulesService.update(updateWrapper);
        return ResultVO.success();
    }
}

