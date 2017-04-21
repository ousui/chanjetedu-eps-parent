package com.chanjet.edu.eps.dal.springconfig;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Configuration
@ComponentScan(basePackages = {"com.chanjet.edu.eps.dal.dao", "com.chanjet.edu.eps.dal.mapper"}) // ide 有时候不认 mapper，则需要这样写
@Import({DataSourceConfig.class, MybatisMapperScannerConfig.class})
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(MybatisConfig.class);

	@Resource(name = "data.source.druid")
	private DataSource dataSource;

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource);
//		ssf.setTypeAliasesPackage("com.chanjet.edu.eps.dal.mapper");
		ssf.setTypeAliasesSuperType(Mapper.class);
		ssf.setTypeHandlersPackage("com.chanjet.edu.eps.dal.typehandler");
//		try {
//			ssf.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis-mapper/*.xml"));
//		} catch (IOException e) {
//			LOGGER.warn("未找到 mybatis sql mapper 文件！");
//		}

		ssf.setPlugins(new Interceptor[]{
		});

		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
//		config.setCacheEnabled(true);
		config.setMapUnderscoreToCamelCase(true);
		config.setUseGeneratedKeys(true);

		ssf.setConfiguration(config);
		return ssf;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager dataSourceTransactionManager() {
		DataSourceTransactionManager dstxm = new DataSourceTransactionManager();
		dstxm.setDataSource(dataSource);
		return dstxm;
	}

}
