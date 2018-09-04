package com.lj.main.req;

import org.hibernate.validator.constraints.NotBlank;

public class ReceiveAddrReq {
    private Long id;
    /**
     *用户id
     */
    //@NotNull(message = "请先进行登录")
    private Long userId;
    /**
     *用户名
     */
    @NotBlank(message = "请填写收货人姓名")
    private String userName;
    /**
     *手机号
     */
    @NotBlank(message = "请填写收货人手机号")
    private String cellphone;
    /**
     *街道信息
     */
    @NotBlank(message = "请填写详细地址")
    private String addressStreet;
    /**
     *收货地址
     */
    @NotBlank(message = "请填写收货地址")
    private String receiveAddress;
    /**
     *是否设置为默认地址：0 是 1 否
     */
    private String isDefault;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "ReceiveAddrReq [id=" + id + ", userId=" + userId + ", userName=" + userName + ", cellphone=" + cellphone
				+ ", addressStreet=" + addressStreet + ", receiveAddress=" + receiveAddress + ", isDefault=" + isDefault
				+ "]";
	}

}
