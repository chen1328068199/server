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
 * @author 
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_attendance_log")
@ApiModel(value="UserAttendanceLog对象", description="考勤记录表")
public class UserAttendanceLog implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "签到地点-纬度")
                        @TableId("id")
                            private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
                private Integer userId;

    @ApiModelProperty(value = "签到地点-纬度")
    @TableField("location_lat")
                private Double locationLat;

    @ApiModelProperty(value = "签到地点-经度")
    @TableField("location_lng")
                private Double locationLng;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
                private LocalDateTime createTime;

    @ApiModelProperty(value = "考勤方式")
    @TableField("way")
                private Integer way;

    @ApiModelProperty(value = "所属考勤")
    @TableField("attendance_id")
                private Integer attendanceId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LOCATION_LAT = "location_lat";

    public static final String LOCATION_LNG = "location_lng";

    public static final String CREATE_TIME = "create_time";

    public static final String WAY = "way";

    public static final String ATTENDANCE_ID = "attendance_id";

}