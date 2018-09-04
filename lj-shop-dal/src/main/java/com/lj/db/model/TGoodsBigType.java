package com.lj.db.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @公司:王晓深专属
 * @功能:良建商城 Model
 * @作者:leiquan    
 * @日期:2018-07-09 22:34:16  
 * @版本:1.0
 * @修改:
 */
 
public class TGoodsBigType implements Serializable {

   
	@Override
	public String toString() {
		return "TGoodsBigType [id=" + id + ", bigTypeName=" + bigTypeName + ", imgUrl=" + imgUrl + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", outTime=" + outTime + ", deleted=" + deleted + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 307148003757299055L;
	/**
	 *主键 
	 */
	private Integer id; 
	/**
	 *大类名称 
	 */
	private String bigTypeName; 
	/**
	 *广告图片url 
	 */
	private String imgUrl;
	/**
	 * 是否设为广告：0 否 1 是
	 */
	private String isAd;
	/**
	 *创建时间 
	 */
	private Date createTime; 
	/**
	 *更新时间 
	 */
	private Date updateTime; 
	/**
	 *过期时间 
	 */
	private Date outTime; 
	/**
	 *删除标示：0有效，1已删 
	 */
	private String deleted; 
	
	public TGoodsBigType() { 
	  super();
	}
	
	/**
	 * 获取主键 
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * 设置主键 
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取大类名称 
	 */
	public String getBigTypeName() {
		return bigTypeName;
	}
	
	/**
	 * 设置大类名称 
	 */
	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}
	
	/**
	 * 获取广告图片url 
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * 设置广告图片url 
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
	 * 获取过期时间 
	 */
	public Date getOutTime() {
		return outTime;
	}
	
	/**
	 * 设置过期时间 
	 */
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	
	/**
	 * 获取删除标示：0有效，1已删 
	 */
	public String getDeleted() {
		return deleted;
	}
	
	/**
	 * 设置删除标示：0有效，1已删 
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getIsAd() {
		return isAd;
	}

	public void setIsAd(String isAd) {
		this.isAd = isAd;
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
		   TGoodsBigType other = (TGoodsBigType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}
	


}
