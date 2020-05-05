package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendanceLeaveTypeEnum {
    PERSONAL_LEAVE(1, "病假"),
    SICK_LEAVE(1, "病假"),
    HOLIDAY(1, "节假"),
    VACATION(1, "休假"),
    ;

    private Integer code;

    private String message;

    AttendanceLeaveTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (AttendanceLeaveTypeEnum anEnum : AttendanceLeaveTypeEnum.values()) {
            if (code.equals(anEnum.code))
                return anEnum.message;
        }
        return null;
    }
}
