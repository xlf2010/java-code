package com.xlf.account.common.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateAccountReq {
    @NotBlank(message = "appId can't be blank")
    private String appId;
    @NotNull(message = "orderTime can't be blank")
    private Date orderTime;

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
     * @see com.xlf.account.enums.AccountTypeEnums
     */
    @NotNull(message = "accountType can't be null")
    private Integer accountType;
    /**
     * 1-user,2-amdin
     * default user
     *
     * @see com.xlf.account.enums.AccountCreateTypeEnums
     */
    private Integer createType;
    /**
     * userId or admin name
     */
    private String createBy;

    /**
     * currencyType
     *
     * @see com.xlf.account.enums.CurrencyTypeEnums
     */
    @NotBlank(message = "currencyType can't be blank")
    private String currencyType;


}
