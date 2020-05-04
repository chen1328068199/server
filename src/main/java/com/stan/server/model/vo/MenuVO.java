package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class MenuVO {
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "菜单名")
    private String title;

    @ApiModelProperty(value = "别名")
    private String name;

    @ApiModelProperty(value = "权限")
    private List<PermissionVO> permissions = new LinkedList<>();
}
