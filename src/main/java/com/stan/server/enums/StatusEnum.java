package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    INVALID(0, "失效"),
    NORMAL(1, "正常"),
    ;

    private Integer code;

    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
