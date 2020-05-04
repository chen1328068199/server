package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionVO {
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "权限")
    private String permission;

    @ApiModelProperty(value = "显示给用户")
    private String title;

}
