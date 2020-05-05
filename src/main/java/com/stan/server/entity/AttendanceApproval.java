package com.stan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Ren
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_approval")
@ApiModel(value = "AttendanceApproval对象", description = "")
public class AttendanceApproval implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "审批发起人")
    @TableField("initiator")
    private Integer initiator;

    @ApiModelProperty(value = "审批接收人,多个审批接收人按审批顺序用逗号分隔")
    @TableField("receiver")
    private String receiver;

    @ApiModelProperty(value = "审批状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "审批类型")
    @TableField("approval_type")
    private Integer approvalType;

    @ApiModelProperty(value = "关联记录ID, 请假记录关联的是请假ID")
    @TableField("record_id")
    private Integer recordId;

    @ApiModelProperty(value = "审批描述")
    @TableField("description")
    private String description;
}