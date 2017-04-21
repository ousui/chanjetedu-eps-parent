package com.chanjet.edu.eps.songoku.mvc.controller;

import com.chanjet.edu.eps.common.mvc.AutoIndexController;
import com.chanjet.edu.framework.spring.shiro.web.AbstractFilterChainDefinitionsService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Controller
@RequestMapping("/internal")
public class InternalController extends AutoIndexController {

	@Resource
	AbstractFilterChainDefinitionsService authService;

	@RequestMapping(value = "/filters", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map filters() {
		return authService.getAllFilterChain();
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public String update() {
		authService.updateFilterChain();
		return "success";
	}

	@RequestMapping(value = "/ex", method = RequestMethod.GET)
	@ResponseBody
	public String exception(@RequestParam(name = "msg", defaultValue = "一个运行时异常！") String msg) {
		if (true) {
			throw new RuntimeException(msg);
		}
		return "success";
	}


}
