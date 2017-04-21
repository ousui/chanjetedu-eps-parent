package com.chanjet.edu.eps.songoku.service.auth;

import com.chanjet.edu.eps.dal.domain.Module;
import com.chanjet.edu.eps.dal.domain.User;
import com.chanjet.edu.eps.dal.mapper.ModuleMapper;
import com.chanjet.edu.eps.dal.mapper.RoleMapper;
import com.chanjet.edu.eps.dal.mapper.UserMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shuai.w on 2016/6/3.
 */
@Service
public class AuthServiceImpl  implements AuthService {

	@Resource
	private UserMapper userMapper;

	@Resource
	private RoleMapper roleMapper;

	@Resource
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> selectAllModules() {
		return moduleMapper.selectAllWithRoles();
	}

	@Override
	@Cacheable(key = "#username")
	public User findByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return findRole(user);
	}

	@Override
	public User findByEmail(String email) {
		// 匹配是否为邮箱
		User user = new User();
		user.setEmail(email);
		return findRole(user);	}

	@Override
	public User findByMobile(long mobile) {
		// 匹配是否为手机号码
		User user = new User();
		user.setMobile(mobile);
		return findRole(user);
	}

	@Override
	@Cacheable(key = "#username")
	public User findByLoginName(String loginName) {
		return null;
	}

	private User findRole(User user) {
		user = userMapper.selectOne(user);
		if (user != null && user.getRoleId() != null) {
			user.setRole(roleMapper.selectByPrimaryKey(user.getRoleId()));
		}
		return user;
	}


}
