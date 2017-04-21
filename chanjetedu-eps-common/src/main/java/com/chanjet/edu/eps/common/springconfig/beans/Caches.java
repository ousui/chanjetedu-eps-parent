package com.chanjet.edu.eps.common.springconfig.beans;

import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Configuration
@Import(RedisConfig.class)
public class Caches {

	@Bean(name = "cache.guava")
	public CacheManager cacheManagerGuava() {
		GuavaCacheManager cm = new GuavaCacheManager();
		cm.setAllowNullValues(true);
		return cm;
	}

	@Bean(name = "cache.redis")
	public CacheManager cacheManagerRedis(RedisTemplate redisTemplate) {
		RedisCacheManager cm = new RedisCacheManager(redisTemplate);
		return cm;
	}




}
