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
 * @since 2020-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("c_department")
@ApiModel(value="Department对象", description="")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
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


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PARENT_ID = "parent_id";

    public static final String CHILDREN_ID = "children_id";

}