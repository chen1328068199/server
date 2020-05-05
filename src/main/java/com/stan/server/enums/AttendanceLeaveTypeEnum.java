package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendanceLeaveTypeEnum {
    PERSONAL_LEAVE(0, "事假"),
    SICK_LEAVE(1, "病假"),
    VACATION(2, "休假"),
    HOLIDAY(3, "节假"),
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
