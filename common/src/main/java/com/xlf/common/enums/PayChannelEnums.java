package com.xlf.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PayChannelEnums {
    WECHAT_PAY(1, new CurrencyTypeEnums[]{CurrencyTypeEnums.CNY}, AccountTypeEnums.WECHAT_PAY),
    ALI_PAY(2, new CurrencyTypeEnums[]{CurrencyTypeEnums.CNY}, AccountTypeEnums.ALI_PAY),
    PAYPAL(3, new CurrencyTypeEnums[]{CurrencyTypeEnums.USD}, AccountTypeEnums.PAYPAL),
    AMAZON(4, new CurrencyTypeEnums[]{CurrencyTypeEnums.USD}, AccountTypeEnums.AMAZON),
    ;

    private final int code;
    private final CurrencyTypeEnums[] currencyTypeEnums;
    private final AccountTypeEnums accountTypeEnums;

    public static PayChannelEnums parse(int code) {
        for (PayChannelEnums enums : PayChannelEnums.values()) {
            if (enums.code == code) {
                return enums;
            }
        }
        return null;
    }
}
