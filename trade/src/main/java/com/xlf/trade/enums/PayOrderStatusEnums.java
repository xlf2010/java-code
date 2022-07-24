package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 1-init, 10-calling pay channel, 11-call pay channel fail, 20-calling account , 21-call account channel fail,200-success
 */
@Getter
@AllArgsConstructor
public enum PayOrderStatusEnums {

    INIT(1),

    CALLING_PAY_CHANNEL(10),

    CALL_PAY_CHANNEL_FAIL(11),

    CALLING_ACCOUNT(20),

    CALLING_ACCOUNT_FAIL(21),

    SUCCESS(200),
    ;

    private final int code;

    public static PayOrderStatusEnums parse(int code) {
        for (PayOrderStatusEnums enums : PayOrderStatusEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}