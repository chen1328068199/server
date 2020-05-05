package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum ApprovalTypeEnum {
    FILL(0, "补卡"),
    LEAVE(1, "请假"),
    ;

    private Integer code;

    private String message;

    ApprovalTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (ApprovalTypeEnum anEnum : ApprovalTypeEnum.values()) {
            if (code.equals(anEnum.code))
                return anEnum.message;
        }
        return null;
    }
}
