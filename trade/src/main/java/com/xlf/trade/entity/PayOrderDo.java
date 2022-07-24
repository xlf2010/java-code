package com.xlf.trade.entity;

import java.util.Date;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table pay_order
 */
public class PayOrderDo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.order_id
     *
     * @mbg.generated 2022-07-20
     */
    private Long orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.user_id
     *
     * @mbg.generated 2022-07-20
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.to_user_id
     *
     * @mbg.generated 2022-07-20
     */
    private String toUserId;

    /**
     * Database Column Remarks:
     * transation id
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.trans_id
     *
     * @mbg.generated 2022-07-20
     */
    private String transId;

    /**
     * Database Column Remarks:
     * 1-wechat,2-alipay,3-paypal,4-amazon
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.pay_channel
     *
     * @mbg.generated 2022-07-20
     */
    private Integer payChannel;

    /**
     * Database Column Remarks:
     * 1-create trade,2-recharge,3-withdraw,4-transaction,5-frozen,6-unfrozen
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.operate_type
     *
     * @mbg.generated 2022-07-20
     */
    private Integer operateType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.amount
     *
     * @mbg.generated 2022-07-20
     */
    private Long amount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.currency_type
     *
     * @mbg.generated 2022-07-20
     */
    private String currencyType;

    /**
     * Database Column Remarks:
     * 1-init,10-calling pay channel, 11-call pay channel success, 20-calling account ,21-call account success
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.status
     *
     * @mbg.generated 2022-07-20
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.request_url
     *
     * @mbg.generated 2022-07-20
     */
    private String requestUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.request_param
     *
     * @mbg.generated 2022-07-20
     */
    private String requestParam;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.response
     *
     * @mbg.generated 2022-07-20
     */
    private String response;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.notify
     *
     * @mbg.generated 2022-07-20
     */
    private String notify;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.remark
     *
     * @mbg.generated 2022-07-20
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.create_by
     *
     * @mbg.generated 2022-07-20
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.create_time
     *
     * @mbg.generated 2022-07-20
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.update_time
     *
     * @mbg.generated 2022-07-20
     */
    private Date updateTime;

    /**
     * Database Column Remarks:
     * order time for bill check
     * <p>
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pay_order.order_time
     *
     * @mbg.generated 2022-07-20
     */
    private Date orderTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.order_id
     *
     * @return the value of pay_order.order_id
     * @mbg.generated 2022-07-20
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.order_id
     *
     * @param orderId the value for pay_order.order_id
     * @mbg.generated 2022-07-20
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.user_id
     *
     * @return the value of pay_order.user_id
     * @mbg.generated 2022-07-20
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.user_id
     *
     * @param userId the value for pay_order.user_id
     * @mbg.generated 2022-07-20
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.to_user_id
     *
     * @return the value of pay_order.to_user_id
     * @mbg.generated 2022-07-20
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.to_user_id
     *
     * @param toUserId the value for pay_order.to_user_id
     * @mbg.generated 2022-07-20
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.trans_id
     *
     * @return the value of pay_order.trans_id
     * @mbg.generated 2022-07-20
     */
    public String getTransId() {
        return transId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.trans_id
     *
     * @param transId the value for pay_order.trans_id
     * @mbg.generated 2022-07-20
     */
    public void setTransId(String transId) {
        this.transId = transId == null ? null : transId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.pay_channel
     *
     * @return the value of pay_order.pay_channel
     * @mbg.generated 2022-07-20
     */
    public Integer getPayChannel() {
        return payChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.pay_channel
     *
     * @param payChannel the value for pay_order.pay_channel
     * @mbg.generated 2022-07-20
     */
    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.operate_type
     *
     * @return the value of pay_order.operate_type
     * @mbg.generated 2022-07-20
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.operate_type
     *
     * @param operateType the value for pay_order.operate_type
     * @mbg.generated 2022-07-20
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.amount
     *
     * @return the value of pay_order.amount
     * @mbg.generated 2022-07-20
     */
    public Long getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.amount
     *
     * @param amount the value for pay_order.amount
     * @mbg.generated 2022-07-20
     */
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.currency_type
     *
     * @return the value of pay_order.currency_type
     * @mbg.generated 2022-07-20
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.currency_type
     *
     * @param currencyType the value for pay_order.currency_type
     * @mbg.generated 2022-07-20
     */
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.status
     *
     * @return the value of pay_order.status
     * @mbg.generated 2022-07-20
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.status
     *
     * @param status the value for pay_order.status
     * @mbg.generated 2022-07-20
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.request_url
     *
     * @return the value of pay_order.request_url
     * @mbg.generated 2022-07-20
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.request_url
     *
     * @param requestUrl the value for pay_order.request_url
     * @mbg.generated 2022-07-20
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.request_param
     *
     * @return the value of pay_order.request_param
     * @mbg.generated 2022-07-20
     */
    public String getRequestParam() {
        return requestParam;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.request_param
     *
     * @param requestParam the value for pay_order.request_param
     * @mbg.generated 2022-07-20
     */
    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam == null ? null : requestParam.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.response
     *
     * @return the value of pay_order.response
     * @mbg.generated 2022-07-20
     */
    public String getResponse() {
        return response;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.response
     *
     * @param response the value for pay_order.response
     * @mbg.generated 2022-07-20
     */
    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.notify
     *
     * @return the value of pay_order.notify
     * @mbg.generated 2022-07-20
     */
    public String getNotify() {
        return notify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.notify
     *
     * @param notify the value for pay_order.notify
     * @mbg.generated 2022-07-20
     */
    public void setNotify(String notify) {
        this.notify = notify == null ? null : notify.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.remark
     *
     * @return the value of pay_order.remark
     * @mbg.generated 2022-07-20
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.remark
     *
     * @param remark the value for pay_order.remark
     * @mbg.generated 2022-07-20
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.create_by
     *
     * @return the value of pay_order.create_by
     * @mbg.generated 2022-07-20
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.create_by
     *
     * @param createBy the value for pay_order.create_by
     * @mbg.generated 2022-07-20
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.create_time
     *
     * @return the value of pay_order.create_time
     * @mbg.generated 2022-07-20
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.create_time
     *
     * @param createTime the value for pay_order.create_time
     * @mbg.generated 2022-07-20
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.update_time
     *
     * @return the value of pay_order.update_time
     * @mbg.generated 2022-07-20
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.update_time
     *
     * @param updateTime the value for pay_order.update_time
     * @mbg.generated 2022-07-20
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pay_order.order_time
     *
     * @return the value of pay_order.order_time
     * @mbg.generated 2022-07-20
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pay_order.order_time
     *
     * @param orderTime the value for pay_order.order_time
     * @mbg.generated 2022-07-20
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_order
     *
     * @mbg.generated 2022-07-20
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayOrderDo other = (PayOrderDo) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getToUserId() == null ? other.getToUserId() == null : this.getToUserId().equals(other.getToUserId()))
                && (this.getTransId() == null ? other.getTransId() == null : this.getTransId().equals(other.getTransId()))
                && (this.getPayChannel() == null ? other.getPayChannel() == null : this.getPayChannel().equals(other.getPayChannel()))
                && (this.getOperateType() == null ? other.getOperateType() == null : this.getOperateType().equals(other.getOperateType()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getCurrencyType() == null ? other.getCurrencyType() == null : this.getCurrencyType().equals(other.getCurrencyType()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
                && (this.getRequestParam() == null ? other.getRequestParam() == null : this.getRequestParam().equals(other.getRequestParam()))
                && (this.getResponse() == null ? other.getResponse() == null : this.getResponse().equals(other.getResponse()))
                && (this.getNotify() == null ? other.getNotify() == null : this.getNotify().equals(other.getNotify()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getOrderTime() == null ? other.getOrderTime() == null : this.getOrderTime().equals(other.getOrderTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_order
     *
     * @mbg.generated 2022-07-20
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getToUserId() == null) ? 0 : getToUserId().hashCode());
        result = prime * result + ((getTransId() == null) ? 0 : getTransId().hashCode());
        result = prime * result + ((getPayChannel() == null) ? 0 : getPayChannel().hashCode());
        result = prime * result + ((getOperateType() == null) ? 0 : getOperateType().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCurrencyType() == null) ? 0 : getCurrencyType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getRequestParam() == null) ? 0 : getRequestParam().hashCode());
        result = prime * result + ((getResponse() == null) ? 0 : getResponse().hashCode());
        result = prime * result + ((getNotify() == null) ? 0 : getNotify().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getOrderTime() == null) ? 0 : getOrderTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pay_order
     *
     * @mbg.generated 2022-07-20
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", toUserId=").append(toUserId);
        sb.append(", transId=").append(transId);
        sb.append(", payChannel=").append(payChannel);
        sb.append(", operateType=").append(operateType);
        sb.append(", amount=").append(amount);
        sb.append(", currencyType=").append(currencyType);
        sb.append(", status=").append(status);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", requestParam=").append(requestParam);
        sb.append(", response=").append(response);
        sb.append(", notify=").append(notify);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", orderTime=").append(orderTime);
        sb.append("]");
        return sb.toString();
    }
}