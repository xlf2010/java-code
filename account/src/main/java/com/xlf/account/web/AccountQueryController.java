package com.xlf.account.web;

import com.xlf.account.common.response.AccountFlowRsp;
import com.xlf.account.common.response.QueryAccountInfoRsp;
import com.xlf.account.service.AccountQueryService;
import com.xlf.common.response.ApiResult;
import com.xlf.common.response.PageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/account/query")
public class AccountQueryController {

    @Resource
    private AccountQueryService accountQueryService;

    @GetMapping("/account_info")
    public ApiResult<List<QueryAccountInfoRsp>> queryAccountInfo(@RequestParam("userId") String userId,
                                                                 @RequestParam(value = "accountType", required = false) Integer accountType) {
        return ApiResult.success(accountQueryService.queryAccountInfo(userId, accountType));
    }

    @GetMapping("/account_flow")
    public ApiResult<PageResponse<AccountFlowRsp>> queryAccountFlow(@RequestParam("userId") String userId,
                                                                    @RequestParam(value = "accountType", required = false) Integer accountType,
                                                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        return ApiResult.success(accountQueryService.queryAccountFlow(userId, accountType, pageNum, pageSize));
    }


    @GetMapping("/account_flow_trans_operate")
    public ApiResult<List<AccountFlowRsp>> queryAccountFlowTransId(@RequestParam("transId") String transId,
                                                                   @RequestParam(value = "operateType") Integer operateType) {
        return ApiResult.success(accountQueryService.queryAccountFlow(transId, operateType));
    }

}
