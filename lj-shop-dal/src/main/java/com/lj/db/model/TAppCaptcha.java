package com.lj.db.model;

import java.util.Date;
/**
 * @公司:良建商城
 * @功能: 手机验证码
 * @作者:wangxs    
 * @日期:2018-07-07 23:46:28  
 * @版本:1.0
 * @修改:
 */
 
public class TAppCaptcha {

  	/**
	 * 
	 */
	private Long id; 
	/**
	 *手机号 
	 */
	private String cellphone; 
	/**
	 *验证码 
	 */
	private String captcha; 
	/**
	 *有效时间 
	 */
	private Date effectiveTime; 
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
	
	public TAppCaptcha() { 
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
	 * 获取验证码 
	 */
	public String getCaptcha() {
		return captcha;
	}
	
	/**
	 * 设置验证码 
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	/**
	 * 获取有效时间 
	 */
	public Date getEffectiveTime() {
		return effectiveTime;
	}
	
	/**
	 * 设置有效时间 
	 */
	public void setEffectiveTime(Date effectiveTime) {
		this.effectiveTime = effectiveTime;
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
		   TAppCaptcha other = (TAppCaptcha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}
	


}
