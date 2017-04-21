package com.chanjet.edu.eps.api.service.helloworld;

import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.chanjet.edu.eps.dal.mapper.HelloWorldMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HelloWorldServiceImpl implements HelloWorldService {

	@Resource
	private HelloWorldMapper helloWorldMapper;


	@Override
	public List<HelloWorld> findAll() {
		return helloWorldMapper.selectAll();
	}

	@Override
	public HelloWorld find(int id) {
		HelloWorld hw = new HelloWorld();
		hw.setId(id);
		return helloWorldMapper.selectOne(hw);
	}

	@Override
	public void remove(int id) {
		helloWorldMapper.deleteByPrimaryKey(id);
	}

	@Override
	public HelloWorld findWithVw(int id) {
		return helloWorldMapper.selectByVelocity(id);
	}

	@Override
	public HelloWorld create(HelloWorld helloWorld, boolean throwException) {
		helloWorldMapper.insert(helloWorld);
		if(throwException) {
			throw  new RuntimeException(helloWorld.toString());
		}
		return helloWorld;
	}

}
