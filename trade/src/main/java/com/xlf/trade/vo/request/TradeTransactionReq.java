package com.xlf.trade.vo.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TradeTransactionReq extends BaseReq {

    @NotBlank(message = "toUserId can't be blank")
    private String toUserId;

    @NotNull(message = "amount can't be null")
    @Min(value = 1, message = "recharge amount must be greater 0")
    private Long amount;

}
