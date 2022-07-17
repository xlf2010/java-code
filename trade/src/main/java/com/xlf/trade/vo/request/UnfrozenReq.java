package com.xlf.trade.vo.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UnfrozenReq {
    @NotBlank(message = "transId can't be blank")
    private String transId;
    private String frozenTransId;
    @NotBlank(message = "userId can't be blank")
    private String userId;
    @NotNull(message = "accountType can't be null")
    private Integer accountType;
    @NotNull(message = "amount can't be null")
    @Min(value = 1, message = "recharge amount must be greater 0")
    private Long amount;

}
