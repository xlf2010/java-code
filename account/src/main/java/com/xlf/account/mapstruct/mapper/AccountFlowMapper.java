package com.xlf.account.mapstruct.mapper;

import com.xlf.account.common.response.AccountFlowRsp;
import com.xlf.account.entity.AccountFlowBakDo;
import com.xlf.account.entity.AccountFlowDo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountFlowMapper {

    AccountFlowMapper INSTANCE = Mappers.getMapper(AccountFlowMapper.class);

    AccountFlowBakDo toAccountFlowBakDo(AccountFlowDo accountFlowDo);

    AccountFlowRsp toAccountFlowRsp(AccountFlowDo accountFlowDo);

    List<AccountFlowRsp> toAccountFlowRspList(List<AccountFlowDo> accountFlowDos);

}
