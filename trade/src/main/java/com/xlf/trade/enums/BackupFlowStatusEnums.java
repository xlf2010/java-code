package com.xlf.trade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BackupFlowStatusEnums {
    NONE(0),
    BACKUPING(1),
    FINISH(2),
    ;

    private final int code;


    public static BackupFlowStatusEnums parse(int code) {
        for (BackupFlowStatusEnums enums : BackupFlowStatusEnums.values()) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        return null;
    }
}