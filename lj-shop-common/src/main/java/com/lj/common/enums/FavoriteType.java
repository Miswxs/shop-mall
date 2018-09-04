package com.lj.common.enums;

import com.lj.common.exception.LJShopException;

/**
 * Created by xiaoshen.wang on 2018/7/17
 */
public enum FavoriteType {
    GOODS("G","商品"),
    SHOP("S","店铺");
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    FavoriteType(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static FavoriteType getEnum(String value) {
        for (FavoriteType _enum : values()) {
            if (_enum.getValue().equals(value)) {
                return _enum;
            }
        }
        throw new LJShopException(ErrorCode.INVALID_DATA,"收藏类别不正确");
    }
}
