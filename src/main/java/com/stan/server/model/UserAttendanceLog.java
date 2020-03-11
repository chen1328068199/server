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
 * 考勤记录表
 * </p>
 *
 * @author 
 * @since 2020-03-11
 */
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserAttendanceLog对象", description="考勤记录表")
public class UserAttendanceLog extends Model<UserAttendanceLog> {

private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "签到地点-纬度")
                                            private Integer id;

        @ApiModelProperty(value = "用户ID")
                private Integer userId;

        @ApiModelProperty(value = "签到地点-纬度")
                private Double locationLat;

        @ApiModelProperty(value = "签到地点-经度")
                private Double locationLng;

        @ApiModelProperty(value = "创建时间")
                private LocalDateTime createTime;

        @ApiModelProperty(value = "考勤方式")
                private Integer way;

        @ApiModelProperty(value = "所属考勤")
                private Integer attendanceId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LOCATION_LAT = "location_lat";

    public static final String LOCATION_LNG = "location_lng";

    public static final String CREATE_TIME = "create_time";

    public static final String WAY = "way";

    public static final String ATTENDANCE_ID = "attendance_id";

@Override
protected Serializable pkVal() {
            return this.id;
        }

        }