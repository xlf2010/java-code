package com.xlf.trade.service.impl;

import com.xlf.trade.entity.AccountFlowDo;
import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.mapstruct.mapper.AccountFlowMapper;
import com.xlf.trade.mapstruct.mapper.AccountInfoMapper;
import com.xlf.trade.repository.AccountFlowRepository;
import com.xlf.trade.repository.AccountInfoRepository;
import com.xlf.trade.service.AccountQueryService;
import com.xlf.trade.vo.response.AccountFlowRsp;
import com.xlf.trade.vo.response.QueryAccountInfoRsp;
import com.xlf.common.response.PageResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    @Resource
    private AccountInfoRepository accountInfoRepository;
    @Resource
    private AccountFlowRepository accountFlowRepository;

    @Override
    public List<QueryAccountInfoRsp> queryAccountInfo(String userId, Integer accountType) {
        List<AccountInfoDo> accountInfoDos = accountInfoRepository.queryByUserIdAccountType(userId, accountType);
        if (CollectionUtils.isEmpty(accountInfoDos)) {
            return new ArrayList<>();
        }
        return AccountInfoMapper.INSTANCE.toQueryAccountInfoRspList(accountInfoDos);
    }

    @Override
    public PageResponse<AccountFlowRsp> queryAccountFlow(String userId, Integer accountType, Integer pageNum, Integer pageSize) {
        long total = accountFlowRepository.count(userId, accountType);
        if (total <= 0) {
            return PageResponse.empty();
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
        Integer offset = (pageNum - 1) * pageSize;
        List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByUseridAccountType(userId, accountType, offset, pageSize);
        if (CollectionUtils.isEmpty(accountFlowDos)) {
            return PageResponse.empty();
        }
        return new PageResponse<>(pageNum, pageSize, total, AccountFlowMapper.INSTANCE.toAccountFlowRspList(accountFlowDos));
    }

    @Override
    public List<AccountFlowRsp> queryAccountFlow(String transId, Integer operationType) {
        List<AccountFlowDo> accountFlowDos = accountFlowRepository.queryByTransIdOperateType(transId, operationType);
        if (CollectionUtils.isEmpty(accountFlowDos)) {
            return new ArrayList<>();
        }
        return AccountFlowMapper.INSTANCE.toAccountFlowRspList(accountFlowDos);
    }
}
