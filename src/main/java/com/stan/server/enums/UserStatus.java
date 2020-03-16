package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum UserStatus {
    DISMISSION(0, "离职"),

    NORMAL(1, "正常"),

    VACATE(2, "休假")
    ;

    private Integer code;

    private String message;

    UserStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
