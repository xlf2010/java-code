package com.xlf.trade.service;

import com.xlf.trade.vo.response.AccountFlowRsp;
import com.xlf.trade.vo.response.QueryAccountInfoRsp;
import com.xlf.common.response.PageResponse;

import java.util.List;

public interface AccountQueryService {
    List<QueryAccountInfoRsp> queryAccountInfo(String userId, Integer accountType);

    PageResponse<AccountFlowRsp> queryAccountFlow(String userId, Integer accountType, Integer pageNum, Integer pageSize);

    List<AccountFlowRsp> queryAccountFlow(String transId, Integer operationType);
}
