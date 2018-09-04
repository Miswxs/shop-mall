package com.lj.common.util;

public class Constants {
    // 短信验证码有效时间
    public static final Integer CAPTCHA_EFFECTIVE_TIME = 2;

    //港澳台手机正则
    public static final String HK_MOBILE_REX="^852\\d{8}$";

    //国内手机正则
    public static final String COMMON_MOBILE_REX="^1\\d{10}";

    //验证码src
    public static final String CAPTCHA_SRC="0123456789";

    // 生成token的私盐Sha256Hash加密
    public static final String TOKEN_SALT="LXW2";
}
