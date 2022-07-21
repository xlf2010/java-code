package com.xlf.trade.vo.request;

import com.xlf.common.enums.PayChannelEnums;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateTradeAccountReq {

    @NotBlank(message = "transId can't be blank")
    private String transId;
    @NotBlank(message = "userId can't be blank")
    private String userId;
    @NotBlank(message = "userName can't be blank")
    private String userName;
    @NotBlank(message = "accountName can't be blank")
    private String accountName;
    /**
     * payChannel Enum
     *
     * @see PayChannelEnums
     */
    @NotNull(message = "payChannel can't be null")
    private Integer payChannel;

    /**
     * currencyType, use PayChannelEnums if null
     *
     * @see com.xlf.trade.enums.CurrencyTypeEnums
     */
    private String currencyType;


}
