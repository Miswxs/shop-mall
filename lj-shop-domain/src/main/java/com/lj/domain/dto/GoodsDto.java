package com.lj.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsDto implements Serializable{
	private static final long serialVersionUID = 3758325045945294149L;
	
	private Long id;
	/**
	 * 商品id 即 主键id，兼容购物车
	 */
	private Long goodsNo;
	/**
	 *商品名 
	 */
	private String name; 
	/**
	 *商品价格 
	 */
	private BigDecimal price;
	/**
	 *商品数量 
	 */
	private Integer stockNum; 
	/**
	 * 
	 */
	private String remark; 
	/**
	 *商品描述 
	 */
	private String description; 
	/**
	 *商品规格 
	 */
	private Integer specificationId; 
	/**
	 *商品分类 
	 */
	private String goodsType; 
	/**
	 * 商品状态
	 */
	private String goodsStatus;
	/**
	 *卖家id 
	 */
	private Long sellerId; 
	/**
	 *是否广告位 
	 */
	private Integer isAdvantage; 
	
	private String imgUrl;
	private String imgUrl1;
	private String imgUrl2;
	private String imgUrl3;
	private String imgUrl4;
	private String imgUrl5;
	private String goodsColor;
	private String goodsSize;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getStockNum() {
		return stockNum;
	}
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSpecificationId() {
		return specificationId;
	}
	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getIsAdvantage() {
		return isAdvantage;
	}
	public void setIsAdvantage(Integer isAdvantage) {
		this.isAdvantage = isAdvantage;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getImgUrl1() {
		return imgUrl1;
	}
	public void setImgUrl1(String imgUrl1) {
		this.imgUrl1 = imgUrl1;
	}
	public String getImgUrl2() {
		return imgUrl2;
	}
	public void setImgUrl2(String imgUrl2) {
		this.imgUrl2 = imgUrl2;
	}
	public String getImgUrl3() {
		return imgUrl3;
	}
	public void setImgUrl3(String imgUrl3) {
		this.imgUrl3 = imgUrl3;
	}
	public String getImgUrl4() {
		return imgUrl4;
	}
	public void setImgUrl4(String imgUrl4) {
		this.imgUrl4 = imgUrl4;
	}
	public String getImgUrl5() {
		return imgUrl5;
	}
	public void setImgUrl5(String imgUrl5) {
		this.imgUrl5 = imgUrl5;
	}
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Long goodsNo) {
		this.goodsNo = goodsNo;
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
