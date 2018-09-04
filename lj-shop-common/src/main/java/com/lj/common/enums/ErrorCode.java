package com.lj.common.enums;

/**
 * Created by xiaoshen.wang on 2018/7/4
 */
public enum ErrorCode {
        SYSTEM_ERROR(0x10000001, "系统错误"),
        ARGS_ERROR(0x10000002, "参数错误"),
        INVALID_DATA(0x10000003, "无效数据"),
        INVALID_TIME(0x10000004, "日期不正确"),
        AUTH_VALID_ERROR(0x10000005, "用户权限不足"),
        UNLOGIN_ERROR(0x10000006, "用户未登录或登录状态超时失效"),
        ARGS_MISS_ERROR(0x10000007, "参数缺失"),
        USER_NOT_EXIST(0x10000008, "用户不存在"),
        GOODS_NOT_EXIST(0x10000011, "商品不存在"),
        DATA_NOT_EXIST(0x10000012, "更新操作没有找到对应记录"),
        ORDER_NOT_EXIST(0x10000013, "订单不存在"),
        ORDER_STATUS_ERROR(0x10000014, "订单状态不正确"),
        ERR_API_CUSTOM(0x40080000, "自定义错误信息");

        private final int value;

        private final String message;

        ErrorCode(int value, String message) {
            this.value = value;
            this.message = message;
        }

        public int getValue() {
            return value;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }

        public String getCode() {
            return Integer.toHexString(this.value).toUpperCase();
        }

        public String getSystem() {
            return Integer.toHexString(this.value >>> 24).toUpperCase();
        }

        public String getModule() {
            return Integer.toHexString(this.value >>> 16).toUpperCase();
        }

        public static ErrorCode getByCode(int value) {
            for (ErrorCode _enum : values()) {
                if (_enum.getValue() == value) {
                    return _enum;
                }
            }
            return null;
        }

}
