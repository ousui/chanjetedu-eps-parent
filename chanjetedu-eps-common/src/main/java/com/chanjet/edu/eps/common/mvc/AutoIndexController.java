package com.chanjet.edu.eps.common.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 自动导向页面的 controller，继承这个 controller 的 controller，可以不用写 requestmapping，即可导向。
 */
public abstract class AutoIndexController extends BaseController {

	@RequestMapping(value = "/**", method = RequestMethod.GET)
	public void index() {
	}

}
