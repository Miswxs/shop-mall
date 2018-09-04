package com.lj.main.web;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Maps;
import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.EyasException;
import com.lj.common.resp.RespMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 全局异常处理
 * http://www.mkyong.com/spring-mvc/spring-mvc-exceptionhandler-example/
 */
@RestControllerAdvice
public class GlobalExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 一般业务异常，不打印堆栈信息
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(EyasException.class)
    public RespMessage handleResultException(EyasException e, HttpServletRequest request) {
        //业务异常，不打印堆栈信息
        logger.error("全局异常处理【EyasException】：systemCode={},errorCode={},message={}",
                e.getSystemCode(),e.getErrorCode(),e.getMessage());
        return new RespMessage(e.getErrorCode(),e.getMessage(),null);
    }

    /**
     * 请求参数类型转换异常（满足Cause是InvalidFormatException异常类型的条件），不需要打印堆栈信息
     * <p>{@link HttpMessageNotReadableException} && {@link InvalidFormatException}</p>
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RespMessage handleResultException(HttpMessageNotReadableException e, HttpServletRequest request) {
        if(e.getCause() instanceof InvalidFormatException){
            //参数类型转换失败，不需要打印堆栈信息
            logger.error("全局异常处理【参数类型错误】：{}",e.getMessage());
            return RespMessage.failResp(ErrorCode.ARGS_ERROR.getCode(),"参数类型错误",null);
        }else{
            logger.error("全局异常处理【系统异常】",e);
            return RespMessage.failResp(ErrorCode.SYSTEM_ERROR,null);
        }
    }
    /**
     * hibernate注解字段校验异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(BindException.class)
    public RespMessage handleResultException(BindException e, HttpServletRequest request) {
        //注解校验失败，不需要打印堆栈信息
        logger.error("全局异常处理【参数校验错误】:{}",e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        Map<String,String> result = Maps.newHashMap();
        if(bindingResult.hasErrors()){
            List<FieldError> list = bindingResult.getFieldErrors();
            for (FieldError fieldError : list) {
                result.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
        }
        return new RespMessage(ErrorCode.SYSTEM_ERROR.getCode(),result.toString(),null);
    }


    /**
     * hibernate注解字段校验异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespMessage handleResultException(MethodArgumentNotValidException e, HttpServletRequest request) {
        //注解校验失败，不需要打印堆栈信息
        logger.error("全局异常处理【参数校验错误】:{}",e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = "无错误消息";
        if(bindingResult.hasErrors()){
            for(FieldError error: bindingResult.getFieldErrors()){
                //1、先打印出所有的校验错误字段信息
                logger.error("参数校验错误：obj={}, field={}, reject={}, message={}",error.getObjectName()
                        ,error.getField(),error.getRejectedValue(),error.getDefaultMessage());
                //2、以最后一个参数错误信息做为异常抛出
                errorMsg = error.getDefaultMessage();
            }

        }
        return new RespMessage(ErrorCode.ARGS_ERROR.getCode(),errorMsg,null);
    }

    /**
     * 系统异常,打印错误堆栈
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public RespMessage handleResultException(Exception e, HttpServletRequest request) {
        logger.error("全局异常处理【系统异常】",e);
        if(e instanceof MissingServletRequestParameterException){
            return RespMessage.failResp(ErrorCode.ARGS_MISS_ERROR.getCode(),e.getMessage(),null);
        }else{
            return RespMessage.failResp(ErrorCode.SYSTEM_ERROR,null);
        }
    }
}
