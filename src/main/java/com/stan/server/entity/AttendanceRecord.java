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
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * <p>
 * 考勤记录表
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_record")
@ApiModel(value = "AttendanceRecord对象", description = "考勤记录表")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "签到地点-纬度")
    @TableField("location_lat")
    private Double locationLat;

    @ApiModelProperty(value = "签到地点-经度")
    @TableField("location_lon")
    private Double locationLon;

    @ApiModelProperty(value = "考勤时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "考勤方式")
    @TableField("attendance_mode")
    private Integer attendanceMode;

    @ApiModelProperty(value = "关联考勤规则")
    @TableField("rule_id")
    private Integer ruleId;

    @ApiModelProperty(value = "打卡地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "考勤状态")
    @TableField("purpose")
    private Integer purpose;
}