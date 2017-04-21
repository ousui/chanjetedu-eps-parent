package com.chanjet.edu.eps.dal;

import com.chanjet.edu.eps.dal.springconfig.MybatisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Configuration
@Import(MybatisConfig.class)
public class SpringAppConfigTest {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
