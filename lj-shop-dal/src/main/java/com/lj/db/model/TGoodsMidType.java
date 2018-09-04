package com.lj.db.model;

import java.io.Serializable;
import java.util.Date;

public class TGoodsMidType implements Serializable {
 
	@Override
	public String toString() {
		return "TGoodsMidType [id=" + id + ", middleTypeName=" + middleTypeName + ", bigId=" + bigId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", deleted=" + deleted + "]";
	}

	private static final long serialVersionUID = 852642391725383436L;
	
	/**
	 *主键 
	 */
	private Integer id; 
	/**
	 *小类名称 
	 */
	private String middleTypeName; 
	/**
	 *大类id 
	 */
	private Integer bigId; 
	/**
	 *创建时间 
	 */
	private Date createTime; 
	/**
	 *更新时间 
	 */
	private Date updateTime; 
	/**
	 *删除标识：0未删，1已删 
	 */
	private String deleted; 
	
	private String bigTypeName;
	
	public TGoodsMidType() { 
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
	 * 获取小类名称 
	 */
	public String getMiddleTypeName() {
		return middleTypeName;
	}
	
	/**
	 * 设置小类名称 
	 */
	public void setMiddleTypeName(String middleTypeName) {
		this.middleTypeName = middleTypeName;
	}
	
	/**
	 * 获取大类id 
	 */
	public Integer getBigId() {
		return bigId;
	}
	
	/**
	 * 设置大类id 
	 */
	public void setBigId(Integer bigId) {
		this.bigId = bigId;
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
	 * 获取删除标识：0未删，1已删 
	 */
	public String getDeleted() {
		return deleted;
	}
	
	/**
	 * 设置删除标识：0未删，1已删 
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
		   TGoodsMidType other = (TGoodsMidType) obj;
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

	 
}
