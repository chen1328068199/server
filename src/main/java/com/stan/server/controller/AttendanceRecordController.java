package com.stan.server.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stan.server.entity.AttendanceRecord;
import com.stan.server.model.RecordRequestParam;
import com.stan.server.model.vo.AttendanceRecordVO;
import com.stan.server.model.vo.AttendanceVO;
import com.stan.server.service.AttendanceRecordService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.SecurityAuthUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 考勤记录表 前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/attendanceRecord")
public class AttendanceRecordController {

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @GetMapping("pageOrderByAttendanceTime")
    @ApiOperation("分页查询考勤记录并根据考勤时间排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", required = true),
            @ApiImplicitParam(name = "size", value = "每页显示条数", required = true),
            @ApiImplicitParam(name = "isAsc", value = "根据考勤时间，默认降序 0升序/1降序"),
    })
    public ResultVO<IPage<AttendanceRecordVO>> page(@RequestParam("current") int current,
                                                    @RequestParam("size") int size,
                                                    @RequestParam(value = "isAsc", required = false) Integer isAsc,
                                                    RecordRequestParam requestParam) {
        Page<AttendanceRecord> page = new Page<>(current, size);
        if (isAsc == null || isAsc == 1)
            page.addOrder(OrderItem.desc("attendance_time"));
        else
            page.addOrder(OrderItem.asc("attendance_time"));
        return ResultVO.success(attendanceRecordService.pageOrderByAttendanceTime(page, requestParam));
    }

    @GetMapping("getAttendanceRecordFromCurrentUser")
    @ApiOperation("获得当前用户当天的考勤记录")
    public ResultVO<AttendanceVO> getAttendanceRecordFromCurrentUser() {
        return ResultVO.success(attendanceRecordService.getAttendanceRecordFromUser(SecurityAuthUtil.getLoginId()));
    }

    @GetMapping("getAttendanceRecordFromUser")
    @ApiOperation("获得指定用户当天的考勤记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "指定员工ID", required = true),
    })
    public ResultVO<AttendanceVO> getAttendanceRecordFromUser(@RequestParam("userId") Integer userId) {
        return ResultVO.success(attendanceRecordService.getAttendanceRecordFromUser(userId));
    }
}

