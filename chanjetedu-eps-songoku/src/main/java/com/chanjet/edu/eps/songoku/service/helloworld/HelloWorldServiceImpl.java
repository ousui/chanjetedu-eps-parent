package com.chanjet.edu.eps.songoku.service.helloworld;

import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.chanjet.edu.eps.dal.mapper.HelloWorldMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shuai.w on 2016/5/30.
 */
@Service
public class HelloWorldServiceImpl implements HelloWorldService {

	@Resource
	private HelloWorldMapper helloWorldMapper;

	@Override
	public List<HelloWorld> listall() {
		return helloWorldMapper.selectAll();
	}

	@Override
	public HelloWorld findById(int id) {
		return helloWorldMapper.selectByPrimaryKey(id);
	}

	@Override
	public HelloWorld findByIdWithVm(int id) {
		return helloWorldMapper.selectByVelocity(id);
	}

	@Override
	public HelloWorld create(HelloWorld helloWorld) {
		Assert.isNull(helloWorld.getId(), "创建时不能有id");
		Assert.hasText(helloWorld.getKey(), "输入key");
		Assert.hasText(helloWorld.getValue(), "输入 vlaue");
		helloWorldMapper.insert(helloWorld);
		return helloWorld;
	}
}
