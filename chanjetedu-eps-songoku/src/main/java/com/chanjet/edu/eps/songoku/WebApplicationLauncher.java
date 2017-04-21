package com.chanjet.edu.eps.songoku;

import com.chanjet.edu.eps.songoku.spring.config.SpringAppConfig;
import com.chanjet.edu.eps.songoku.spring.config.SpringMvcConfig;
import com.chanjet.edu.framework.spring.web.servlet.support.SimpleDispatcherServletInitializer;
import com.google.common.base.Charsets;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;

/**
 * Created by shuai.w on 2016/5/24.
 */
public class WebApplicationLauncher extends SimpleDispatcherServletInitializer {

	@Override
	protected Filter[] getServletFilters() {
		// UTF-8 过滤
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(Charsets.UTF_8.name());
		characterEncodingFilter.setForceEncoding(true);

		// SHIRO 过滤
		DelegatingFilterProxy shiroFilter = new DelegatingFilterProxy();
		shiroFilter.setTargetBeanName("shiro.factory");
		shiroFilter.setTargetFilterLifecycle(true);
		return new Filter[]{characterEncodingFilter, shiroFilter};
	}

	@Override
	public String getSpringPropertiesFile() {
		return "conf/spring.properties";
	}

	@Override
	protected Class<?> getSpringAppConfig() {
		return SpringAppConfig.class;
	}

	@Override
	protected Class<?> getSpringMvcConfig() {
		return SpringMvcConfig.class;
	}

}
