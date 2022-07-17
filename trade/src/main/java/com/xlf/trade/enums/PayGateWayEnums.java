package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayGateWayEnums {
    WECHAT_PAY(1),
    ALI_PAY(2),
    PAYPAL(3),
    AMAZON(4),
    ;

    private final int code;

    public static PayGateWayEnums parse(int code) {
        for (PayGateWayEnums enums : PayGateWayEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}
