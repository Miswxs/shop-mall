package com.lj.common.util;

import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoxia.zhou on 2017/12/4.
 */
public class ValidateUtil {

    public static final Pattern PHONE = Pattern.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|14[57])[0-9]{8}$");

    public static void base(String message, ErrorCode... errorCode){
        if(errorCode == null || errorCode.length == 0){
            throw new LJShopException(ErrorCode.ERR_API_CUSTOM.getCode(), message);
        }else{
            throw new LJShopException(errorCode[0].getCode(), message);
        }
    }

    public static void notNull(Object object, String message, ErrorCode... errorCode) {
        if (object == null) {
            base(message, errorCode);
        }
    }

    public static void isNotBlank(String str, String message,ErrorCode... errorCode){
        if(StringUtils.isBlank(str)){
            base(message, errorCode);
        }
    }

    public static void isBlank(String str, String message, ErrorCode... errorCode){
        if(StringUtils.isNotBlank(str)){
            base(message, errorCode);
        }
    }

    public static void isTrue(boolean expression, String message, ErrorCode... errorCode) {
        if (!expression) {
            base(message, errorCode);
        }
    }

    public static void isNotTrue(boolean expression, String message, ErrorCode... errorCode) {
        if (expression) {
            base(message, errorCode);
        }
    }

    /**
     * 断言集合不为空
     * @param object
     * @param message
     * @param errorCode
     */
    public static void notEmpty(Collection<?> object, String message, ErrorCode... errorCode) {
        if (object == null || object.isEmpty()) {
            base(message, errorCode);
        }
    }
    /**
     * 判断是否是空字符串
     * @param str
     * @param message
     */
    public static void notEmpty(String str,String message, ErrorCode... errorCode){
        if (str == null || str == "") {
            base(message, errorCode);
        }
    }

    /**
     * 断言是否小于指定数字，否在抛出Bussiness异常
     * @param theNum
     * @param diff
     * @param message
     */
    public static void lt(Integer theNum,Integer diff,String message, ErrorCode... errorCode){
        if (theNum == null || diff == null) {
            base(message, errorCode);
        }
        if(theNum >= diff){
            base(message, errorCode);
        }
    }

    /**
     * 断言是否小于等于指定数字，否在抛出Bussiness异常
     * @param theNum
     * @param diff
     * @param message
     */
    public static void le(Integer theNum,Integer diff,String message, ErrorCode... errorCode){
        if (theNum == null || diff == null) {
            base(message, errorCode);
        }
        if(theNum > diff){
            base(message, errorCode);
        }
    }

    /**
     * 断言是否大于指定数字，否在抛出Bussiness异常
     * @param theNum
     * @param diff
     * @param message
     */
    public static void gt(Integer theNum,Integer diff,String message, ErrorCode... errorCode){
        if (theNum == null || diff == null) {
            base(message, errorCode);
        }
        if(theNum <= diff){
            base(message, errorCode);
        }
    }

    /**
     * 断言是否大于等于指定数字，否在抛出Bussiness异常
     * @param theNum
     * @param diff
     * @param message
     */
    public static void ge(Integer theNum,Integer diff,String message, ErrorCode... errorCode){
        if (theNum == null || diff == null) {
            base(message, errorCode);
        }
        if(theNum < diff){
            base(message, errorCode);
        }
    }

    /**
     * 断言字符串是否超长
     * @param str
     * @param maxLength
     * @param message
     */
    public static void strMaxLen(String str, int maxLength, String message, ErrorCode... errorCode) {
        if (str == null) {
            base(message, errorCode);
        }
        if(str.length() > maxLength) {
            base(message, errorCode);
        }
    }

    /**
     * 断言字符串是否是固定长度
     * @param str
     * @param equalLength
     * @param message
     */
    public static void strEqualLen(String str, int equalLength, String message, ErrorCode... errorCode) {
        if (str == null) {
            base(message, errorCode);
        }
        if(str.length() != equalLength) {
            base(message, errorCode);
        }
    }

    /**
     * 断言布尔值是否超长（boolean默认长度为1）
     * @param bln
     * @param maxLength
     * @param message
     */
    public static void booleanMaxLen(Boolean bln, int maxLength, String message, ErrorCode... errorCode) {
        if (bln == null) {
            base(message, errorCode);
        }
        if(1 > maxLength) {
            base(message, errorCode);
        }
    }

    /**
     * 断言对象是否不为空
     * @param obj
     * @param message
     * @param errorCode
     */
    public static void assertNotNull(Object obj, String message, ErrorCode... errorCode) {
        if (obj == null) {
            base(message, errorCode);
        }
    }

    public static Boolean verifycellPhone(String cellPhone){
        Matcher m = PHONE.matcher(cellPhone);
        return m.matches();
    }

}
