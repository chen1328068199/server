package com.stan.server.entity;

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
 * 系统菜单
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="系统菜单")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "别名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "重定向")
    @TableField("redirect")
    private String redirect;

    @ApiModelProperty(value = "url")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "是否在菜单显示")
    @TableField("hide_in_menu")
    private Integer hideInMenu;

    @ApiModelProperty(value = "父菜单id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "子菜单id")
    @TableField("children_id")
    private String childrenId;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    @TableField("remark")
    private String remark;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "path")
    @TableField(exist = false)
    private String path;


    public static final String ID = "id";

    public static final String TITLE = "title";

    public static final String NAME = "name";

    public static final String REDIRECT = "redirect";

    public static final String COMPONENT = "component";

    public static final String HIDE_IN_MENU = "hide_in_menu";

    public static final String PARENT_ID = "parent_id";

    public static final String CHILDREN_ID = "children_id";

    public static final String SORT = "sort";

    public static final String REMARK = "remark";

    public static final String ICON = "icon";

}