package com.lj.common.exception;

import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.SystemCode;

/**
 * Created by feng.ao on 2017/11/30.
 */
public class LJShopException extends EyasException {

    private static final long serialVersionUID = 1066421747683576878L;

    public LJShopException(String errorCode, String message) {
        super(errorCode, message);
    }

    public LJShopException(String errorCode, String message, Throwable e) {
        super(errorCode, message, e);
    }

    public LJShopException(String errorCode, String message, SystemCode systemCode) {
        super(errorCode, message, systemCode);
    }

    public LJShopException(String errorCode, String message, SystemCode systemCode, Throwable e) {
        super(errorCode, message, systemCode, e);
    }
    public LJShopException(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public LJShopException(ErrorCode errorCode, String message) {
        super(errorCode.getCode(), message);
    }

    public LJShopException(ErrorCode errorCode, String message, Throwable e) {
        super(errorCode.getCode(), message,e);
    }
}
