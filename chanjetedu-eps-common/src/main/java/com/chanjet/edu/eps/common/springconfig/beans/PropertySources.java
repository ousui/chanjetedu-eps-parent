package com.chanjet.edu.eps.common.springconfig.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Configuration
@PropertySource("classpath:conf/spring.properties")
public class PropertySources {

	/**
	 * 属性配置器，用于加载属性配置
	 *
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
