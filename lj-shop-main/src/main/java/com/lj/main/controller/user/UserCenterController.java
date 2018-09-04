package com.lj.main.controller.user;

import com.lj.common.annotation.Login;
import com.lj.common.enums.ErrorCode;
import com.lj.common.enums.FavoriteType;
import com.lj.common.enums.UserTypeEnum;
import com.lj.common.exception.LJShopException;
import com.lj.common.resp.PageResp;
import com.lj.common.resp.RespMessage;
import com.lj.common.util.*;
import com.lj.db.model.TAppCaptcha;
import com.lj.db.model.TMessageConfig;
import com.lj.db.model.TUser;
import com.lj.domain.dto.BaseUser;
import com.lj.domain.dto.GoodsDto;
import com.lj.main.req.UserReq;
import com.lj.main.util.SessionUtil;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.main.vo.UserInfoVo;
import com.lj.service.cache.EhcacheService;
import com.lj.service.user.UserCenterService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户个人中心
 */
@RestController
@RequestMapping("/user")
public class UserCenterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCenterService.class);

    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    private EhcacheService ehcacheService;
    @Autowired
    private EhcacheUtil ehcacheUtil;

    /**
     * 发送验证短信；注册时
     * @param cellphone
     * @return
     */
    @RequestMapping("/getCaptcha")
    public RespMessage getCaptcha(@RequestParam String cellphone){
        LOGGER.info("获取手机验证码入参【{}】",cellphone);
        ValidateUtil.isNotBlank(cellphone,"请先输入手机号", ErrorCode.ARGS_MISS_ERROR);
        // 判断手机类型

        TMessageConfig tMessageConfig = userCenterService.getMessageConfigByType(cellphone);
        if (null == tMessageConfig){
            return RespMessage.failResp(ErrorCode.INVALID_DATA.getCode(),"请输入正确的手机号",null);
        }
        //发送短信验证码
        userCenterService.sendMessage(cellphone,tMessageConfig);
        return RespMessage.successResp("验证码发送成功，请注意查收",null);
    }

    /**
     * 发送验证短信：忘记密码
     * @param cellphone
     * @return
     */
    @RequestMapping("/getCaptcha_forgetPwd")
    public RespMessage getCaptcha_forgetPwd(@RequestParam String cellphone){
        LOGGER.info("获取手机验证码入参【{}】",cellphone);
        ValidateUtil.isNotBlank(cellphone,"请先输入手机号", ErrorCode.ARGS_MISS_ERROR);
        TUser tUser = userCenterService.findUserByCellphone(cellphone);
        if (tUser == null){
            throw new LJShopException(ErrorCode.USER_NOT_EXIST,"该用户手机号不存在");
        }
        // 判断手机类型
        TMessageConfig tMessageConfig = userCenterService.getMessageConfigByType(cellphone);
        if (null == tMessageConfig){
            return RespMessage.failResp(ErrorCode.INVALID_DATA.getCode(),"请输入正确的手机号",null);
        }
        //发送短信验证码
        userCenterService.sendMessage(cellphone,tMessageConfig);
        return RespMessage.successResp("验证码发送成功，请注意查收",null);
    }

    /**
     * 商城注册提交
     * @param cellphone
     * @param pwd
     * @param captcha
     * @return
     */
    @RequestMapping("/userRegister")
    public RespMessage submitUser(@RequestParam String cellphone, String pwd, String captcha){
        LOGGER.info("用户注册入参【手机号：{},验证码：{}】",cellphone,captcha);
        TAppCaptcha tAppCaptcha = userCenterService.validateCaptcha(cellphone);
        if (tAppCaptcha == null){
            return RespMessage.failResp("请先获取验证码",null);
        }
        if (!tAppCaptcha.getCaptcha().equalsIgnoreCase(captcha)){
            return RespMessage.failResp("验证码不正确",null);
        }
        Calendar curDate = Calendar.getInstance();
        if (curDate.after(tAppCaptcha.getEffectiveTime())){
            return RespMessage.failResp("验证码失效，请重新获取",null);
        }
        userCenterService.registerUser(cellphone,pwd, SessionUtil.getIp(), UserTypeEnum.CUSTOMER.getValue());
        return RespMessage.successResp("注册成功");
    }

    /**
     * 修改密码
     * @param cellphone
     * @param pwd
     * @param captcha
     * @return
     */
    @RequestMapping("/forgetPwd")
    public RespMessage forgetPwd(@RequestParam String cellphone, String pwd, String captcha){
        LOGGER.info("修改密码入参【手机号：{},验证码：{}】",cellphone,captcha);
        TAppCaptcha tAppCaptcha = userCenterService.validateCaptcha(cellphone);
        if (tAppCaptcha == null){
            return RespMessage.failResp("请先获取验证码",null);
        }
        if (!tAppCaptcha.getCaptcha().equalsIgnoreCase(captcha)){
            return RespMessage.failResp("验证码不正确",null);
        }
        Calendar curDate = Calendar.getInstance();
        if (curDate.after(tAppCaptcha.getEffectiveTime())){
            return RespMessage.failResp("验证码失效，请重新获取",null);
        }
        userCenterService.forgetPwd(cellphone,pwd);
        return RespMessage.successResp("密码修改成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespMessage login(@RequestParam String cellphone, String password) {
        LOGGER.info("用户登录入参【手机号：{}】",cellphone);
        TUser tUser = userCenterService.findUserByCellphone(cellphone);
        LOGGER.info("用户信息【{}】",tUser);
        if (tUser == null || !tUser.getLoginPwd().equals(password)){
            return RespMessage.failResp("用户名或密码不正确",null);
        }
        BaseUser baseUser = BeanUtil.copyProperties(tUser, BaseUser.class);
        baseUser.setEffectiveTime(DateHelper.nowAdd(Calendar.MINUTE,3));
        // 设置session
        SessionUtil.set("baseUser", baseUser);
        // 设置缓存
        // String token= ehcacheService.getToken(baseUser.getId().toString());
        String token = ehcacheUtil.createToken(baseUser.getId().toString());
        ehcacheService.getBaseUser(token,baseUser);

        return RespMessage.successResp("登录成功", token);
    }

    @RequestMapping(value = "/logout")
    public RespMessage logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        //  清除session
        HttpSession session = SessionUtil.getHttpSession();
        session.removeAttribute("baseUser");
        session.invalidate();
        ThreadLocalUtil.removeAccount();
        // 清空缓存
        ehcacheService.clearByToken(token);
        /*BaseUser baseUser= ehcacheService.getBaseUser(token,null);
        if (baseUser !=null) {
            ehcacheService.clearByUserId(baseUser.getId().toString());
        }*/
        return RespMessage.successResp("注销成功");
    }

    /**
     * 查看个人资料
     * @return
     */
    @RequestMapping("/showUserDetail")
    @Login
    public UserInfoVo showUserDetail(){
        BaseUser user = ThreadLocalUtil.currentAccount();
        return BeanUtil.copyProperties(userCenterService.findUserById(user.getId()),UserInfoVo.class);
    }

    /**
     * 编辑用户信息
     * @param req
     */
    @RequestMapping("/editUserInfo")
    @Login
    public void editUserInfo(@RequestBody UserReq req){
        LOGGER.info("/user/editUserInfo入参[{}]",req);
        BaseUser user = ThreadLocalUtil.currentAccount();
        req.setId(user.getId());
        TUser tUser = BeanUtil.copyProperties(req,TUser.class);
        userCenterService.updateUserInfo(tUser);
    }

    /**
     * 添加到收藏夹
     * @param goodsNo
     */
    @RequestMapping("/addToMyFavirate")
    @Login
    public void addToMyFavirate(@RequestParam String goodsNo,String favoriteType){
        ValidateUtil.isNotBlank(goodsNo,"请先选择商品",ErrorCode.ARGS_MISS_ERROR);
        if (StringUtils.isBlank(favoriteType)) {
			favoriteType = FavoriteType.GOODS.getValue();
		}
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        userCenterService.addToMyFavirate(user.getId(),goodsNo,favoriteType);
    }

    /**
     * 取消收藏商品
     * @param gIds
     */
    @RequestMapping("/deleteMyFavirateGoods")
    @Login
    public void deleteMyFavirateGoods(@RequestBody List<Long> gIds){
        LOGGER.info("/user/deleteMyFavirateGoods入参[{}]",gIds);
        BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        userCenterService.deleteMyFavirate(user.getId(),gIds,FavoriteType.GOODS.getValue());
    }
    
    @RequestMapping("/listMyFavorite")
    @Login
    public PageResp<GoodsDto> listMyFavorite(@RequestParam String type){
    	if (StringUtils.isBlank(type)) {
			type = FavoriteType.GOODS.getValue();
		}
    	BaseUser user = ThreadLocalUtil.currentAccount();
        if (user == null || user.getId() == null || user.getId().equals(0L)){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"请先登录");
        }
        return userCenterService.listMyFavorite(user.getId(), type);
    }

    /**
     * 用于校验是否登录并重定向到登录页面
     */
    @RequestMapping("/validIsLogin")
    @Login
    public void validIsLogin(){

    }

    @RequestMapping("/pageUserInfo")
    public RespMessage pageUserInfo(@RequestParam(value = "searchTxt",required = false) String searchTxt,Integer pageCount,Integer pageSize){
        PageUtil pageUtil =new PageUtil(pageCount,pageSize);
        PageResp<TUser> userPageResp = userCenterService.pageUserInfo(searchTxt,pageUtil.getSkipCount(),pageUtil.getPageSize());

        pageUtil.setRowCount((int)userPageResp.getTotal());
        pageUtil.setList(userPageResp.getContent());
        pageUtil.setPageSize(pageUtil.getPageSize());
        pageUtil.setPageCount(pageUtil.getPageCount());
        pageUtil.setPageIndex(pageUtil.getPageCount()+1);
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("modle", pageUtil);
        return RespMessage.successResp(retMap);
    }

    @RequestMapping("deleteUserById")
    public void deleteUserById(@RequestParam Long userId){
        userCenterService.deleteUserById(userId);
    }

    /**
     * 对密码进行再一次加密
     * @param pwd
     * @return
     */
    private String encryptPassword(String pwd){
    	return DigestUtils.sha256Hex(pwd);
    }

}
