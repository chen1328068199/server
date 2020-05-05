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
import java.time.LocalDate;

/**
 * <p>
 *
 * </p>
 *
 * @author Ren
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_leave")
@ApiModel(value = "AttendanceLeave对象", description = "")
public class AttendanceLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "假期开始时间")
    @TableField("start_date")
    private LocalDate startDate;

    @ApiModelProperty(value = "假期结束时间")
    @TableField("end_date")
    private LocalDate endDate;

    @ApiModelProperty(value = "请假原因说明")
    @TableField("reason")
    private String reason;

    @ApiModelProperty(value = "假期类型 0：事假 1：病假 3：休假")
    @TableField("type")
    private Integer type;
}