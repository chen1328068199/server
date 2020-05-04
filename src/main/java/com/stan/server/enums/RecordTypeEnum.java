package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum RecordTypeEnum {
    LEAVE(0, "请假"),
    BEGIN_WORKING(1, "上班"),
    END_WORKING(2, "下班")
    ;

    private Integer code;

    private String message;

    RecordTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (RecordTypeEnum anEnum : RecordTypeEnum.values()) {
            if (code.equals(anEnum.code))
                return anEnum.message;
        }
        return null;
    }
}
