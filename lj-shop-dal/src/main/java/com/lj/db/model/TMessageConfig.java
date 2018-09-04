package com.lj.db.model;

/**
 * @公司:良建商城
 * @功能: Model
 * @作者:wangxs    
 * @日期:2018-07-08 14:00:02  
 * @版本:1.0
 * @修改:
 */
 
public class TMessageConfig {

  	/**
	 * 
	 */
	private Long id; 
	/**
	 *短信地址 
	 */
	private String messageUrl; 
	/**
	 *账号 
	 */
	private String messageAccount; 
	/**
	 *密码 
	 */
	private String messagePwd; 
	/**
	 *短信类型：1 大陆 2 港澳 
	 */
	private String messageType; 
	/**
	 *短信内容 
	 */
	private String messageContent; 
	/**
	 *备注 
	 */
	private String remark; 
	
	public TMessageConfig() { 
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
	 * 获取短信地址 
	 */
	public String getMessageUrl() {
		return messageUrl;
	}
	
	/**
	 * 设置短信地址 
	 */
	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}
	
	/**
	 * 获取账号 
	 */
	public String getMessageAccount() {
		return messageAccount;
	}
	
	/**
	 * 设置账号 
	 */
	public void setMessageAccount(String messageAccount) {
		this.messageAccount = messageAccount;
	}
	
	/**
	 * 获取密码 
	 */
	public String getMessagePwd() {
		return messagePwd;
	}
	
	/**
	 * 设置密码 
	 */
	public void setMessagePwd(String messagePwd) {
		this.messagePwd = messagePwd;
	}
	
	/**
	 * 获取短信类型：1 大陆 2 港澳 
	 */
	public String getMessageType() {
		return messageType;
	}
	
	/**
	 * 设置短信类型：1 大陆 2 港澳 
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	/**
	 * 获取短信内容 
	 */
	public String getMessageContent() {
		return messageContent;
	}
	
	/**
	 * 设置短信内容 
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
	/**
	 * 获取备注 
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 设置备注 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
		   TMessageConfig other = (TMessageConfig) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}
	


}
