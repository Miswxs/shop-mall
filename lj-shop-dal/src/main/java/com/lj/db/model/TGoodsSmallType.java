package com.lj.db.model;

import java.io.Serializable;
import java.util.Date;

public class TGoodsSmallType implements Serializable{
 
	

	private static final long serialVersionUID = 7180268740352575302L;
	
	/**
	 *主键id 
	 */
	private Integer id; 
	/**
	 *商品中型类别id 
	 */
	private Integer middId; 
	/**
	 *小类型名称 
	 */
	private String smallName; 
	/**
	 * 
	 */
	private String imgUrl; 
	/**
	 *创建时间 
	 */
	private Date createTime; 
	/**
	 *更新时间 
	 */
	private Date updateTime; 
	/**
	 *删除标识：0标识有效，1标识已删 
	 */
	private String deleted; 
	
	/**
	 * 中类名称
	 */
	private String middleTypeName;
	
	/**
	 * 大类名称
	 */
	private String bigTypeName;

	
	/**
	 * 中类名称
	 */
	private String midTypeName;
	
	public TGoodsSmallType() { 
	  super();
	}
	
	/**
	 * 获取主键id 
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置主键id 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取商品中型类别id 
	 */
	public Integer getMiddId() {
		return middId;
	}
	
	/**
	 * 设置商品中型类别id 
	 */
	public void setMiddId(Integer middId) {
		this.middId = middId;
	}
	
	/**
	 * 获取小类型名称 
	 */
	public String getSmallName() {
		return smallName;
	}
	
	/**
	 * 设置小类型名称 
	 */
	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}
	
	/**
	 * 获取 
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * 设置 
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/**
	 * 获取创建时间 
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取更新时间 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置更新时间 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取删除标识：0标识有效，1标识已删 
	 */
	public String getDeleted() {
		return deleted;
	}
	
	/**
	 * 设置删除标识：0标识有效，1标识已删 
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
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
		   TGoodsSmallType other = (TGoodsSmallType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}

	 

	public String getMidTypeName() {
		return midTypeName;
	}

	public void setMidTypeName(String midTypeName) {
		this.midTypeName = midTypeName;
	}

	public String getMiddleTypeName() {
		return middleTypeName;
	}

	public void setMiddleTypeName(String middleTypeName) {
		this.middleTypeName = middleTypeName;
	}

	public String getBigTypeName() {
		return bigTypeName;
	}

	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}
	
	@Override
	public String toString() {
		return "TGoodsSmallType [id=" + id + ", middId=" + middId + ", smallName=" + smallName + ", imgUrl=" + imgUrl
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", deleted=" + deleted
				+ ", middleTypeName=" + middleTypeName + ", bigTypeName=" + bigTypeName + ", midTypeName=" + midTypeName
				+ "]";
	}

}
