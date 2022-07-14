package com.xlf.account.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class AccountFlowRsp {

    private Long flowId;

    private Long accountId;

    private Long toAccountId;

    private String userId;

    private String toUserId;

    private Integer accountType;

    private Integer toAccountType;

    private String transId;

    private Integer operateType;

    private Long amount;

    private String currencyType;

    private Long balance;

    private Long frozenBalance;

    private Date createTime;

}