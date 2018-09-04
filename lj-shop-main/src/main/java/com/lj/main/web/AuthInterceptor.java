package com.lj.main.web;

import com.alibaba.druid.support.json.JSONUtils;
import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.RespMessage;
import com.lj.common.util.DateHelper;
import com.lj.common.util.EhcacheUtil;
import com.lj.domain.dto.BaseUser;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.service.cache.EhcacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiaoshen.wang on 2018/7/5
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EhcacheService ehcacheService;
    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadLocalUtil.setHttp(request,response);
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Login login = method.getAnnotation(Login.class);
            logger.info("当前拦截方法:{}",method.toString());
            if (null == login) {
                // 不需要登录
                return true;
            } else {
                BaseUser baseUser =null;
                // 获取token
                String token = request.getHeader("token");
                if (StringUtils.isEmpty(token)){
                    // token获取失败则获取session
                    HttpSession session = request.getSession(false);
                    if (session == null || session.getAttribute("baseUser") == null) {
                        throw new LJShopException(ErrorCode.UNLOGIN_ERROR);
                    }
                    baseUser = (BaseUser) session.getAttribute("baseUser");
                }else {
                    logger.info("用户token【{}】",token);
                    baseUser = ehcacheService.getBaseUser(token,null);
                    if (baseUser == null){
                        throw new LJShopException(ErrorCode.UNLOGIN_ERROR);
                    }
                    // 判断token是否到了有效期，是则生成新的token
                    Date curDate = DateHelper.getCurrentTime();
                    if (curDate.after(baseUser.getEffectiveTime())){
                        ehcacheService.clearByToken(token);
                        //ehcacheService.clearByUserId(baseUser.getId().toString());
                        // String newtoken = ehcacheService.getToken(baseUser.getId().toString());
                        String newtoken = ehcacheUtil.createToken(baseUser.getId().toString());
                        baseUser.setEffectiveTime(DateHelper.nowAdd(Calendar.MINUTE,3));
                        ehcacheService.getBaseUser(newtoken,baseUser);
                        response.setHeader("token",newtoken);
                        logger.info("过期生成的新token【{}】",newtoken);
                    }
                }
                if (null == baseUser){
                    throw new LJShopException(ErrorCode.UNLOGIN_ERROR);
                }
                ThreadLocalUtil.setCurUser(baseUser);

                //是否需要权限
                if(!login.requirePerm()){
                    return true;
                }else {
                    //权限验证
                    if (login.permissions().length > 0) {
                        String[] permissions = login.permissions();
                        for (int i = 0; i < permissions.length; i++) {
                            //TODO 把用户信息放到redis中
                            //if(userInfo.getPermissions().contains(permissions[i])){
                            return true;
                            //}
                        }
                    }
                    throw new LJShopException(ErrorCode.AUTH_VALID_ERROR);
                }
            }
        }
        return true;
    }

    /**
     * @Title: returnJsonMsg
     * @Description: 返回给前端封装
     * @param res
     * @param response
     * @return void
     * @throws
     */
    public void returnJsonMsg(RespMessage res, HttpServletResponse response) {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");

            String msg = JSONUtils.toJSONString(res);
            out.print(msg);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


}
