package com.stan.server.controller;

import com.stan.server.model.AttendanceStatsRequestParam;
import com.stan.server.model.vo.AttendanceRecordStatVO;
import com.stan.server.model.vo.AttendanceStatVO;
import com.stan.server.service.AttendanceStatsService;
import com.stan.server.utils.ResultVO;
import com.stan.server.utils.SecurityAuthUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/attendanceStats")
public class AttendanceStatsController {

    @Autowired
    private AttendanceStatsService attendanceStatsService;

    @GetMapping("statisticInfo")
    @ApiOperation("考勤统计信息")
    public ResultVO<List<AttendanceStatVO>> page(AttendanceStatsRequestParam requestParam) {
        if (requestParam.getEndDate() == null)
            requestParam.setEndDate(LocalDate.now().minusDays(1));
        if (requestParam.getStartDate() == null)
            requestParam.setStartDate(requestParam.getEndDate().minusDays(7));
        return ResultVO.success(attendanceStatsService.getStats(requestParam));
    }

    @GetMapping("getHistoryStatsFromUser")
    @ApiOperation("获得指定员工的历史考勤记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "员工ID", required = true),
            @ApiImplicitParam(name = "date", value = "查询日期", required = true)
    })
    public ResultVO<List<AttendanceRecordStatVO>> getHistoryStatsFromUser(@RequestParam("userId") Integer userId,
                                                                          @RequestParam("date")
                                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                  LocalDate date) {
        return ResultVO.success(attendanceStatsService.attendanceStatsService(userId, date));
    }

    @GetMapping("getHistoryStatsFromCurrentUser")
    @ApiOperation("获得当前员工的历史考勤记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "date", value = "查询日期", required = true)
    })
    public ResultVO<List<AttendanceRecordStatVO>> getHistoryStatsFromCurrentUser(@RequestParam("date")
                                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                                         LocalDate date) {
        return ResultVO.success(attendanceStatsService.attendanceStatsService(SecurityAuthUtil.getLoginId(), date));
    }


}

