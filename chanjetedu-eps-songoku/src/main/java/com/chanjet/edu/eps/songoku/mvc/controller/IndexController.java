package com.chanjet.edu.eps.songoku.mvc.controller;

import com.chanjet.edu.eps.common.mvc.BaseController;
import com.chanjet.edu.eps.dal.mapper.ModuleMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@Resource
	ModuleMapper moduleMapper;

	@Resource
	Environment environment;

	@Resource(name = "cache.manager")
	@Lazy
	CacheManager cacheManager;

	@RequestMapping("/")
	public void index() {
		System.err.println(":       "+environment);
		System.out.println(environment.acceptsProfiles("LOCAL"));

		System.out.println("cacheManager = " + cacheManager);

	}


}
