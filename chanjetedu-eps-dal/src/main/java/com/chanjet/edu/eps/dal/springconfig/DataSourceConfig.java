package com.chanjet.edu.eps.dal.springconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.chanjet.edu.framework.base.io.EncryptString;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
@PropertySource(value = "classpath:conf/db.properties") // 只加载 jdbc 相关的属性
public class DataSourceConfig {
	@Autowired
	Environment env;

	@Value(value = "${db.url}")
	private String url;
	@Value(value = "${db.username}")
	private String username;
	@Value(value = "${db.password}")
	private EncryptString password;
//	private String password;
	@Value(value = "${db.driver}")
	private String driverClass;

	@Value(value = "${db.maxConnTime}")
	private long maxConnTime;

	@Value(value = "${db.minIdlePoolSize}")
	private int minIdlePoolSize;

	private final static String DEFAULT_TEST_SQL = "SELECT 1";

	@Bean(name = "data.source.druid", destroyMethod = "close")
	public DataSource dataSourceDruid() {
		DruidDataSource ds = new DruidDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password.toString());

		ds.setValidationQuery(env.getProperty("db.druid.testSql", DEFAULT_TEST_SQL));

		ds.setDefaultCatalog("eps");
//		<!-- 配置初始化大小、最小、最大 -->
		ds.setMinIdle(minIdlePoolSize);
		ds.setMaxWait(maxConnTime);

		ds.setInitialSize(env.getProperty("db.druid.initSize", Integer.class));
		ds.setMaxActive(env.getProperty("db.druid.maxActive", Integer.class));
//		间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		ds.setTimeBetweenEvictionRunsMillis(env.getProperty("db.druid.timeBetweenEvictionRuns", Long.class));
//		配置一个连接在池中最小生存的时间，单位是毫秒
		ds.setMinEvictableIdleTimeMillis(env.getProperty("db.druid.minEvictableIdleTime", Long.class));
		ds.setTestWhileIdle(true);
		return ds;
	}

	@Bean(name = "data.source.hikari", destroyMethod = "close")
	public DataSource dataSourceHikara() {
		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName(driverClass);
		ds.setJdbcUrl(url);
		ds.setUsername(username);
		ds.setPassword(password.toString());
		ds.setCatalog("eps");
		ds.setConnectionInitSql(env.getProperty("db.hikari.testSql", DEFAULT_TEST_SQL));
		ds.setConnectionTestQuery(env.getProperty("db.hikari.testSql", DEFAULT_TEST_SQL));

//		等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒
		ds.setConnectionTimeout(maxConnTime);

		// 一个连接idle状态的最大时长（毫秒），超时则被释放（retired），缺省:10分钟`
		ds.setIdleTimeout(env.getProperty("db.hikari.idleTimeout", Long.class));
//		一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒，参考MySQL wait_timeout参数（show variables like '%timeout%';） -->
		ds.setMaxLifetime(env.getProperty("db.hikari.maxLifetime", Long.class));
//		连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count)
		ds.setMaximumPoolSize(env.getProperty("db.hikari.maxiPoolSize", Integer.class));
//		最小空闲数量
		ds.setMinimumIdle(minIdlePoolSize);

		return ds;
	}

}
