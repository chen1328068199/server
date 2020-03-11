package com.stan.server.bean;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @author 
 * @since 2020-03-11
 */
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AttendanceWay对象", description="")
public class AttendanceWay extends Model<AttendanceWay> {

private static final long serialVersionUID=1L;

        @ApiModelProperty(value = "主键")
                                            private Integer id;

        @ApiModelProperty(value = "考勤方式")
                private String way;


    public static final String ID = "id";

    public static final String WAY = "way";

@Override
protected Serializable pkVal() {
            return this.id;
        }

        }