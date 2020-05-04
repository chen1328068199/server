package com.stan.server.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AttendanceRecordStatVO {

    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "考勤状态")
    private String status;

    @ApiModelProperty(value = "考勤方式")
    private String attendanceWay;

    @ApiModelProperty(value = "上班打卡时间")
    private LocalTime beginWorkingTime;

    @ApiModelProperty(value = "下班打卡时间")
    private LocalTime endWorkingTime;

}
