package com.lj.db.model;

import java.math.BigDecimal;
import java.util.Date;
/**
 * @公司:良建商城
 * @功能: Model
 * @作者:wangxs    
 * @日期:2018-07-09 22:33:25  
 * @版本:1.0
 * @修改:
 */
 
public class TGoods {

  	@Override
	public String toString() {
		return "TGoods [id=" + id + ", goodsNo=" + goodsNo + ", name=" + name + ", price=" + price + ", stockNum="
				+ stockNum + ", remark=" + remark + ", description=" + description + ", specificationId="
				+ specificationId + ", goodsType=" + goodsType + ", goodsStatus=" + goodsStatus + ", sellerId="
				+ sellerId + ", isAdvantage=" + isAdvantage + ", imgUrl=" + imgUrl + ", imgUrl1=" + imgUrl1
				+ ", imgUrl2=" + imgUrl2 + ", bigTypeId=" + bigTypeId + ", midTypeId=" + midTypeId + ", smallTypeId="
				+ smallTypeId + ", deleted=" + deleted + ", createdBy=" + createdBy + ", createdAt=" + createdAt
				+ ", updatedBy=" + updatedBy + ", updatedAt=" + updatedAt + ", isPostAge=" + isPostAge + ", color="
				+ goodsColor + ", size=" + goodsSize + ", salesPromation=" + salesPromation + "]";
	}

	public String getBigTypeId() {
		return bigTypeId;
	}

	public void setBigTypeId(String bigTypeId) {
		this.bigTypeId = bigTypeId;
	}

	public String getMidTypeId() {
		return midTypeId;
	}

	public void setMidTypeId(String midTypeId) {
		this.midTypeId = midTypeId;
	}

	public String getSmallTypeId() {
		return smallTypeId;
	}

	public void setSmallTypeId(String smallTypeId) {
		this.smallTypeId = smallTypeId;
	}

	public int getIsPostAge() {
		return isPostAge;
	}

	public void setIsPostAge(int isPostAge) {
		this.isPostAge = isPostAge;
	}

	public String getSalesPromation() {
		return salesPromation;
	}

	public void setSalesPromation(String salesPromation) {
		this.salesPromation = salesPromation;
	}

	 

	/**
	 * 
	 */
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
	private String bigTypeId;
	private String midTypeId;
	private String smallTypeId;
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
	
	/**是否包邮**/
	private int isPostAge;

	/** 商品颜色：用逗号隔开 **/
	private String goodsColor;

	/**商品尺寸：用逗号隔开**/
	private String goodsSize;

	/**促销政策**/
	private String salesPromation;
	
	/**大类类别***/
	private String bigTypeName;
	
	/**中类类别**/
	private String middleTypeName;
	
	/***小类类别**/
	private String smallTypeName;
	
	
	public TGoods() { 
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
	 * 获取商品名 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置商品名 
	 */
	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取商品数量 
	 */
	public Integer getStockNum() {
		return stockNum;
	}
	
	/**
	 * 设置商品数量 
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}
	
	/**
	 * 获取 
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 设置 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取商品描述 
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 设置商品描述 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取商品规格 
	 */
	public Integer getSpecificationId() {
		return specificationId;
	}
	
	/**
	 * 设置商品规格 
	 */
	public void setSpecificationId(Integer specificationId) {
		this.specificationId = specificationId;
	}
	
	/**
	 * 获取商品分类 
	 */
	public String getGoodsType() {
		return goodsType;
	}
	
	/**
	 * 设置商品分类 
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	
	/**
	 * 获取卖家id 
	 */
	public Long getSellerId() {
		return sellerId;
	}
	
	/**
	 * 设置卖家id 
	 */
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	
	/**
	 * 获取是否广告位 
	 */
	public Integer getIsAdvantage() {
		return isAdvantage;
	}
	
	/**
	 * 设置是否广告位 
	 */
	public void setIsAdvantage(Integer isAdvantage) {
		this.isAdvantage = isAdvantage;
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

	
	
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Long getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(Long goodsNo) {
		this.goodsNo = goodsNo;
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
		   TGoods other = (TGoods) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}

	public String getBigTypeName() {
		return bigTypeName;
	}

	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}

	public String getMiddleTypeName() {
		return middleTypeName;
	}

	public void setMiddleTypeName(String middleTypeName) {
		this.middleTypeName = middleTypeName;
	}

	public String getSmallTypeName() {
		return smallTypeName;
	}

	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName;
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
