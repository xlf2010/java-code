package com.xlf.account.repository;

import com.xlf.account.entity.AccountFlowBakDo;
import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountFlowDoExample;
import com.xlf.account.repository.mapper.AccountFlowBakMapper;
import com.xlf.account.repository.mapper.AccountFlowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class AccountFlowRepository {
    @Resource
    private AccountFlowMapper accountFlowMapper;
    @Resource
    private AccountFlowBakMapper accountFlowBakMapper;

    public void insert(AccountFlowDo accountFlowDo) {
        accountFlowMapper.insertSelective(accountFlowDo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertBatch(List<AccountFlowDo> accountFlowDos) {
        for (AccountFlowDo accountFlowDo : accountFlowDos) {
            accountFlowMapper.insertSelective(accountFlowDo);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void moveAccountFlowToBak(List<AccountFlowDo> accountFlowDos, List<AccountFlowBakDo> accountFlowBakDos) {
        for (AccountFlowBakDo accountFlowBakDo : accountFlowBakDos) {
            accountFlowBakMapper.insertSelective(accountFlowBakDo);
        }
        AccountFlowDoExample example = new AccountFlowDoExample();
        example.createCriteria().andFlowIdIn(accountFlowDos.stream().map(AccountFlowDo::getFlowId).collect(Collectors.toList()));
        accountFlowMapper.deleteByExample(example);
    }

    public long count(String userId, Integer accountType) {
        AccountFlowDoExample example = new AccountFlowDoExample();
        AccountFlowDoExample.Criteria criteria = example.createCriteria()
                .andUserIdEqualTo(userId);
        if (Objects.nonNull(accountType)) {
            criteria.andAccountTypeEqualTo(accountType);
        }
        return accountFlowMapper.countByExample(example);
    }

    public List<AccountFlowDo> queryByUseridAccountType(String userId, Integer accountType, Integer offset, Integer pageSize) {
        AccountFlowDoExample example = new AccountFlowDoExample();
        AccountFlowDoExample.Criteria criteria = example.createCriteria()
                .andUserIdEqualTo(userId);
        if (Objects.nonNull(accountType)) {
            criteria.andAccountTypeEqualTo(accountType);
        }
        example.setOrderByClause(" flow_id asc limit " + offset + "," + pageSize);
        return accountFlowMapper.selectByExample(example);
    }

    public List<AccountFlowDo> queryByTransIdOperateType(String transId, Integer operateType) {
        AccountFlowDoExample example = new AccountFlowDoExample();
        example.createCriteria()
                .andTransIdEqualTo(transId)
                .andOperateTypeEqualTo(operateType);
        return accountFlowMapper.selectByExample(example);
    }


    public List<AccountFlowDo> queryByAccountIdPage(Long accountId, Long startId, Integer limit) {
        AccountFlowDoExample example = new AccountFlowDoExample();
        example.createCriteria()
                .andAccountIdEqualTo(accountId)
                .andFlowIdGreaterThan(startId);
        example.setOrderByClause(" flow_id asc limit " + limit);
        return accountFlowMapper.selectByExample(example);
    }

}
