package com.stan.server.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
@ApiModel(value="AttendanceLog对象", description="用户考勤记录表")
public class AttendanceLog extends Model<AttendanceLog> {

private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "主键")
                                            private Integer id;

        @ApiModelProperty(value = "考勤发起人")
                private Integer userId;

        @ApiModelProperty(value = "考勤经度")
                private Double lat;

        @ApiModelProperty(value = "考勤纬度")
                private Double lng;

        @ApiModelProperty(value = "考勤有效距离")
                private Integer distance;

        @ApiModelProperty(value = "考勤开始时间")
                private LocalDateTime startTime;

        @ApiModelProperty(value = "考勤结束时间")
                private LocalDateTime endTime;

        @ApiModelProperty(value = "考勤方式")
                private String way;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LAT = "lat";

    public static final String LNG = "lng";

    public static final String DISTANCE = "distance";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String WAY = "way";

@Override
protected Serializable pkVal() {
            return this.id;
        }

        }