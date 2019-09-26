package com.tcc.redis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 应为将redisUtils 放入到api当中 ， 这个实体类需要被扫描到
 * 不需要有数据库配置 DataSourceAutoConfiguration  HibernateJpaAutoConfiguration
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.tcc.redis", "com.tcc.utils"})
public class CacheProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheProviderApplication.class, args);
    }
}
