package com.chanjet.edu.eps.common.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by shuai.w on 2015/9/14 0014.
 */
public abstract class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected final String REDIRECT_PREFIX = "redirect:";

	public String redirect(String view){
		return REDIRECT_PREFIX + view;
	}



}
