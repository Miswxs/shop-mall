package com.lj.db.model;

import java.math.BigDecimal;

/**
 * @公司:良建商城
 * @功能: Model
 * @作者:wangxs    
 * @日期:2018-07-09 22:17:07  
 * @版本:1.0
 * @修改:
 */
 
public class TOrderInfo {

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
	/**
	 * 交易流水号
	 */
	private String tradeId;
	/**
	 * 预支付交易会话标识
	 */
	private String prepayId;

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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		   TOrderInfo other = (TOrderInfo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}

	@Override
	public String toString() {
		return "TOrderInfo{" +
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
				", deleted='" + deleted + '\'' +
				", createdBy='" + createdBy + '\'' +
				", createdAt='" + createdAt + '\'' +
				", updatedBy='" + updatedBy + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				'}';
	}
}
