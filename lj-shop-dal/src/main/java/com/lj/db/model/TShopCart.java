package com.lj.db.model;

import java.util.Date;
/**
 * @公司:
 * @功能:良建商城 Model
 * @作者:leiquan    
 * @日期:2018-07-27 17:56:34  
 * @版本:1.0
 * @修改:
 */
 
public class TShopCart {

  	/**
	 * 
	 */
	private Long id; 
	/**
	 *商品编号 
	 */
	private Long goodsNo;
	/**
	 *商品数量 
	 */
	private Integer goodsNum;
	private String goodsColor;
	private String goodsSize;
	/**
	 *买家id 
	 */
	private Long customerId; 
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
	private Date createdAt; 
	/**
	 *更新人 
	 */
	private String updatedBy; 
	/**
	 *更新时间 
	 */
	private Date updatedAt; 
	
	public TShopCart() { 
	  super();
	}
	
	/**
	 * 获取 
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 设置 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 获取商品编号 
	 */
	public Long getGoodsNo() {
		return goodsNo;
	}
	
	/**
	 * 设置商品编号 
	 */
	public void setGoodsNo(Long goodsNo) {
		this.goodsNo = goodsNo;
	}
	
	/**
	 * 获取商品数量 
	 */
	public Integer getGoodsNum() {
		return goodsNum;
	}
	
	/**
	 * 设置商品数量 
	 */
	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}
	
	/**
	 * 获取买家id 
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * 设置买家id 
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * 获取删除标识：0 未删除 1 已删除 
	 */
	public String getDeleted() {
		return deleted;
	}
	
	/**
	 * 设置删除标识：0 未删除 1 已删除 
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	/**
	 * 获取创建人 
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * 设置创建人 
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * 获取创建时间 
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * 设置创建时间 
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * 获取更新人 
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	/**
	 * 设置更新人 
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	/**
	 * 获取更新时间 
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * 设置更新时间 
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
		   TShopCart other = (TShopCart) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}
	


}
