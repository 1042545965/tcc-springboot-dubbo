package com.tcc.elasticsearch;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * 应为将redisUtils 放入到api当中 ， 这个实体类需要被扫描到
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SearchProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchProviderApplication.class, args);
	}
}
