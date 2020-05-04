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
import java.time.LocalTime;

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
@TableName("attendance_stats")
@ApiModel(value = "AttendanceStats对象", description = "")
public class AttendanceStats implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "考勤状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "考勤方式")
    @TableField("attendance_way")
    private Integer attendanceWay;

    @ApiModelProperty(value = "考勤日期")
    @TableField("attendance_date")
    private LocalDate attendanceDate;

    @ApiModelProperty(value = "上班打卡时间")
    @TableField("begin_working_time")
    private LocalTime beginWorkingTime;

    @ApiModelProperty(value = "下班打卡时间")
    @TableField("end_working_time")
    private LocalTime endWorkingTime;
}