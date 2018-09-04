package com.lj.common.exception;

import com.lj.common.enums.SystemCode;

public class EyasException extends RuntimeException{

	private static final long serialVersionUID = -8777276057702845371L;
	
	/** 系统编码*/
	private SystemCode systemCode;
	
	/** 异常编码*/
	private String errorCode;
    
    public EyasException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }
    
    public EyasException(String errorCode, String message, Throwable e){
        super(message, e);
        this.errorCode = errorCode;
    }
    
    public EyasException(String errorCode, String message, SystemCode systemCode){
        super(message);
        this.errorCode = errorCode;
        this.systemCode = systemCode;
    }
    
    public EyasException(String errorCode, String message, SystemCode systemCode, Throwable e){
        super(message, e);
        this.errorCode = errorCode;
        this.systemCode = systemCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

	public SystemCode getSystemCode() {
		return systemCode;
	}
}
