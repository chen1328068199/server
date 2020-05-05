package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AttendanceStatLineChartVO {
    @ApiModelProperty(value = "日期")
    private LocalDate date;

    @ApiModelProperty(value = "缺勤人数")
    private Integer absenceNum;

    @ApiModelProperty(value = "正常人数")
    private Integer normalNum;

    @ApiModelProperty(value = "加班人数")
    private Integer overtimeNum;

    @ApiModelProperty(value = "迟到人数")
    private Integer lateNum;

    @ApiModelProperty(value = "早退人数")
    private Integer earlyNum;

    @ApiModelProperty(value = "请假人数")
    private Integer leaveNum;

    @ApiModelProperty(value = "上班未打卡人数")
    private Integer notBeginNum;

    @ApiModelProperty(value = "下班未打卡人数")
    private Integer notEndNum;
}
