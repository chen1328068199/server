package com.stan.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel(value="请假审批参数", description="")
public class LeaveApprovalRequestParam {

    @ApiModelProperty(value = "审批发起人ID")
    private Integer initiator;

    @ApiModelProperty(value = "审批接收人ID,多个审批接收人按审批顺序用逗号分隔")
    private String receiver;

    @ApiModelProperty(value = "假期开始时间")
    private LocalDate startDate;

    @ApiModelProperty(value = "假期结束时间")
    private LocalDate endDate;

    @ApiModelProperty(value = "请假原因说明")
    private String reason;

    @ApiModelProperty(value = "假期类型")
    private Integer type;

}
