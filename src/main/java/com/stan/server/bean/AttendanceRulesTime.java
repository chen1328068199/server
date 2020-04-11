package com.stan.server.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalTime;
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
 * @since 2020-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_rules_time")
@ApiModel(value="AttendanceRulesTime对象", description="")
public class AttendanceRulesTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "有效日期")
    @TableField("effective_date")
    private LocalDate effectiveDate;

    @ApiModelProperty(value = "考勤时间段开始时间")
    @TableField("start_time")
    private LocalTime startTime;

    @ApiModelProperty(value = "考勤时间段结束时间")
    @TableField("end_time")
    private LocalTime endTime;

    @ApiModelProperty(value = "考勤目的")
    @TableField("attendance_purpose")
    private Integer attendancePurpose;

    @ApiModelProperty(value = "状态")
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String EFFECTIVE_DATE = "effective_date";

    public static final String START_TIME = "start_time";

    public static final String END_TIME = "end_time";

    public static final String ATTENDANCE_PURPOSE = "attendance_purpose";

    public static final String STATUS = "status";

}