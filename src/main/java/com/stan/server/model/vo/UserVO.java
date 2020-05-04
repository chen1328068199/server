package com.stan.server.model.vo;

import com.stan.server.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserVO {
    @ApiModelProperty(value = "自增id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "部门ID")
    private Integer departmentId;
    @ApiModelProperty(value = "部门名")
    private String department;
    @ApiModelProperty(value = "手机号")
    private Long phoneNumber;
    @ApiModelProperty(value = "员工编号")
    private String userCode;
    @ApiModelProperty(value = "用户角色")
    private List<Integer> roles;
}
