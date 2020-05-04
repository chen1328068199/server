package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRecordStatVO {
    @ApiModelProperty(value = "打卡人数")
    private Integer number;
}
