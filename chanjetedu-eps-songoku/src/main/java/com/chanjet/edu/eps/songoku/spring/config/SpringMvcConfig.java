package com.chanjet.edu.eps.songoku.spring.config;

import com.chanjet.edu.eps.common.springconfig.beans.Converters;
import com.chanjet.edu.eps.common.springconfig.beans.PropertySources;
import com.chanjet.edu.framework.spring.web.servlet.handler.IndexMappingSupportHandlerInterceptor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shuai.w on 2016/5/24.
 */
//@EnableWebMvc 在继承了 li a{@link WebMvcConfigurationSupport} 的时候，是不需要这个注解的。
@Configuration
@ComponentScan(basePackages = {"com.chanjet.edu.eps.songoku.mvc"}) // 扫描 controller 所在包
@Import({ PropertySources.class, Converters.class})
public class SpringMvcConfig extends WebMvcConfigurationSupport {
	private static Logger logger = LoggerFactory.getLogger(SpringMvcConfig.class);

	@Resource
	private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Resource
	private ObjectToStringHttpMessageConverter objectToStringHttpMessageConverter;

	@Override
	protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
//		converters.add(new SourceHttpMessageConverter<>());
//		converters.add(new AllEncompassingFormHttpMessageConverter());
		converters.add(objectToStringHttpMessageConverter);
		converters.add(mappingJackson2HttpMessageConverter);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		logger.debug("注册自动索引拦截器。");
		IndexMappingSupportHandlerInterceptor i = new IndexMappingSupportHandlerInterceptor();
		i.addMapping(".*/$", "index"); // path/to/uri/ -> path/to/uri/index
		i.addMapping(".*/\\d+$", "at"); // path/to/uri/15 -> path/to/uri/at
		registry.addInterceptor(i);
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);

		logger.debug("注册静态资源路径。");
		// 将 /static/ 目录重定位到 /static/
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/").setCachePeriod(365 * 24 * 60 * 60);
	}

	/*-------------- shiro 相关 ----------------------*/
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor bean = new AuthorizationAttributeSourceAdvisor();
		bean.setSecurityManager(securityManager);
		return bean;
	}


}
