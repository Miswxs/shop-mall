package com.lj.domain.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xiaoshen.wang on 2018/7/5
 */
public class BaseUser implements Serializable{
    private static final long serialVersionUID = -2603212551654243212L;
    private Long id;
    private String userName;
    private String cellphone;
    // 失效时间
    private Date effectiveTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", cellphone='" + effectiveTime + '\'' +
                '}';
    }
}
