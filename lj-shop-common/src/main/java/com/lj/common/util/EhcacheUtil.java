package com.lj.common.util;

import com.lj.common.util.spring.SpringContextUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

@Component
public class EhcacheUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EhcacheUtil.class);

    /**
     * 根据缓存名称获取缓存
     * @param cacheName
     * @return
     */
    public Cache getCache(String cacheName){
       EhCacheCacheManager ehCacheManager =SpringContextUtils.getBean("ehCacheManager");
       Cache cache = ehCacheManager.getCache(cacheName);
       return cache;
    }

    /**
     * 设置缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public void putCache(String cacheName, String key,Object value){
        EhCacheCacheManager ehCacheManager =SpringContextUtils.getBean("ehCacheManager");
        Cache cache = ehCacheManager.getCache(cacheName);
        cache.put(key,value);
    }

    /**
     * 生成token
     * @param userId
     * @return
     */
    public String createToken(String userId){
        String token = DigestUtils.sha256(RandomNum.createRandomString(16)+userId).toString();
        return token;
    }
}
