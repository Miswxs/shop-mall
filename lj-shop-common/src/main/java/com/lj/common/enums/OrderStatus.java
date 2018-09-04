package com.lj.common.enums;

import com.lj.common.exception.LJShopException;

/**
 * 订单状态枚举
 */
public enum OrderStatus {

    PENDING_PAY("0","1","待付款"),
    PENDING_DELIVERY("1","2","待发货"),
    PENDING_TAKE("2","3","待收货"),
    PENDING_OPINION("3","done","待评价");

    private String value;
    private String nextValue;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public String getNextValue() {
        return nextValue;
    }

    OrderStatus(String value, String nextValue, String desc) {
        this.value = value;
        this.nextValue = nextValue;
        this.desc = desc;
    }

    public static OrderStatus getEnum(String value) {
        for (OrderStatus _enum : values()) {
            if (_enum.getValue().equals(value)) {
                return _enum;
            }
        }
        throw new LJShopException(ErrorCode.INVALID_DATA,"订单状态不正确");
    }
}
