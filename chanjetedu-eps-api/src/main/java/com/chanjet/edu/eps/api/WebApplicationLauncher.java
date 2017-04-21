package com.chanjet.edu.eps.api;

import com.chanjet.edu.eps.api.spring.config.SpringAppConfig;
import com.chanjet.edu.eps.api.spring.config.SpringMvcConfig;
import com.chanjet.edu.framework.spring.web.servlet.support.SimpleDispatcherServletInitializer;
import com.google.common.base.Charsets;
import org.springframework.web.filter.CharacterEncodingFilter;

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

        return new Filter[]{characterEncodingFilter};
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
