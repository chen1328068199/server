package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    SUCCESS(200, "成功"),

    PARAM_ERROR(300, "参数不正确"),

    LOGIN_FAIL(400, "登录失败, 登录信息不正确"),

    SYSTEM_ERROR(500, "系统错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
