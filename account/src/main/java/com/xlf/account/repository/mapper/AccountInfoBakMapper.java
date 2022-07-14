package com.xlf.account.repository.mapper;

import com.xlf.account.entity.AccountInfoBakDo;
import com.xlf.account.entity.AccountInfoBakDoExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountInfoBakMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    long countByExample(AccountInfoBakDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int deleteByExample(AccountInfoBakDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int insert(AccountInfoBakDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int insertSelective(AccountInfoBakDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    List<AccountInfoBakDo> selectByExample(AccountInfoBakDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    AccountInfoBakDo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int updateByExampleSelective(@Param("row") AccountInfoBakDo row, @Param("example") AccountInfoBakDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int updateByExample(@Param("row") AccountInfoBakDo row, @Param("example") AccountInfoBakDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int updateByPrimaryKeySelective(AccountInfoBakDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info_bak
     *
     * @mbg.generated 2022-07-14
     */
    int updateByPrimaryKey(AccountInfoBakDo row);
}