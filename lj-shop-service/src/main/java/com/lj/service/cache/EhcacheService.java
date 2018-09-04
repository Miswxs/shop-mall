package com.lj.service.cache;

import com.lj.common.util.EhcacheUtil;
import com.lj.domain.dto.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Cacheable 先查缓存，没有则插入
 * @CacheEvict 清空缓存
 */
@Service
public class EhcacheService {

    @Autowired
    private EhcacheUtil ehcacheUtil;

    /**
     * key-value: userId-token
     * @param userId
     * @return
     */
    @Cacheable(value = "userTokenCache" ,key = "#userId")
    public String getToken(String userId){
        String token = ehcacheUtil.createToken(userId);
        return token;
    }

    /**
     * key-value : token-BaseUser
     * @param token
     * @param baseUser
     * @return
     */
    @Cacheable(value = "userTokenCache" ,key = "#p0")
    public BaseUser getBaseUser(String token, BaseUser baseUser){
        return baseUser;
    }

    /**
     * 通过userId清空缓存
     * @param userId
     */
    @CacheEvict(value = "userTokenCache" ,key = "#p0")
    public void clearByUserId(String userId){
        return;
    }

    /**
     * 通过token清空缓存
     * @param token
     */
    @CacheEvict(value = "userTokenCache" ,key = "#p0")
    public void clearByToken(String token){
        return;
    }
}
