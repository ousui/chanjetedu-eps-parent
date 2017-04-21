package com.chanjet.edu.eps.common.springconfig.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by shuai.w on 2016/6/1.
 */
@Configuration
@Import(Converters.class)
public class BeanTest {


	@Bean(name = "test1")
	public String test1(@Qualifier("converter.json2") MappingJackson2HttpMessageConverter converter) {
		System.out.println(converter);
		return "test1";
	}
}
