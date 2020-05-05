package com.stan.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="考勤记录分页查询参数", description="")
public class RecordRequestParam {
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @ApiModelProperty(value = "考勤方式 0：位置考勤 1：口令 2：二维码")
    private Integer attendanceMode;

    @ApiModelProperty(value = "记录类型，0：请假 1：上班打卡 2：下班打卡")
    private Integer type;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "员工编号")
    private String userCode;

    @ApiModelProperty(value = "部门ID")
    private Integer departmentId;


}
