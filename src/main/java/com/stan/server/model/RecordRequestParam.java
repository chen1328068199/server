package com.stan.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="考勤记录分页查询参数", description="")
public class RecordRequestParam {
    @ApiModelProperty(value = "开始时间")
    private LocalDate startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDate endTime;

    @ApiModelProperty(value = "考勤方式")
    private Integer attendanceMode;

    @ApiModelProperty(value = "考勤目的")
    private Integer attendancePurpose;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "部门ID")
    private Integer departmentId;


}
