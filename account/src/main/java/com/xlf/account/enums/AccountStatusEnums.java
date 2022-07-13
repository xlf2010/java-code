package com.xlf.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatusEnums {
    INVALID(0),
    VALID(1),
    ;

    private final int code;


    public static AccountStatusEnums parse(int code) {
        for (AccountStatusEnums enums : AccountStatusEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}