package com.lj.db.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单扩展类
 */
public class TOrderInfoPo implements Serializable{

    private static final long serialVersionUID = 6738811756601424890L;
    private Long id;
    /**
     *订单号
     */
    private String saleOrderNo;
    /**
     *购买用户id
     */
    private Long customerId;

    private String customerName;
    /**
     *订单的商品总数
     */
    private Integer totalGoodsNum;
    /**
     *订单总价
     */
    private BigDecimal totalPrice;
    /**
     *订单状态
     */
    private String status;
    /**
     * 交易流水号
     */
    private String tradeId;
    /**
     * 预支付交易会话标识
     */
    private String prepayId;
    /**
     *历史收款人手机号
     */
    private String histAddressCellphone;
    /**
     *历史收款人姓名
     */
    private String histAddressName;
    /**
     *历史收款人详细地址
     */
    private String histAddressDetail;
    /**
     *备注
     */
    private String remark;

    /**
     *商品id
     */
    private Long goodsNo;
    /**
     *商品数量
     */
    private Integer goodsNum;
    private String goodsColor;
    private String goodsSize;
    /**
     *历史商品价格
     */
    private BigDecimal histGoodsPrice;
    /**
     *历史商品名称
     */
    private String histGoodsName;
    /**
     *历史商品主图
     */
    private String histGoodsImg;

    private String searchTxt;

    /**
     *删除标识：0 未删除 1 已删除
     */
    private String deleted;
    /**
     *创建人
     */
    private String createdBy;
    /**
     *创建时间
     */
    private String createdAt;
    /**
     *更新人
     */
    private String updatedBy;
    /**
     *更新时间
     */
    private String updatedAt;
    // 成交开始时间
    private String dealBeginTime;
    // 成交结束时间
    private String dealEndTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleOrderNo() {
        return saleOrderNo;
    }

    public void setSaleOrderNo(String saleOrderNo) {
        this.saleOrderNo = saleOrderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getTotalGoodsNum() {
        return totalGoodsNum;
    }

    public void setTotalGoodsNum(Integer totalGoodsNum) {
        this.totalGoodsNum = totalGoodsNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHistAddressCellphone() {
        return histAddressCellphone;
    }

    public void setHistAddressCellphone(String histAddressCellphone) {
        this.histAddressCellphone = histAddressCellphone;
    }

    public String getHistAddressName() {
        return histAddressName;
    }

    public void setHistAddressName(String histAddressName) {
        this.histAddressName = histAddressName;
    }

    public String getHistAddressDetail() {
        return histAddressDetail;
    }

    public void setHistAddressDetail(String histAddressDetail) {
        this.histAddressDetail = histAddressDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getHistGoodsPrice() {
        return histGoodsPrice;
    }

    public void setHistGoodsPrice(BigDecimal histGoodsPrice) {
        this.histGoodsPrice = histGoodsPrice;
    }

    public String getHistGoodsName() {
        return histGoodsName;
    }

    public void setHistGoodsName(String histGoodsName) {
        this.histGoodsName = histGoodsName;
    }

    public String getHistGoodsImg() {
        return histGoodsImg;
    }

    public void setHistGoodsImg(String histGoodsImg) {
        this.histGoodsImg = histGoodsImg;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDealBeginTime() {
        return dealBeginTime;
    }

    public void setDealBeginTime(String dealBeginTime) {
        this.dealBeginTime = dealBeginTime;
    }

    public String getDealEndTime() {
        return dealEndTime;
    }

    public void setDealEndTime(String dealEndTime) {
        this.dealEndTime = dealEndTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }
}
