package com.xlf.trade.mapstruct.mapper;

import com.xlf.trade.entity.AccountFlowBakDo;
import com.xlf.trade.entity.AccountFlowDo;
import com.xlf.trade.vo.response.AccountFlowRsp;
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
