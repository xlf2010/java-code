package com.xlf.trade.mapstruct.mapper;

import com.xlf.trade.vo.response.QueryAccountInfoRsp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountInfoMapper {

    AccountInfoMapper INSTANCE = Mappers.getMapper(AccountInfoMapper.class);


}
