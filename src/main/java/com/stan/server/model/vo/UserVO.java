package com.stan.server.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVO {
    @ApiModelProperty(value = "自增id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "菜单权限")
    private String modules;
    @ApiModelProperty(value = "角色")
    private String roles;
}
