package com.xlf.account.repository.mapper;

import com.xlf.account.entity.AccountFlowDo;
import com.xlf.account.entity.AccountFlowDoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountFlowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    long countByExample(AccountFlowDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int deleteByExample(AccountFlowDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int deleteByPrimaryKey(Long flowId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int insert(AccountFlowDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int insertSelective(AccountFlowDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    List<AccountFlowDo> selectByExample(AccountFlowDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    AccountFlowDo selectByPrimaryKey(Long flowId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int updateByExampleSelective(@Param("row") AccountFlowDo row, @Param("example") AccountFlowDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int updateByExample(@Param("row") AccountFlowDo row, @Param("example") AccountFlowDoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int updateByPrimaryKeySelective(AccountFlowDo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow
     *
     * @mbg.generated 2022-07-20
     */
    int updateByPrimaryKey(AccountFlowDo row);
}