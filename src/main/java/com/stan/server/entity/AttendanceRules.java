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
import java.time.LocalTime;

/**
 * <p>
 *
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_rules")
@ApiModel(value = "AttendanceRules对象", description = "")
public class AttendanceRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "经度")
    @TableField("lon")
    private Double lon;

    @ApiModelProperty(value = "纬度")
    @TableField("lat")
    private Double lat;

    @ApiModelProperty(value = "有效距离")
    @TableField("distance")
    private Integer distance;

    @ApiModelProperty(value = "考勤方式")
    @TableField("way")
    private Integer way;

    @ApiModelProperty(value = "考勤口令, 考勤方式为口令考勤时有效")
    @TableField("attendance_code")
    private String attendanceCode;

    @ApiModelProperty(value = "规则状态，是否启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "上班时间")
    @TableField("begin_work_time")
    private LocalTime beginWorkTime;

    @ApiModelProperty(value = "下班时间")
    @TableField("end_work_time")
    private LocalTime endWorkTime;

    @ApiModelProperty(value = "上班打卡最晚时间")
    @TableField("begin_work_end_time")
    private LocalTime beginWorkEndTime;

    @ApiModelProperty(value = "下班打卡最晚时间")
    @TableField("end_work_end_time")
    private LocalTime endWorkEndTime;

    @ApiModelProperty(value = "考勤规则说明")
    @TableField("description")
    private String description;


    public static final String ID = "id";

    public static final String LON = "lon";

    public static final String LAT = "lat";

    public static final String DISTANCE = "distance";

    public static final String WAY = "way";

    public static final String ATTENDANCE_CODE = "attendance_code";

    public static final String STATUS = "status";

    public static final String BEGIN_WORK_TIME = "begin_work_time";

    public static final String END_WORK_TIME = "end_work_time";

    public static final String BEGIN_WORK_END_TIME = "begin_work_end_time";

    public static final String END_WORK_END_TIME = "end_work_end_time";

    public static final String DESCRIPTION = "description";

}