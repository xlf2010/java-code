package com.xlf.account.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountFlowBakDoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public AccountFlowBakDoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFlowIdIsNull() {
            addCriterion("flow_id is null");
            return (Criteria) this;
        }

        public Criteria andFlowIdIsNotNull() {
            addCriterion("flow_id is not null");
            return (Criteria) this;
        }

        public Criteria andFlowIdEqualTo(Long value) {
            addCriterion("flow_id =", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotEqualTo(Long value) {
            addCriterion("flow_id <>", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThan(Long value) {
            addCriterion("flow_id >", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdGreaterThanOrEqualTo(Long value) {
            addCriterion("flow_id >=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThan(Long value) {
            addCriterion("flow_id <", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdLessThanOrEqualTo(Long value) {
            addCriterion("flow_id <=", value, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdIn(List<Long> values) {
            addCriterion("flow_id in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotIn(List<Long> values) {
            addCriterion("flow_id not in", values, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdBetween(Long value1, Long value2) {
            addCriterion("flow_id between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andFlowIdNotBetween(Long value1, Long value2) {
            addCriterion("flow_id not between", value1, value2, "flowId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdIsNull() {
            addCriterion("to_account_id is null");
            return (Criteria) this;
        }

        public Criteria andToAccountIdIsNotNull() {
            addCriterion("to_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andToAccountIdEqualTo(Long value) {
            addCriterion("to_account_id =", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdNotEqualTo(Long value) {
            addCriterion("to_account_id <>", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdGreaterThan(Long value) {
            addCriterion("to_account_id >", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("to_account_id >=", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdLessThan(Long value) {
            addCriterion("to_account_id <", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("to_account_id <=", value, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdIn(List<Long> values) {
            addCriterion("to_account_id in", values, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdNotIn(List<Long> values) {
            addCriterion("to_account_id not in", values, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdBetween(Long value1, Long value2) {
            addCriterion("to_account_id between", value1, value2, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andToAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("to_account_id not between", value1, value2, "toAccountId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNull() {
            addCriterion("to_user_id is null");
            return (Criteria) this;
        }

        public Criteria andToUserIdIsNotNull() {
            addCriterion("to_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andToUserIdEqualTo(String value) {
            addCriterion("to_user_id =", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotEqualTo(String value) {
            addCriterion("to_user_id <>", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThan(String value) {
            addCriterion("to_user_id >", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("to_user_id >=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThan(String value) {
            addCriterion("to_user_id <", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLessThanOrEqualTo(String value) {
            addCriterion("to_user_id <=", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdLike(String value) {
            addCriterion("to_user_id like", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotLike(String value) {
            addCriterion("to_user_id not like", value, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdIn(List<String> values) {
            addCriterion("to_user_id in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotIn(List<String> values) {
            addCriterion("to_user_id not in", values, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdBetween(String value1, String value2) {
            addCriterion("to_user_id between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andToUserIdNotBetween(String value1, String value2) {
            addCriterion("to_user_id not between", value1, value2, "toUserId");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(Integer value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(Integer value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(Integer value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(Integer value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<Integer> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<Integer> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeIsNull() {
            addCriterion("to_account_type is null");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeIsNotNull() {
            addCriterion("to_account_type is not null");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeEqualTo(Integer value) {
            addCriterion("to_account_type =", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeNotEqualTo(Integer value) {
            addCriterion("to_account_type <>", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeGreaterThan(Integer value) {
            addCriterion("to_account_type >", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_account_type >=", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeLessThan(Integer value) {
            addCriterion("to_account_type <", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("to_account_type <=", value, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeIn(List<Integer> values) {
            addCriterion("to_account_type in", values, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeNotIn(List<Integer> values) {
            addCriterion("to_account_type not in", values, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeBetween(Integer value1, Integer value2) {
            addCriterion("to_account_type between", value1, value2, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andToAccountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("to_account_type not between", value1, value2, "toAccountType");
            return (Criteria) this;
        }

        public Criteria andTransIdIsNull() {
            addCriterion("trans_id is null");
            return (Criteria) this;
        }

        public Criteria andTransIdIsNotNull() {
            addCriterion("trans_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransIdEqualTo(String value) {
            addCriterion("trans_id =", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdNotEqualTo(String value) {
            addCriterion("trans_id <>", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdGreaterThan(String value) {
            addCriterion("trans_id >", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdGreaterThanOrEqualTo(String value) {
            addCriterion("trans_id >=", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdLessThan(String value) {
            addCriterion("trans_id <", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdLessThanOrEqualTo(String value) {
            addCriterion("trans_id <=", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdLike(String value) {
            addCriterion("trans_id like", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdNotLike(String value) {
            addCriterion("trans_id not like", value, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdIn(List<String> values) {
            addCriterion("trans_id in", values, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdNotIn(List<String> values) {
            addCriterion("trans_id not in", values, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdBetween(String value1, String value2) {
            addCriterion("trans_id between", value1, value2, "transId");
            return (Criteria) this;
        }

        public Criteria andTransIdNotBetween(String value1, String value2) {
            addCriterion("trans_id not between", value1, value2, "transId");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNull() {
            addCriterion("operate_type is null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIsNotNull() {
            addCriterion("operate_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperateTypeEqualTo(Integer value) {
            addCriterion("operate_type =", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotEqualTo(Integer value) {
            addCriterion("operate_type <>", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThan(Integer value) {
            addCriterion("operate_type >", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operate_type >=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThan(Integer value) {
            addCriterion("operate_type <", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operate_type <=", value, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeIn(List<Integer> values) {
            addCriterion("operate_type in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotIn(List<Integer> values) {
            addCriterion("operate_type not in", values, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeBetween(Integer value1, Integer value2) {
            addCriterion("operate_type between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andOperateTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operate_type not between", value1, value2, "operateType");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Long value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Long value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Long value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Long value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Long value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Long value1, Long value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Long value1, Long value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNull() {
            addCriterion("currency_type is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIsNotNull() {
            addCriterion("currency_type is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeEqualTo(String value) {
            addCriterion("currency_type =", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotEqualTo(String value) {
            addCriterion("currency_type <>", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThan(String value) {
            addCriterion("currency_type >", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("currency_type >=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThan(String value) {
            addCriterion("currency_type <", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLessThanOrEqualTo(String value) {
            addCriterion("currency_type <=", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeLike(String value) {
            addCriterion("currency_type like", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotLike(String value) {
            addCriterion("currency_type not like", value, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeIn(List<String> values) {
            addCriterion("currency_type in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotIn(List<String> values) {
            addCriterion("currency_type not in", values, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeBetween(String value1, String value2) {
            addCriterion("currency_type between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andCurrencyTypeNotBetween(String value1, String value2) {
            addCriterion("currency_type not between", value1, value2, "currencyType");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Long value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Long value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Long value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Long value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Long value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Long> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Long> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Long value1, Long value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Long value1, Long value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIsNull() {
            addCriterion("frozen_balance is null");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIsNotNull() {
            addCriterion("frozen_balance is not null");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceEqualTo(Long value) {
            addCriterion("frozen_balance =", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotEqualTo(Long value) {
            addCriterion("frozen_balance <>", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceGreaterThan(Long value) {
            addCriterion("frozen_balance >", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("frozen_balance >=", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceLessThan(Long value) {
            addCriterion("frozen_balance <", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceLessThanOrEqualTo(Long value) {
            addCriterion("frozen_balance <=", value, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceIn(List<Long> values) {
            addCriterion("frozen_balance in", values, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotIn(List<Long> values) {
            addCriterion("frozen_balance not in", values, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceBetween(Long value1, Long value2) {
            addCriterion("frozen_balance between", value1, value2, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andFrozenBalanceNotBetween(Long value1, Long value2) {
            addCriterion("frozen_balance not between", value1, value2, "frozenBalance");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_flow_bak
     *
     * @mbg.generated do_not_delete_during_merge 2022-07-14
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table account_flow_bak
     *
     * @mbg.generated 2022-07-14
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}