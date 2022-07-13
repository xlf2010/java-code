package com.xlf.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountCreateTypeEnums {
    USER(1),
    ADMIN(2),
    ;

    private final int code;


    public static AccountCreateTypeEnums parse(int code) {
        for (AccountCreateTypeEnums enums : AccountCreateTypeEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}