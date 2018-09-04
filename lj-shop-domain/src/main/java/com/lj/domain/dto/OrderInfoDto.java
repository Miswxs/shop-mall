package com.lj.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderInfoDto implements Serializable{
    private static final long serialVersionUID = -1403585419441075059L;
    /**
     *
     */
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

    private String createdAt;
    private String updatedAt;

    private String searchTxt;

    private List<OrderGoodsDto> goodsList;

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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<OrderGoodsDto> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoodsDto> goodsList) {
        this.goodsList = goodsList;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "OrderInfoDto{" +
                "id=" + id +
                ", saleOrderNo='" + saleOrderNo + '\'' +
                ", customerId=" + customerId +
                ", totalGoodsNum=" + totalGoodsNum +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", histAddressCellphone='" + histAddressCellphone + '\'' +
                ", histAddressName='" + histAddressName + '\'' +
                ", histAddressDetail='" + histAddressDetail + '\'' +
                ", remark='" + remark + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", goodsList=" + goodsList +
                '}';
    }
}
