package com.xlf.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountTypeEnums {
    NORMAL(1),
    RED_PACKET(2),
    GIFT_CARD(3),
    ;

    private final int code;
}
