package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendancePurposeEnum {
    BEGIN_WORKING(0, "上班打卡"),
    END_WORKING(1, "下班打卡"),
    ;

    private Integer code;

    private String message;

    AttendancePurposeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
