package com.xlf.trade.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateTradeAccountReq extends BaseReq {

    @NotBlank(message = "userName can't be blank")
    private String userName;
    @NotBlank(message = "accountName can't be blank")
    private String accountName;

    /**
     * currencyType, use PayChannelEnums if null
     *
     * @see com.xlf.trade.enums.CurrencyTypeEnums
     */
    private String currencyType;


}
