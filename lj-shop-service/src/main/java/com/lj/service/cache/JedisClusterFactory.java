package com.lj.service.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by xiaoshen.wang on 2018/7/5
 */
@Repository
public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
    private InputStream is;
    private String addressKeyPrefix ;
    private JedisCluster jedisCluster;
    private Integer timeout;
    private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;

    private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

    @Override
    public JedisCluster getObject() throws Exception {
        return jedisCluster;
    }

    @Override
    public Class<? extends JedisCluster> getObjectType() {
        return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private Set<HostAndPort> parseHostAndPort() throws Exception {
        try {
            Properties prop = new Properties();
            prop.load(is);

            Set<HostAndPort> haps = new HashSet<HostAndPort>();
            for (Object key : prop.keySet()) {
                if (!((String) key).startsWith(addressKeyPrefix)) {
                    continue;
                }
                String val = (String) prop.get(key);
                boolean isIpPort = p.matcher(val).matches();
                if (!isIpPort) {
                    throw new IllegalArgumentException("ip 或 port 不合法");
                }
                String[] ipAndPort = val.split(":");
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                haps.add(hap);
            }
            return haps;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new Exception("解析 jedis 配置文件失败", ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        is = getClass().getResourceAsStream("/conf/redis.properties");
        addressKeyPrefix = "cache.nodes";
        timeout = 300000;
        maxRedirections = 6;
        genericObjectPoolConfig = new GenericObjectPoolConfig();

        genericObjectPoolConfig.setMaxIdle(20);//#允许最大空闲对象数
        genericObjectPoolConfig.setMaxWaitMillis(5000);//#允许最大等待时间毫秒数
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(1800000);//#被空闲对象回收器回收前在池中保持空闲状态的最小时间毫秒数
        genericObjectPoolConfig.setMinIdle(2);//#允许最小空闲对象数
        genericObjectPoolConfig.setTestOnBorrow(false);//#指明是否在从池中取出对象前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.
        genericObjectPoolConfig.setTestOnCreate(false);
        genericObjectPoolConfig.setTestOnReturn(false);//#指明是否在归还到池中前进行检验
        genericObjectPoolConfig.setTestWhileIdle(true);//#指明连接是否被空闲连接回收器(如果有)进行检验.如果检测失败,则连接将被从池中去除.
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(10000);//#在空闲连接回收器线程运行期间休眠的时间毫秒数. 如果设置为非正数,则不运行空闲连接回收器线程
        genericObjectPoolConfig.setMaxTotal(1500);//#允许最大活动对象数
        genericObjectPoolConfig.setNumTestsPerEvictionRun(1);//#设定在进行后台对象清理时，每次检查对象数
        genericObjectPoolConfig.setLifo(true);//#设置后进先出的池策略

        Set<HostAndPort> haps = this.parseHostAndPort();
        jedisCluster = new JedisCluster(haps, timeout, maxRedirections,genericObjectPoolConfig);
    }
}
