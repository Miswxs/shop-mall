package com.lj.common.resp;

import com.lj.common.enums.ErrorCode;

import java.io.Serializable;

/**
 * Created by xiaoshen.wang on 2018/7/4
 * 返回给前端的封装类
 */
public class RespMessage implements Serializable{
    

	private static final long serialVersionUID = 8710979890536332553L;

        private final static String SUCCESS_CODE = "0";

        private String code;
        private String msg;
        private Object data;
        
        private boolean result;//兼容分页插件

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public RespMessage() {
        }

        public RespMessage(String code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }
        
        public RespMessage(Boolean result, String message, Object model) {
        	 this.result = result;
             this.msg = message;
             this.data = model;
        }
        
        public static RespMessage retPageMsg(Boolean result, String message, Object model) {
        	return new RespMessage(result,message, model);
        }

        public static RespMessage successResp(Object data){
            return new RespMessage(SUCCESS_CODE,"请求成功", data);
        }

        public static RespMessage successResp(String message, Object data){
            return new RespMessage(SUCCESS_CODE,message, data);
        }

        public static RespMessage failResp(Object data){
            return new RespMessage(ErrorCode.SYSTEM_ERROR.getCode(),"请求失败", data);
        }

        public static RespMessage failResp(String message, Object data){
            return new RespMessage(ErrorCode.SYSTEM_ERROR.getCode(), message, data);
        }

        public static RespMessage failResp(ErrorCode errorCode, Object data){
            return new RespMessage(errorCode.getCode(), errorCode.getMessage(), data);
        }

        public static RespMessage failResp(String errorCode, String message, Object data){
            return new RespMessage(errorCode, message, data);
        }
        
        @Override
    	public String toString() {
    		return "RespMessage [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    	}

}
