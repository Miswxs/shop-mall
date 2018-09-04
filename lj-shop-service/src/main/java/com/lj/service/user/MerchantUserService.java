package com.lj.service.user;

import com.lj.db.mapper.TMerchantUserMapper;
import com.lj.db.model.TMerchantUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MerchantUserService.class);

    @Autowired
    private TMerchantUserMapper merchantUserMapper;

    /**
     * 通过账号查商户用户
     * @param userAccount
     * @return
     */
    public TMerchantUser findMerchantUserByAccount(String userAccount){
        TMerchantUser merchantUser =merchantUserMapper.findMerchantUserByAccount(userAccount);
        return merchantUser;
    }

    /**
     * 插入
     * @param merchantUser
     */
    public void insertMerchantUser(TMerchantUser merchantUser){
        merchantUserMapper.insert(merchantUser);
    }

    public void updatePwd(Long userId,String pwd){
        merchantUserMapper.updatePwd(userId,pwd);
    }
}
