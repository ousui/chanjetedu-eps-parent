package com.chanjet.edu.eps.songoku.mvc;

import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shuai.w on 2016/6/16.
 */
@ControllerAdvice
public class ExceptionAdvice {


	@ExceptionHandler(value = ShiroException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ModelAndView unauthorized(NativeWebRequest request, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("_ex", e);
		mav.setViewName("../frame/error/401");
		return mav; //返回一个逻辑视图名
	}

	@ExceptionHandler(value = {RuntimeException.class, Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView internalServerError(NativeWebRequest request, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("_ex", e);
		mav.setViewName("../frame/error/500");
		return mav; //返回一个逻辑视图名
	}


}
