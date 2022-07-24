package com.xlf.account.common.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class DeleteAccountReq {
    @NotBlank(message = "appId can't be blank")
    private String appId;
    @NotNull(message = "orderTime can't be blank")
    private Date orderTime;
    @NotBlank(message = "transId can't be blank")
    private String transId;
    @NotBlank(message = "userId can't be blank")
    private String userId;
    @NotNull(message = "accountType can't be null")
    private Integer accountType;

}
