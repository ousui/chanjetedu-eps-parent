package com.chanjet.edu.eps.api.service.helloworld;

import com.chanjet.edu.eps.dal.domain.HelloWorld;

import java.util.List;

/**
 * Created by shuai.w on 2016/5/25.
 */
public interface HelloWorldService {

	List<HelloWorld> findAll();

	HelloWorld find(int id);

	void remove(int id);

	HelloWorld findWithVw(int id);

	HelloWorld create(HelloWorld helloWorld, boolean throwExcetion);
}
