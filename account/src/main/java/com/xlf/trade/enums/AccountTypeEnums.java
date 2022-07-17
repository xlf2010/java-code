package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypeEnums {
    WECHAT_PAY(1),
    ALI_PAY(2),
    PAYPAL(3),
    AMAZON(4),
    RED_PACKET(50),
    GIFT_CARD(51),
    ;

    private final int code;

    public static AccountTypeEnums parse(int code) {
        for (AccountTypeEnums enums : AccountTypeEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}
