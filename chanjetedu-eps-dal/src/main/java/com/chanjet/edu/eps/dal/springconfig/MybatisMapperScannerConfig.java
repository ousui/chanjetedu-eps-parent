package com.chanjet.edu.eps.dal.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 不使用注解的方式，是为了使用自定义的 mapper scanner
 * 默认加载优先，所以需要把这个配置类独立
 *
 * @MapperScan(basePackages = {"com.chanjet.edu.eps.dal.mapper"})
 * Created by shuai.w on 2016/5/27.
 */
@Configuration
public class MybatisMapperScannerConfig {

	@Bean
	@Lazy // 使用懒加载的方式，在其他类访问的时候加载，确保其他类的优先级。
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.chanjet.edu.eps.dal.mapper");
		return msc;
	}
}
