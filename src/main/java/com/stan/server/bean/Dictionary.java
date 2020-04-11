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
 * 字典表
 * </p>
 *
 * @author Ren
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dictionary")
@ApiModel(value="Dictionary对象", description="字典表")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "类别")
    @TableField("category")
    private String category;

    @ApiModelProperty(value = "字段")
    @TableField("key")
    private Integer key;

    @ApiModelProperty(value = "字段含义")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "父类")
    @TableField("parent_id")
    private Integer parentId;


    public static final String ID = "id";

    public static final String CATEGORY = "category";

    public static final String KEY = "key";

    public static final String VALUE = "value";

    public static final String PARENT_ID = "parent_id";

}