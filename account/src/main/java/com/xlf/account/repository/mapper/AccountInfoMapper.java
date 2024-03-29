package com.xlf.account.repository.mapper;

import com.xlf.account.entity.AccountInfoDo;
import com.xlf.account.entity.AccountInfoDoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    long countByExample(AccountInfoDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int deleteByExample(AccountInfoDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int insert(AccountInfoDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int insertSelective(AccountInfoDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    List<AccountInfoDo> selectByExample(AccountInfoDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    AccountInfoDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int updateByExampleSelective(@Param("row") AccountInfoDo row, @Param("example") AccountInfoDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int updateByExample(@Param("row") AccountInfoDo row, @Param("example") AccountInfoDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int updateByPrimaryKeySelective(AccountInfoDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbg.generated 2022-07-20
     */
    int updateByPrimaryKey(AccountInfoDo row);
}