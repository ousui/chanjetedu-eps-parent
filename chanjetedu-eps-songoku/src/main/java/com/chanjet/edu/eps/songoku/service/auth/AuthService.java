package com.chanjet.edu.eps.songoku.service.auth;

import com.chanjet.edu.eps.dal.domain.Module;
import com.chanjet.edu.eps.dal.domain.User;

import java.util.List;

/**
 * Created by shuai.w on 2016/6/3.
 */
public interface AuthService {

	/**
	 * 根据用户名查找用户
	 *
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * 按照邮箱查找
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * 按照电话查找
	 * @param mobile
	 * @return
	 */
	User findByMobile(long mobile);

	/**
	 * 按照登录名模糊查找
	 * @param loginName 登陆名：用户名/邮箱/电话
	 * @return
	 */
	User findByLoginName(String loginName);

	List<Module> selectAllModules();

}
