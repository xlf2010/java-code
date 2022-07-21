package com.xlf.account.common.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class WithdrawReq {
    @NotBlank(message = "appId can't be blank")
    private String appId;
    @NotNull(message = "orderTime can't be blank")
    private Date orderTime;
    @NotBlank(message = "transId can't be blank")
    private String transId;
    @NotBlank(message = "userId can't be blank")
    private String userId;
    /**
     * accountType Enum
     *
     * @see com.xlf.account.enums.AccountTypeEnums
     */
    @NotNull(message = "accountType can't be null")
    private Integer accountType;
    @NotNull(message = "amount can't be null")
    @Min(value = 1, message = "recharge amount must be greater 0")
    private Long amount;

}
