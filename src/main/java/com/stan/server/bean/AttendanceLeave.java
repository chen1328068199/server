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
 * 
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_leave")
@ApiModel(value="AttendanceLeave对象", description="")
public class AttendanceLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
                    @TableId("id")
                            private Integer id;

    @ApiModelProperty(value = "请假人员")
    @TableField("user_id")
                private Integer userId;

    @ApiModelProperty(value = "请假开始时间")
    @TableField("start_date_time")
                private LocalDateTime startDateTime;

    @ApiModelProperty(value = "请假结束时间")
    @TableField("end_date_time")
                private LocalDateTime endDateTime;

    @ApiModelProperty(value = "状态")
    @TableField("status")
                private Integer status;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String START_DATE_TIME = "start_date_time";

    public static final String END_DATE_TIME = "end_date_time";

    public static final String STATUS = "status";

}