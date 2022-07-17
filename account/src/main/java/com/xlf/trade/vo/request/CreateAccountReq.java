package com.xlf.trade.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccountReq {
    @NotBlank(message = "transId can't be blank")
    private String transId;

    @NotBlank(message = "userId can't be blank")
    private String userId;
    @NotBlank(message = "userName can't be blank")
    private String userName;
    @NotBlank(message = "accountName can't be blank")
    private String accountName;
    /**
     * accountType Enum
     *
     * @see com.xlf.trade.enums.AccountTypeEnums
     */
    @NotNull(message = "accountType can't be null")
    private Integer accountType;
    /**
     * 1-user,2-amdin
     * default user
     *
     * @see com.xlf.trade.enums.AccountCreateTypeEnums
     */
    private Integer createType;
    /**
     * userId or admin name
     */
    private String createBy;

    /**
     * currencyType
     *
     * @see com.xlf.trade.enums.CurrencyTypeEnums
     */
    @NotBlank(message = "currencyType can't be blank")
    private String currencyType;


}
