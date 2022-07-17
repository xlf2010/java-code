package com.xlf.trade.mapstruct.mapper;

import com.xlf.trade.entity.AccountInfoBakDo;
import com.xlf.trade.entity.AccountInfoDo;
import com.xlf.trade.vo.response.QueryAccountInfoRsp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountInfoMapper {

    AccountInfoMapper INSTANCE = Mappers.getMapper(AccountInfoMapper.class);

    AccountInfoBakDo toAccountInfoBakDo(AccountInfoDo accountInfoDo);

    QueryAccountInfoRsp toQueryAccountInfoRsp(AccountInfoDo accountInfoDo);

    List<QueryAccountInfoRsp> toQueryAccountInfoRspList(List<AccountInfoDo> accountInfoDos);

}
