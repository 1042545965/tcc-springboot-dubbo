package com.tcc.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisConfig {

    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;
 
    @Value("${spring.redis.port}")
    private int port;
 
    @Value("${spring.redis.timeout}")
    private int timeout;
 
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
 
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWaitMillis;
 
    @Value("${spring.redis.password}")
    private String password;

 
    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        logger.info("JedisPool注入成功！！");
        logger.info("redis地址：" + host + ":" + port);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted(true);
        // 是否启用pool的jmx管理功能, 默认true
        jedisPoolConfig.setJmxEnabled(true);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        return jedisPool;
    }

    @Bean
    public RedisUtil getRedisUtil()  throws Exception{
        return new RedisUtil();
    }
 
}