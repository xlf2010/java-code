package com.xlf.account.repository;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountFlowDoExample;
import com.xlf.account.repository.mapper.AccountFlowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class AccountFlowRepository {
    @Resource
    private AccountFlowMapper accountFlowMapper;

    public void insert(AccountFlowDo accountFlowDo) {
        accountFlowMapper.insertSelective(accountFlowDo);
    }

    public void insertBatch(List<AccountFlowDo> accountFlowDos) {
        for (AccountFlowDo accountFlowDo : accountFlowDos) {
            accountFlowMapper.insertSelective(accountFlowDo);
        }
    }

    public List<AccountFlowDo> queryByTransIdOperateType(String transId, Integer operateType) {
        AccountFlowDoExample example = new AccountFlowDoExample();
        example.createCriteria()
                .andTransIdEqualTo(transId)
                .andOperateTypeEqualTo(operateType);
        return accountFlowMapper.selectByExample(example);
    }

}
