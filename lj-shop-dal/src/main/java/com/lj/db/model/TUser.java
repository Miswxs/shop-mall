package com.lj.db.model;

import java.io.Serializable;
import java.util.Date;
/**
 * @公司:良建商城
 * @功能: Model
 * @作者:wangxs    
 * @日期:2018-07-07 22:41:38  
 * @版本:1.0
 * @修改:
 */
 
public class TUser implements Serializable{

	private static final long serialVersionUID = 8659027916796513569L;
	/**
	 * 用户ID
	 */
	private Long id;
	/**
	 *用户名 
	 */
	private String userName; 
	/**
	 *登陆密码 
	 */
	private String loginPwd; 
	/**
	 *昵称 
	 */
	private String nickName;
	/**
	 * 会员积分
	 */
	private Long memberPoint;
	/**
	 * 用户类型：M 商户 C 商城客户
	 */
	private String userType;
	/**
	 *性别 
	 */
	private String sex; 
	/**
	 *地址 
	 */
	private String address; 
	/**
	 *图片路径 
	 */
	private String imgUrl;
	/**
	 * 兴趣爱好
	 */
	private String hobby;
	/**
	 *微信号 
	 */
	private String wechat; 
	/**
	 *qq号 
	 */
	private String qq; 
	/**
	 *新浪号 
	 */
	private String sina; 
	/**
	 * 
	 */
	private String money; 
	/**
	 *邮箱地址 
	 */
	private String email; 
	/**
	 *手机号码 
	 */
	private String cellphone; 
	/**
	 *IP地址 
	 */
	private String ip; 
	/**
	 *生日 
	 */
	private String birthday;
	/**
	 *职业 
	 */
	private String job; 
	/**
	 *系统随机名称 
	 */
	private String randomName; 
	/**
	 *是否删除：0 未删除 1 已删除 
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
	
	public TUser() { 
	  super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	 * 获取登陆密码 
	 */
	public String getLoginPwd() {
		return loginPwd;
	}
	
	/**
	 * 设置登陆密码 
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
	/**
	 * 获取昵称 
	 */
	public String getNickName() {
		return nickName;
	}
	
	/**
	 * 设置昵称 
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	/**
	 * 获取性别 
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * 设置性别 
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 获取地址 
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * 设置地址 
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取图片路径 
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	
	/**
	 * 设置图片路径 
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/**
	 * 获取微信号 
	 */
	public String getWechat() {
		return wechat;
	}
	
	/**
	 * 设置微信号 
	 */
	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	/**
	 * 获取qq号 
	 */
	public String getQq() {
		return qq;
	}
	
	/**
	 * 设置qq号 
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	
	/**
	 * 获取新浪号 
	 */
	public String getSina() {
		return sina;
	}
	
	/**
	 * 设置新浪号 
	 */
	public void setSina(String sina) {
		this.sina = sina;
	}
	
	/**
	 * 获取 
	 */
	public String getMoney() {
		return money;
	}
	
	/**
	 * 设置 
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	
	/**
	 * 获取邮箱地址 
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置邮箱地址 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 获取手机号码 
	 */
	public String getCellphone() {
		return cellphone;
	}
	
	/**
	 * 设置手机号码 
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	/**
	 * 获取IP地址 
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * 设置IP地址 
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	/**
	 * 获取生日 
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * 设置生日 
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	/**
	 * 获取职业 
	 */
	public String getJob() {
		return job;
	}
	
	/**
	 * 设置职业 
	 */
	public void setJob(String job) {
		this.job = job;
	}
	
	/**
	 * 获取系统随机名称 
	 */
	public String getRandomName() {
		return randomName;
	}
	
	/**
	 * 设置系统随机名称 
	 */
	public void setRandomName(String randomName) {
		this.randomName = randomName;
	}
	
	/**
	 * 获取是否删除：0 未删除 1 已删除 
	 */
	public String getDeleted() {
		return deleted;
	}
	
	/**
	 * 设置是否删除：0 未删除 1 已删除 
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

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Long getMemberPoint() {
		return memberPoint;
	}

	public void setMemberPoint(Long memberPoint) {
		this.memberPoint = memberPoint;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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
		   TUser other = (TUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.getId()))
		    return false;
	    return true;
	}

	@Override
	public String toString() {
		return "[id=" + id + "userName=" + userName + "cellphone=" + cellphone
		+ "nickName=" + nickName +"]";
	}
}
