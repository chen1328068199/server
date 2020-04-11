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
@TableName("sys_permission")
@ApiModel(value="SysPermission对象", description="")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "权限")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "权限描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "权限所属菜单")
    @TableField("menu_id")
    private Integer menuId;


    public static final String ID = "id";

    public static final String PERMISSION = "permission";

    public static final String DESCRIPTION = "description";

    public static final String MENU_ID = "menu_id";

}