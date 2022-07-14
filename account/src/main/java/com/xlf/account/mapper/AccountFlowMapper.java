package com.xlf.account.mapper;

import com.xlf.account.entity.AccountFlowBakDo;
import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.vo.response.AccountFlowRsp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountFlowMapper {

    AccountFlowMapper INSTANCE = Mappers.getMapper(AccountFlowMapper.class);

    AccountFlowBakDo toAccountFlowBakDo(AccountFlowDo accountFlowDo);

    AccountFlowRsp toAccountFlowRsp(AccountFlowDo accountFlowDo);

    List<AccountFlowRsp> toAccountFlowRspList(List<AccountFlowDo> accountFlowDos);

}
