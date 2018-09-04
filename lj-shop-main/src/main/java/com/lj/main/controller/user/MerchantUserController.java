package com.lj.main.controller.user;

import com.lj.common.enums.ErrorCode;
import com.lj.common.exception.LJShopException;
import com.lj.db.model.TMerchantUser;
import com.lj.domain.dto.BaseUser;
import com.lj.main.util.SessionUtil;
import com.lj.main.util.ThreadLocalUtil;
import com.lj.service.user.MerchantUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/merchantUser")
public class MerchantUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantUserController.class);

    @Autowired
    private MerchantUserService merchantUserService;

    @RequestMapping("login")
    public void login(@RequestParam String userAccount,String pwd){
        LOGGER.info("/merchantUser/login入参[userAccount={}]",userAccount);
       TMerchantUser merchantUser = merchantUserService.findMerchantUserByAccount(userAccount);
        if (merchantUser == null || !pwd.equals(merchantUser.getLoginPwd())){
            throw new LJShopException(ErrorCode.USER_NOT_EXIST,"账号或密码错误");
        }
        BaseUser baseUser = new BaseUser();
        baseUser.setId(merchantUser.getId());
        baseUser.setUserName(merchantUser.getUserAccount());
        // 设置session
        SessionUtil.set("baseUser", baseUser);
    }

    @RequestMapping("/register")
    public void register(@RequestParam String userAccount,String pwd){
        LOGGER.info("/merchantUser/register入参[userAccount={}]",userAccount);
        TMerchantUser merchantUser = merchantUserService.findMerchantUserByAccount(userAccount);
        if (merchantUser != null){
            throw new LJShopException(ErrorCode.INVALID_DATA,"这个名字已经被人抢先一步了哟");
        }
        merchantUser = new TMerchantUser();
        merchantUser.setUserAccount(userAccount);
        merchantUser.setLoginPwd(pwd);
        merchantUserService.insertMerchantUser(merchantUser);
    }

    @RequestMapping("/forgetpwd")
    public void forgetpwd(@RequestParam String userAccount,String pwd){
        LOGGER.info("/merchantUser/forgetpwd入参[userAccount={}]",userAccount);
        TMerchantUser merchantUser = merchantUserService.findMerchantUserByAccount(userAccount);
        if (merchantUser == null){
            throw new LJShopException(ErrorCode.INVALID_DATA,"用户名不存在");
        }
        merchantUserService.updatePwd(merchantUser.getId(),pwd);
    }

    @RequestMapping(value = "/logout")
    public void logout() {
        //  清除session
        HttpSession session = SessionUtil.getHttpSession();
        session.removeAttribute("baseUser");
        session.invalidate();
        ThreadLocalUtil.removeAccount();
    }

    @RequestMapping(value = "/getCurUser")
    public String getCurUser() {
        BaseUser baseUser = (BaseUser) SessionUtil.getHttpSession().getAttribute("baseUser");
        if (baseUser == null){
            throw new LJShopException(ErrorCode.UNLOGIN_ERROR,"登录已失效请先登录");
        }
        return baseUser.getUserName();
    }
}
