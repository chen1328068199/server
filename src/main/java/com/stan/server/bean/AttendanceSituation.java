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
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("attendance_situation")
@ApiModel(value="AttendanceSituation对象", description="")
public class AttendanceSituation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "员工ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "签到情况")
    @TableField("attendance_record")
    private Integer attendanceRecord;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String ATTENDANCE_RECORD = "attendance_record";

}