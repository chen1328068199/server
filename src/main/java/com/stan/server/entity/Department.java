package com.stan.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ren
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_department")
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "父ID")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "子ID")
    @TableField("children_id")
    private String childrenId;

    @ApiModelProperty(value = "部门描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "部门负责人ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "部门负责人")
    @TableField(exist = false)
    private String user;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";

    public static final String CHILDREN_ID = "children_id";

    public static final String DESCRIPTION = "description";

    public static final String USER_ID = "user_id";

}