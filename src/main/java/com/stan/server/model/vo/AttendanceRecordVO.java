package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttendanceRecordVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "员工名")
    private String userName;

    @ApiModelProperty(value = "考勤时间")
    private LocalDateTime attendanceTime;

    @ApiModelProperty(value = "考勤方式")
    private String attendanceMode;

    @ApiModelProperty(value = "打卡地址")
    private String address;

    @ApiModelProperty(value = "考勤状态")
    private String type;

    @ApiModelProperty(value = "员工编号")
    private String userCode;

    @ApiModelProperty(value = "部门")
    private String department;
}
