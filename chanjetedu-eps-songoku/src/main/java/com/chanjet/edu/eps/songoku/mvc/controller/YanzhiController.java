package com.chanjet.edu.eps.songoku.mvc.controller;

import com.chanjet.edu.eps.common.mvc.AutoIndexController;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by shuai.w on 2016/6/16.
 */
@Controller
@RequestMapping("/yanzhi")
public class YanzhiController extends AutoIndexController {


	@RequestMapping("/")
	public void index(Model model) {
		List list = Lists.newArrayList();
		list.add("1");
		list.add("x");
		model.addAttribute("lists", list);
	}
}
