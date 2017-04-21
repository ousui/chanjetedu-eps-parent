package com.chanjet.edu.eps.api.mvc.restful;

import com.chanjet.edu.eps.api.service.helloworld.HelloWorldService;
import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by shuai.w on 2016/5/24.
 */
@RestController
@RequestMapping("/hw")
public class HelloWorldResource {

	@Resource
	private HelloWorldService helloWorldService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public HelloWorld create(@RequestParam("key") String key, @RequestParam("value") String value, @RequestParam(name = "e", defaultValue = "false") boolean e) throws Exception {
		HelloWorld helloWorld = new HelloWorld();
		helloWorld.setKey(key);
		helloWorld.setValue(value);
		helloWorldService.create(helloWorld, e);
		return helloWorld;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<HelloWorld> hw() throws Exception {
		return helloWorldService.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Map hwx(@PathVariable("id") int id) throws Exception {
		Map map = Maps.newHashMap();
		map.put("1", 999);
		map.put("BBS", helloWorldService.findWithVw(id));
		return map;
	}


	@RequestMapping(value = "/{id}/", method = RequestMethod.DELETE)
	public void removed(@PathVariable int id) throws Exception {
		helloWorldService.remove(id);
	}

	@ExceptionHandler(value = Exception.class)
	public Exception handlerException(Exception e) {
		return e;
	}

}
