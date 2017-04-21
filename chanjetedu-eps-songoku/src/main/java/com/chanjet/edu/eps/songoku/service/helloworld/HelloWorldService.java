package com.chanjet.edu.eps.songoku.service.helloworld;

import com.chanjet.edu.eps.dal.domain.HelloWorld;

import java.util.List;

/**
 * Created by shuai.w on 2016/5/30.
 */
public interface HelloWorldService {

	List<HelloWorld> listall();

	HelloWorld findById(int id);

	HelloWorld findByIdWithVm(int id);

	HelloWorld create(HelloWorld helloWorld);
}
