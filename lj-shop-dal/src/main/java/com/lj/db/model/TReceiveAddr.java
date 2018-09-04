package com.lj.db.model;

import java.util.Date;
/**
 * @公司:良建商城
 * @功能: Model
 * @作者:wangxs    
 * @日期:2018-07-10 22:39:43  
 * @版本:1.0
 * @修改:
 */
 
public class TReceiveAddr {

  	/**
	 * 
	 */
	private Long id; 
	/**
	 *用户id 
	 */
	private Long userId; 
	/**
	 *用户名 
	 */
	private String userName; 
	/**
	 *手机号 
	 */
	private String cellphone; 
	/**
	 *省份 
	 */
	private String addressProvince; 
	/**
	 *城市 
	 */
	private String addressCity; 
	/**
	 *区域 
	 */
	private String addressArea; 
	/**
	 *街道信息 
	 */
	private String addressStreet; 
	/**
	 *收货地址 
	 */
	private String receiveAddress; 
	/**
	 *地址类型：如家 公司 学校等 
	 */
	private String addressType; 
	/**
	 *是否设置为默认地址：0 是 1 否 
	 */
	private String isDefault; 
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
	
	public TReceiveAddr() { 
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
	 * 获取用户id 
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * 设置用户id 
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取用户名 
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * 设置用户名 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取手机号 
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * 设置手机号 
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	/**
	 * 获取省份 
	 */
	public String getAddressProvince() {
		return addressProvince;
	}
	
	/**
	 * 设置省份 
	 */
	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}
	
	/**
	 * 获取城市 
	 */
	public String getAddressCity() {
		return addressCity;
	}
	
	/**
	 * 设置城市 
	 */
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	
	/**
	 * 获取区域 
	 */
	public String getAddressArea() {
		return addressArea;
	}
	
	/**
	 * 设置区域 
	 */
	public void setAddressArea(String addressArea) {
		this.addressArea = addressArea;
	}
	
	/**
	 * 获取街道信息 
	 */
	public String getAddressStreet() {
		return addressStreet;
	}
	
	/**
	 * 设置街道信息 
	 */
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	
	/**
	 * 获取收货地址 
	 */
	public String getReceiveAddress() {
		return receiveAddress;
	}
	
	/**
	 * 设置收货地址 
	 */
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	
	/**
	 * 获取地址类型：如家 公司 学校等 
	 */
	public String getAddressType() {
		return addressType;
	}
	
	/**
	 * 设置地址类型：如家 公司 学校等 
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	
	/**
	 * 获取是否设置为默认地址：0 是 1 否 
	 */
	public String getIsDefault() {
		return isDefault;
	}
	
	/**
	 * 设置是否设置为默认地址：0 是 1 否 
	 */
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
		   TReceiveAddr other = (TReceiveAddr) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}
	


}
