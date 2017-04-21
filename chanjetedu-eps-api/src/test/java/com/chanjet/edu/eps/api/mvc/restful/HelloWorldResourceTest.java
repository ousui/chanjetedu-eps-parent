package com.chanjet.edu.eps.api.mvc.restful;

import com.chanjet.edu.eps.api.service.helloworld.HelloWorldService;
import com.chanjet.edu.eps.api.spring.config.SpringAppConfig;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by shuai.w on 2016/5/25.
 */

@ContextConfiguration(classes = { SpringAppConfig.class})
public class HelloWorldResourceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private HelloWorldService helloWorldService;

	@Test
	public void remove() {
		System.err.println(helloWorldService.findAll());
		helloWorldService.remove(1);
		System.err.println(helloWorldService.findAll());
	}

	@Test
	public void selectVm() {
		System.err.println(helloWorldService.findWithVw(1));
	}

}