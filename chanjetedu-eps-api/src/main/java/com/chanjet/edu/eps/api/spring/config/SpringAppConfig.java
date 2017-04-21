package com.chanjet.edu.eps.api.spring.config;

import com.chanjet.edu.eps.common.springconfig.beans.Caches;
import com.chanjet.edu.eps.common.springconfig.beans.Converters;
import com.chanjet.edu.eps.common.springconfig.beans.PropertySources;
import com.chanjet.edu.eps.dal.springconfig.MybatisConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
@ComponentScan(basePackages = {"com.chanjet.edu.eps.api.service"})
@Import({PropertySources.class, MybatisConfig.class})
//@EnableCaching(proxyTargetClass = true)
//@CacheConfig(cacheManager = "cache.guava")
public class SpringAppConfig {


}

