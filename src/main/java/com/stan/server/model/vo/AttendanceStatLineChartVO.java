package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceStatLineChartVO {
    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "日期")
    private LocalDate date;
}
