package com.xlf.account.service;

import com.xlf.account.common.response.AccountFlowRsp;
import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.common.response.PageResponse;

import java.util.List;

public interface AccountQueryService {
    List<QueryAccountInfoRsp> queryAccountInfo(String userId, Integer accountType);

    PageResponse<AccountFlowRsp> queryAccountFlow(String userId, Integer accountType, Integer pageNum, Integer pageSize);

    List<AccountFlowRsp> queryAccountFlow(String transId, Integer operationType);
}
