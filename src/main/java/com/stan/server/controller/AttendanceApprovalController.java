package com.stan.server.controller;

import com.stan.server.model.FillApprovalRequestParam;
import com.stan.server.model.LeaveApprovalRequestParam;
import com.stan.server.service.AttendanceApprovalService;
import com.stan.server.utils.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/attendanceApproval")
public class AttendanceApprovalController {

    @Autowired
    private AttendanceApprovalService attendanceApprovalService;

    @GetMapping("requestLeave")
    @ApiOperation("发起请假审批")
    public ResultVO<Object> requestLeave(LeaveApprovalRequestParam requestParam) {
        attendanceApprovalService.requestLeave(requestParam);
        return ResultVO.success();
    }

    @GetMapping("requestFill")
    @ApiOperation("发起补卡审批")
    public ResultVO<Object> requestFill(FillApprovalRequestParam requestParam) {
        attendanceApprovalService.requestFill(requestParam);
        return ResultVO.success();
    }
}

