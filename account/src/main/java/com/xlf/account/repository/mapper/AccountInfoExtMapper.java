package com.xlf.account.repository.mapper;

import com.xlf.account.entity.AccountInfoDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountInfoExtMapper {

    AccountInfoDo queryAccountInfoForUpdate(@Param("userId") String userId, @Param("accountType") Integer accountType);

    int incrBalance(@Param("accountId") Long accountId, @Param("amount") Long amount);
}