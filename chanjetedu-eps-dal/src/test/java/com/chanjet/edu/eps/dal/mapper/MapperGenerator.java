package com.chanjet.edu.eps.dal.mapper;

import com.google.common.io.Resources;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuai.w on 2016/5/27.
 */
public class MapperGenerator {

	@Test
	public void generator() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		System.out.println(Resources.getResource("gen-mapper-config.xml"));
		InputStream is = MapperGenerator.class.getClassLoader().getResourceAsStream("gen-mapper-config.xml");
		if (is == null) {
			return;
		}
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(is);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
