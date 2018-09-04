package com.lj.common.enums;

import com.lj.common.exception.LJShopException;

public enum UserTypeEnum {
    MERCHANT("M","商户"),
    CUSTOMER("C","商城客户");

    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    UserTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static UserTypeEnum getEnum(String value) {
        for (UserTypeEnum _enum : values()) {
            if (_enum.getValue().equals(value)) {
                return _enum;
            }
        }
        throw new LJShopException(ErrorCode.INVALID_DATA,"用户类型不正确");
    }
}
