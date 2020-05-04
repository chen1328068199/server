package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceVO {
    @ApiModelProperty(value = "是否请假， 大于0：请假 0：未请假")
    private Integer leave;
    @ApiModelProperty(value = "是否上班打卡， 大于0：已上班打卡 0：未上班打卡")
    private Integer beginWorking;
    @ApiModelProperty(value = "是否下班打卡， 大于0：已下班打卡 0：未下班打卡")
    private Integer endWorking;
}
