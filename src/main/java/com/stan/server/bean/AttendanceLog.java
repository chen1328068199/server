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
 * 用户考勤记录表
 * </p>
 *
 * @author 
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_log")
@ApiModel(value="AttendanceLog对象", description="用户考勤记录表")
public class AttendanceLog implements Serializable {

private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
                        @TableId("id")
                            private Integer id;

    @ApiModelProperty(value = "考勤发起人")
    @TableField("user_id")
                private Integer userId;

    @ApiModelProperty(value = "考勤经度")
    @TableField("lat")
                private Double lat;

    @ApiModelProperty(value = "考勤纬度")
    @TableField("lng")
                private Double lng;

    @ApiModelProperty(value = "考勤有效距离")
    @TableField("distance")
                private Integer distance;

    @ApiModelProperty(value = "考勤开始时间")
    @TableField("start_time")
                private LocalDateTime startTime;

    @ApiModelProperty(value = "考勤结束时间")
    @TableField("end_time")
                private LocalDateTime endTime;

    @ApiModelProperty(value = "考勤方式")
    @TableField("way")
                private String way;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LAT = "lat";

    public static final String LNG = "lng";

    public static final String DISTANCE = "distance";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String WAY = "way";

}