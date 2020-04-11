package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum AttendanceSituationEnum {
    ABSENCE(0, "缺勤"),
    NORMAL(1, "正常"),
    OVERTIME(2, "加班"),
    LATE(3, "迟到"),
    EARLY(4, "早退"),
    LEAVE(5, "请假"),
    SEVERANCE(6, "离职"),
    ;

    private Integer code;

    private String message;

    AttendanceSituationEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
