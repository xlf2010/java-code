package com.xlf.account.vo.response;

import lombok.Data;

import java.util.Date;

@Data
public class QueryAccountInfoRsp {

    private Long id;

    private String userId;

    private String userName;

    private String accountName;

    private Integer accountType;

    private Long balance;

    private Long frozenBalance;

    private String currencyType;

    private Integer createType;

    private String createBy;

    private Date createTime;

    private Date updateTime;

}