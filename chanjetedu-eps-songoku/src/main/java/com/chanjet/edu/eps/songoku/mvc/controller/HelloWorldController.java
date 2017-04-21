package com.chanjet.edu.eps.songoku.mvc.controller;

import com.chanjet.edu.eps.common.mvc.BaseController;
import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.chanjet.edu.eps.songoku.service.helloworld.HelloWorldService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by shuai.w on 2016/5/24.
 */
@Controller
@RequestMapping("/helloworld")
@RequiresAuthentication
public class HelloWorldController extends BaseController {


	@Resource
	private HelloWorldService helloWorldService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@RequiresAuthentication
	public void test(Model model) {
		model.addAttribute("all", helloWorldService.listall());
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView at(@PathVariable int id, ModelAndView mav) throws Exception {
		mav.addObject("hw", helloWorldService.findById(id));
		mav.setViewName("helloworld/at");
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HelloWorld at(@PathVariable int id) throws Exception {
		return helloWorldService.findByIdWithVm(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<HelloWorld> vi(@RequestBody HelloWorld helloWorld) throws Exception {
		return ResponseEntity.ok(helloWorldService.create(helloWorld));
	}
}
