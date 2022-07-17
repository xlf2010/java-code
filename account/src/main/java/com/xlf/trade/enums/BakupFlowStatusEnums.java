package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BakupFlowStatusEnums {
    NONE(0),
    BAKUPING(1),
    FINISH(2),
    ;

    private final int code;


    public static BakupFlowStatusEnums parse(int code) {
        for (BakupFlowStatusEnums enums : BakupFlowStatusEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        return null;
    }
}