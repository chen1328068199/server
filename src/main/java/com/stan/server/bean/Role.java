package com.stan.server.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 角色表
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色")
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "描述")
    @TableField("`describe`")
    private String describe;

    @ApiModelProperty(value = "状态")
    @TableField("`status`")
    private Integer status;


    public static final String ID = "id";

    public static final String ROLE = "role";

    public static final String NAME = "name";

    public static final String DESCRIBE = "describe";

    public static final String STATUS = "status";

}