package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendanceStatusEnum {
    ABSENCE(0, "缺勤"),
    NORMAL(1, "正常"),
    OVERTIME(2, "加班"),
    LATE(3, "迟到"),
    EARLY(4, "早退"),
    LEAVE(5, "请假"),
    BEGIN_WORKING(6, "上班打卡"),
    END_WORKING(7, "下班打卡"),
    NOT_BEGIN(8, "上班未打卡"),
    NOT_END(9, "下班未打卡"),
    SEVERANCE(10, "离职"),
    ;

    private Integer code;

    private String message;

    AttendanceStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (AttendanceStatusEnum anEnum : AttendanceStatusEnum.values()) {
            if (code.equals(anEnum.code))
                return anEnum.message;
        }
        return null;
    }
}
