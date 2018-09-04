package com.lj.service.cache;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author xiezhenglong 2016-09-09
 *
 */
@Component
public class RedisUtil {
    
    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    public static final String HOTKEY = "LJ_HOT_KEY";

    @Autowired
    public JedisCluster jedis;
    
    /**
     * Set the string value as value of the key. The string can't be longer than 1073741824 bytes (1GB).
     * 
     * @param key
     * @param object
     * @param nxxx NX|XX,   NX -- Only set the key if it does not already exist. 
     *                      XX -- Only set the key if it already exist.
     * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     * @param time expire time in the units of <code>expx</code>
     * @return Status code reply
     */
    public String setObject(String key, Object object, String nxxx, String expx, long time) {
        String value = JSON.toJSONString(object);
        return jedis.set(key, value, nxxx, expx, time);
    }

    public String setString(String key, String value, String nxxx, String expx, long time) {
        return jedis.set(key, value, nxxx, expx, time);
    }
    
    public String setObject(String key, Object object) {
        String value = JSON.toJSONString(object);
        return this.setString(key, value);
    }
    
    public Long setObjectAndExpire(String key, Object object, int seconds) {
        setObject(key, object);
        return expire(key, seconds);
    }
    
    public Long setObjectAndExpireAt(String key, Object object, long unixTime) {
        setObject(key, object);
        return expireAt(key, unixTime);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        String value = getString(key);
        T ret = JSON.parseObject(value, clazz);
        return ret;
    }
    
    public String getString(String key) {
        return jedis.get(key);
    }
    
    public long del(String key) {
        return jedis.del(key);
    }

    public String setString(String key, String value) {
        return jedis.set(key, value);
    }

    public <T> List<T> getArray(String key, Class<T> clazz) {
        String value = getString(key);
        List<T> ret = JSON.parseArray(value, clazz);
        return ret;
    }
    
    public Long expire(String key, int seconds) {
        return jedis.expire(key, seconds);
    }
    
    public Long expireAt(String key, long unixTime) {
        return jedis.expireAt(key, unixTime);
    }
    
    public long ttl(String key) {
        return jedis.ttl(key);
    }
    
    public boolean exists(String key) {
        return jedis.exists(key);
    }
    
    public long incr(String key) {
        return jedis.incr(key);
    }

    /**
     * 加锁
     * 
     * @param keyname $name 锁的标识名
     * @param timeoutMs 循环获取锁的等待超时时间(毫秒)，在此时间内会一直尝试获取锁直到超时，为0表示失败后直接返回不等待
     * @param  expireMs 当前锁的最大生存时间(毫秒)，必须大于0，如果超过生存时间锁仍未被释放，则系统会自动强制释放
     * @param waitIntervalMs 获取锁失败后挂起再试的时间间隔(毫秒)
     * @return [type] [description]
     */
    public long loopLock(String keyname, long timeoutMs, long expireMs, long waitIntervalMs) {
        if (keyname == null || keyname.trim().length() == 0) {
            return 0;
        }

        logger.info("keyname:【{}】,timeout【{}ms】,expire【{}ms】,waitInterval【{}ms】",
            keyname,
            timeoutMs,
            expireMs,
            waitIntervalMs);
        
        long timeoutAt = System.currentTimeMillis();
        if (timeoutMs > 0) {
            timeoutAt = timeoutAt + timeoutMs;
        }
        
        long times = 0;
        while (true) {
            long now = System.currentTimeMillis();
            long expireAt = now + expireMs;
            
            String statusCode = setObject(keyname, String.valueOf(expireAt), "NX", "PX", expireMs);
            if ("OK".equals(statusCode)) {
                logger.info("keyname:【{}】success.", keyname);
                return expireAt;
            }
            
            if (timeoutMs <= 0 || timeoutAt < now) {
                logger.info("keyname:【{}】fail.", keyname);
                return 0;
            }
            
            try {
                Thread.sleep(waitIntervalMs);
            } catch (Exception e) {
                
            }
            logger.info("keyname:【{}】,times of:【{}】.", keyname, ++times);
        }
    }
    
    /**
     * 安全起见,增加一个5毫秒
     * 
     * @param name
     * @param expireAt
     */
    public void releaseLock(String name, long expireAt) {
        if (expireAt - System.currentTimeMillis() > 5) {
            del(name);
        }
    }

    public void addHotKey(String key){
        jedis.zincrby(HOTKEY,1,key);
    }

    /**
     * 通过大小获取热门搜索list
     * -1为全部
     * @param size
     * @return
     */
    public List<String> getHotKey(int size) {
        Set<String> list = jedis.zrevrange(HOTKEY, 0, size);
        return new ArrayList<String>(list);
    }
    
    /**
     * 累加
     * @param key
     * @param value
     */
    public void incrBy(String key,double value) {
    	jedis.incrByFloat(key, value) ;
    }
    
    /**
     * 累减
     * @param key
     * @param integer
     */
    public void decrBy(String key,long integer) {
    	jedis.decrBy(key, integer) ;
    	
    }

}
