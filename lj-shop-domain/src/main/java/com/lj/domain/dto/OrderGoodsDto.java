package com.lj.domain.dto;

import java.math.BigDecimal;

/**
 * @公司:
 * @功能:良建商城 Model
 * @作者:leiquan    
 * @日期:2018-07-24 14:26:39  
 * @版本:1.0
 * @修改:
 */
 
public class OrderGoodsDto {

  	/**
	 * 
	 */
	private Long id; 
	/**
	 *订单号 
	 */
	private String orderNo;
	/**
	 *商品id 
	 */
	private Long goodsNo; 
	/**
	 *商品数量 
	 */
	private Integer goodsNum; 
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
	private String goodsColor;
	private String goodsSize;
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
	 * 获取订单号 
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 设置订单号 
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	/**
	 * 获取商品id 
	 */
	public Long getGoodsNo() {
		return goodsNo;
	}
	
	/**
	 * 设置商品id 
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
	 * 获取历史商品价格 
	 */
	public BigDecimal getHistGoodsPrice() {
		return histGoodsPrice;
	}
	
	/**
	 * 设置历史商品价格 
	 */
	public void setHistGoodsPrice(BigDecimal histGoodsPrice) {
		this.histGoodsPrice = histGoodsPrice;
	}
	
	/**
	 * 获取历史商品名称 
	 */
	public String getHistGoodsName() {
		return histGoodsName;
	}
	
	/**
	 * 设置历史商品名称 
	 */
	public void setHistGoodsName(String histGoodsName) {
		this.histGoodsName = histGoodsName;
	}
	
	/**
	 * 获取历史商品主图 
	 */
	public String getHistGoodsImg() {
		return histGoodsImg;
	}
	
	/**
	 * 设置历史商品主图 
	 */
	public void setHistGoodsImg(String histGoodsImg) {
		this.histGoodsImg = histGoodsImg;
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
	public String getCreatedAt() {
		return createdAt;
	}
	
	/**
	 * 设置创建时间 
	 */
	public void setCreatedAt(String createdAt) {
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
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	/**
	 * 设置更新时间 
	 */
	public void setUpdatedAt(String updatedAt) {
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
}
