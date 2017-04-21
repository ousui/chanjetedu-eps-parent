package com.chanjet.edu.eps.common.springconfig.beans;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Configuration
public class Converters {

	@Bean(name = "converter.json")
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		List supportedMediaTypes = Lists.newArrayList();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(new MediaType("application", "javascript"));
		supportedMediaTypes.add(new MediaType("application", "jsonp"));
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}

	@Bean(name = "converter.text")
	public ObjectToStringHttpMessageConverter objectToStringHttpMessageConverter() {
		ObjectToStringHttpMessageConverter converter = new ObjectToStringHttpMessageConverter(new DefaultFormattingConversionService(), Charsets.UTF_8);
		List supportedMediaTypes = Lists.newArrayList();
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		converter.setWriteAcceptCharset(false);
		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}
}
