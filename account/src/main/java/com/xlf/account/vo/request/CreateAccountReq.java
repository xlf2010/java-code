package com.xlf.account.vo.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateAccountReq {
    @NotBlank(message = "userId can't be blank")
    private String userId;
    @NotBlank(message = "userName can't be blank")
    private String userName;
    @NotBlank(message = "accountName can't be blank")
    private String accountName;
    /**
     * accountType Enum com.xlf.account.enums.AccountTypeEnums
     */
    @NotNull(message = "accountType can't be null")
    private Integer accountType;
    /**
     * 1-user,2-amdin
     * default user
     * com.xlf.account.enums.AccountCreateTypeEnums
     */
    private Integer createType;
    /**
     * userId or admin name
     */
    private String createBy;

}
