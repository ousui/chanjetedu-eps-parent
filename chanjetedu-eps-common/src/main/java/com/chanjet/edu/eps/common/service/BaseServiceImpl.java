package com.chanjet.edu.eps.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shuai.w on 2016/6/1.
 */
@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
}
