package com.xlf.account.mapper;

import com.xlf.account.entity.AccountInfoBakDo;
import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.vo.response.QueryAccountInfoRsp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountInfoMapper {

    AccountInfoMapper INSTANCE = Mappers.getMapper(AccountInfoMapper.class);

    AccountInfoBakDo toAccountInfoBakDo(AccountInfoDo accountInfoDo);

    QueryAccountInfoRsp toQueryAccountInfoRsp(AccountInfoDo accountInfoDo);

    List<QueryAccountInfoRsp> toQueryAccountInfoRspList(List<AccountInfoDo> accountInfoDos);

}
