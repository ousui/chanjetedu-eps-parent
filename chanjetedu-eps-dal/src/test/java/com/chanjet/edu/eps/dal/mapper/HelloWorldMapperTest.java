
package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.SpringAppConfigTest;
import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by shuai.w on 2016/5/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringAppConfigTest.class})
public class HelloWorldMapperTest {

	@Resource
	private HelloWorldMapper helloWorldMapper;

	@Test
	public void test() throws IOException {
		URL url = Resources.getResource("org/apache/velocity/runtime/defaults/*.properties");
		System.out.println(url);
		String content = Resources.toString(url, Charsets.UTF_8);
		System.out.println(content);
		System.out.println(Paths.get("a/bc", "pp.vm", "ded/", "\\asd", "d.vm").toString().replace('\\', '/'));
	}


	@Test
	public void selectByName() throws Exception {

		Map maps = Maps.newHashMap();
		maps.put("fields", "*");
		maps.put("table", "hello_world");
		maps.put("id_field", "id");
		maps.put("id", 1);
		maps.put("ips", Lists.newArrayList(1, 2, 3));
		System.err.println(helloWorldMapper.selectAll());
	}

	@Test
	public void selectByArgs() throws Exception {
//		helloWorldMapper.selectOne(HelloWorld)
		HelloWorld hw = new HelloWorld();
		hw.setKey("1");
		System.err.println(helloWorldMapper.select(hw));
		System.err.println(helloWorldMapper.selectByVelocity(2));
		System.err.println(helloWorldMapper.selectByVmTpl(3));
	}

	@Test
	public void insert() {
		HelloWorld hw = new HelloWorld();
		hw.setKey("testinsert");
		hw.setValue("testinsert");
		helloWorldMapper.insert(hw);
		System.out.println(hw.getId());

		System.out.println(helloWorldMapper.selectByPrimaryKey(hw.getId()));
	}
}