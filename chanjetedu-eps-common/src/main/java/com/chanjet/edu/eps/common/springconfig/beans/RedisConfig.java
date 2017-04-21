package com.chanjet.edu.eps.common.springconfig.beans;

import com.chanjet.edu.framework.base.io.EncryptString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.text.ParseException;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Configuration
@PropertySource(value = "classpath:conf/cache.redis.properties")
public class RedisConfig {

	@Autowired
	Environment env;

	/**
	 * 当别名不清晰即存在多个别名的情况下，需要使用 @Qualifier 注解指定一个别名
	 * @param jedisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate bean = new RedisTemplate();

		bean.setConnectionFactory(jedisConnectionFactory);
		return bean;
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() throws ParseException {
		JedisConnectionFactory bean = new JedisConnectionFactory();

		bean.setHostName(env.getProperty("cache.redis.host"));
		bean.setPort(env.getProperty("cache.redis.port", int.class, 5432));
		bean.setTimeout(env.getProperty("cache.redis.timeout", int.class, 180000));
		bean.setDatabase(env.getProperty("cache.redis.database", int.class));
		bean.setPassword(new EncryptString(env.getProperty("cache.redis.password", "")).toString());

		boolean usePool = env.getProperty("cache.redis.usepool", boolean.class, false);
		if (usePool) {
			bean.setUsePool(usePool);

			JedisPoolConfig jpc = new JedisPoolConfig();
			jpc.setMaxWaitMillis(env.getProperty("cache.redis.pool.maxWait", long.class, 60000l));
			jpc.setMaxIdle(env.getProperty("cache.redis.pool.maxIdle", int.class, 12));
			jpc.setMaxTotal(env.getProperty("cache.redis.pool.maxTotal", int.class, 24));
			jpc.setMinIdle(env.getProperty("cache.redis.pool.minIdle", int.class, 2));
			bean.setPoolConfig(jpc);
		}
		return bean;
	}
}
