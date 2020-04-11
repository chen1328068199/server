package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendanceModeEnum {
    LOCATION(0, "位置考勤"),
    WORD(1, "口令"),
    QRCode(2, "二维码"),
    ;

    private Integer code;

    private String message;

    AttendanceModeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
