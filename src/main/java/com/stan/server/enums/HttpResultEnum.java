package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum HttpResultEnum {
    SUCCESS(200, "成功"),

    FAILURE(400, "失败"),

//    SIGN_IN_FAILURE_CODE(211, "口令不对或二维码过期"),
//
//    SIGN_IN_FAILURE_DISTANCE(212, "距离不对"),
//
//    SIGN_IN_FAILURE_BE_LATE(213, "迟到"),
//
//    SIGN_IN_FAILURE_LEAVE_EARLY(214, "早退"),

    PARAM_ERROR(300, "参数不正确"),

    LOGIN_FAIL(400, "登录失败, 登录信息不正确"),

    SYSTEM_ERROR(500, "系统错误")
    ;

    private Integer code;

    private String message;

    HttpResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
