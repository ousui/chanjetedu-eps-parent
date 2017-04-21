package com.chanjet.edu.eps.api.spring.config;

import com.chanjet.edu.eps.common.springconfig.beans.Converters;
import com.chanjet.edu.eps.common.springconfig.beans.PropertySources;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
@ComponentScan(basePackages = {"com.chanjet.edu.eps.api.mvc"}) // 扫描 controller 所在包
@Import({Converters.class, PropertySources.class})
public class SpringMvcConfig extends WebMvcConfigurationSupport {

	@Resource
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Resource
	private ObjectToStringHttpMessageConverter objectToStringHttpMessageConverter;

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		converters.add(mappingJackson2HttpMessageConverter);

		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
//		converters.add(new SourceHttpMessageConverter<>());
//		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(objectToStringHttpMessageConverter);
		converters.add(mappingJackson2HttpMessageConverter);
	}
}
