package com.stan.server.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("attendance_rules")
@ApiModel(value="AttendanceRules对象", description="")
public class AttendanceRules implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
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
    private String way;

    @ApiModelProperty(value = "考勤口令")
    @TableField("attendance_word")
    private String attendanceWord;

    @ApiModelProperty(value = "规则状态")
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String LON = "lon";

    public static final String LAT = "lat";

    public static final String DISTANCE = "distance";

    public static final String WAY = "way";

    public static final String ATTENDANCE_WORD = "attendance_word";

    public static final String STATUS = "status";

}