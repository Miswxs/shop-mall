package com.lj.main.util;

import com.lj.domain.dto.BaseUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaoshen.wang on 2018/7/5
 */
public class ThreadLocalUtil {
    private static final ThreadLocal<HttpServletRequest> LOCAL_REQUEST = new ThreadLocal<HttpServletRequest>();
    private static final ThreadLocal<HttpServletResponse> LOCAL_RESPONSE = new ThreadLocal<HttpServletResponse>();
    private static final ThreadLocal<BaseUser> CUR_USER = new ThreadLocal<BaseUser>();

    private ThreadLocalUtil(){}

    public static BaseUser currentAccount() {
        return CUR_USER.get();
    }

    public static void removeAccount(){
        CUR_USER.remove();
    }

    public static void setCurUser(BaseUser baseUser) {
        if (baseUser == null) {
            return;
        }
        CUR_USER.set(baseUser);
    }

    public static void setHttp(HttpServletRequest request, HttpServletResponse response) {
        LOCAL_REQUEST.set(request);
        LOCAL_RESPONSE.set(response);
    }

    public static HttpServletRequest getRequest(){
        return LOCAL_REQUEST.get();
    }

    public static HttpServletResponse getResponse(){
        return LOCAL_RESPONSE.get();
    }
}
