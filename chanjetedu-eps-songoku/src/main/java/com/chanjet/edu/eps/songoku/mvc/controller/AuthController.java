package com.chanjet.edu.eps.songoku.mvc.controller;

import com.chanjet.edu.eps.common.mvc.AutoIndexController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by shuai.w on 2016/6/3.
 */
@Controller
@RequestMapping("/auth")
public class AuthController extends AutoIndexController {

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login() { // 使用 shiro 的拦截器进行 login 操作。
	}

}
