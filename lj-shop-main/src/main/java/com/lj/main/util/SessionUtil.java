package com.lj.main.util;

import com.lj.domain.dto.BaseUser;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaoshen.wang on 2018/7/5
 */
public class SessionUtil {
    /**
     * 将信息存储到会话信息中
     */
    public static void set(String key, Object value) {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 从会话信息中获取信息
     */
    protected Object get(String key) {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    /**
     * 获取session
     *
     * @return
     */
    public static HttpSession getHttpSession() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    /**
     * 从session中获取 String
     */
    public String getStr(String key) {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        HttpSession session = request.getSession();

        Object o = session.getAttribute(key);
        if (o == null)
            return null;
        else
            return o.toString();
    }

    public BaseUser getCurUser() {
        BaseUser baseUser = (BaseUser) this.get("baseUser");
        return baseUser;
    }

    /**
     * 获取当前用户的ID
     */
    public Long getCurUserId() {
        BaseUser baseUser = (BaseUser) this.get("baseUser");
        if (baseUser != null) {
            return baseUser.getId();
        }
        return 0L;
    }

    /**
     * 获取当前URL
     *
     * @return
     */
    public String getCururl() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        String reqStr = request.getRequestURL().toString();
        String queryStr = request.getQueryString();

        if (StringUtils.isBlank(queryStr)) {
            return reqStr;
        } else {
            return reqStr + "?" + queryStr;
        }
    }

    public String getRequestURL() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        String reqStr = request.getRequestURL().toString();
        return reqStr;
    }

    /**
     * 获取请求IP
     *
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    public String getRealPath() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        // return request.getRealPath("");
        return request.getServletContext().getRealPath("");
    }

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ThreadLocalUtil.getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ThreadLocalUtil.getResponse();
        return response;
    }
}
