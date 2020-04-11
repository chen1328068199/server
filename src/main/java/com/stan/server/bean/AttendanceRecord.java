package com.stan.server.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考勤记录表
 * </p>
 *
 * @author Ren
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_record")
@ApiModel(value="AttendanceRecord对象", description="考勤记录表")
public class AttendanceRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "签到地点-纬度")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "签到地点-纬度")
    @TableField("location_lon")
    private Double locationLon;

    @ApiModelProperty(value = "签到地点-经度")
    @TableField("location_lat")
    private Double locationLat;

    @ApiModelProperty(value = "考勤时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "考勤方式或考勤状态")
    @TableField("attendance_mode")
    private Integer attendanceMode;

    @ApiModelProperty(value = "考勤目的")
    @TableField("attendance_purpose")
    private Integer attendancePurpose;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LOCATION_LON = "location_lpn";

    public static final String LOCATION_LAT = "location_lat";

    public static final String CREATE_TIME = "create_time";

    public static final String ATTENDANCE_MODE = "attendance_mode";

    public static final String ATTENDANCE_PURPOSE = "attendance_purpose";

}