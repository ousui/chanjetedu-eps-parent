package com.chanjet.edu.eps.songoku.spring.config;

import com.chanjet.edu.framework.spring.web.servlet.view.velocity.VelocityToolsView;
import com.google.common.base.Charsets;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
public class SpringMvcVelocityConfig {
	@Bean
	public VelocityConfigurer velocityConfigurer() throws IOException {
		VelocityConfigurer bean = new VelocityConfigurer();
		bean.setResourceLoaderPath("classpath:/velocity/");

		Properties props = new Properties();
		props.setProperty(RuntimeConstants.INPUT_ENCODING, Charsets.UTF_8.name());
		props.setProperty(RuntimeConstants.OUTPUT_ENCODING, Charsets.UTF_8.name());
		bean.setVelocityProperties(props);
		return bean;
	}

	@Bean
	public ViewResolver velocityLayoutViewResolver() {
		VelocityLayoutViewResolver bean = new VelocityLayoutViewResolver();

		bean.setToolboxConfigLocation("/velocity/tools.xml");
		bean.setViewClass(VelocityToolsView.class);
//		bean.set

		bean.setLayoutUrl("/frame/layout.vm");

		bean.setCache(false);
//		vvr.setCache(true);

		bean.setPrefix("/views/"); // 访问前缀
		bean.setSuffix(".vm");
		bean.setDateToolAttribute("date");
		bean.setNumberToolAttribute("number");

		bean.setContentType(MediaType.TEXT_HTML_VALUE);
		//是否使用spring对宏定义的支持
		bean.setExposeSpringMacroHelpers(true);
		// 是否开放request属性
		bean.setExposeRequestAttributes(true);
		//request属性引用名称
		bean.setRequestContextAttribute("ctx");
		return bean;
	}
}
