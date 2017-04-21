package com.chanjet.edu.eps.songoku.spring.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Configurable
public class Profiles {
	public static final String PROFILE_DEVL = "devl";
	public static final String PROFILE_TEST = "test";
	public static final String PROFILE_PROD = "prod";


	@Bean(name = "cache.manager")
	@Profile({PROFILE_DEVL})
	public CacheManager cacheManagerGuava(@Qualifier("cache.guava") CacheManager cacheManager) {
		System.err.println("CACHE MANAGER --------------- : " + cacheManager);
		return cacheManager;
	}

	@Profile({PROFILE_TEST, PROFILE_PROD})
	@Bean(name = "cache.manager")
	public CacheManager cacheManagerRedis(@Qualifier("cache.redis") CacheManager cacheManager) {
		System.err.println("CACHE MANAGER --------------- : " + cacheManager);
		return cacheManager;
	}

}

