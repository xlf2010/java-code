package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayOrderOperateTypeEnums {
    CREATE_ACCOUNT(1),
    RECHARGE(2),
    WITHDRAW(3),
    TRANSACTION_OUT(4),
    TRANSACTION_IN(5),
    FROZEN(6),
    UNFROZEN(7),
    DELETE_ACCOUNT(8),
    ;

    private final int code;


    public static PayOrderOperateTypeEnums parse(int code) {
        for (PayOrderOperateTypeEnums enums : PayOrderOperateTypeEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}