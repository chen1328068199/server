package com.stan.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@ApiModel(value="补卡审批参数", description="")
public class FillApprovalRequestParam {

    @ApiModelProperty(value = "审批发起人")
    private Integer initiator;

    @ApiModelProperty(value = "审批接收人,多个审批接收人按审批顺序用逗号分隔")
    private String receiver;

    @ApiModelProperty(value = "补卡时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    @ApiModelProperty(value = "补卡类型 1：上班 2：下班")
    private Integer type;

    @ApiModelProperty(value = "审批描述")
    private String description;
}
