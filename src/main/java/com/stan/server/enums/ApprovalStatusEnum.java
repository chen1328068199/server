package com.stan.server.enums;

import lombok.Getter;

@Getter
public enum ApprovalStatusEnum {
    PENDING(0, "待审批"),
    PROCESSING(1, "审批中"),
    END(2, "审批结束"),
    CANCELED(-1, "被取消"),
    ;

    private Integer code;

    private String message;

    ApprovalStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(Integer code) {
        for (ApprovalStatusEnum anEnum : ApprovalStatusEnum.values()) {
            if (code.equals(anEnum.code))
                return anEnum.message;
        }
        return null;
    }
}
