package com.chanjet.edu.eps.songoku.shiro;

import com.chanjet.edu.eps.dal.domain.User;
import com.chanjet.edu.eps.songoku.service.auth.AuthService;
import com.chanjet.edu.framework.spring.shiro.crypto.PasswordHelper;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.AllPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by shuai.w on 2016/5/30.
 */
public class UserRealm extends AuthorizingRealm {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private AuthService authService;

//	public UserRealm(AuthService authService) {
//		this.authService = authService;
//	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.debug("开始进行用户认证：{}", token);
		UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
		// 缓存认证信息

		String username = uptoken.getUsername();
		Object password = uptoken.getPassword(); // 转换

		// TODO 判断用户名类型

		// if email

		// if phone

		// if username

		if (!StringUtils.hasText(username)) {
			throw new AccountException("用户名不能为空");
		}

		if (StringUtils.isEmpty(password)) {
			throw new AccountException("密码不能为空");
		}

		User user = authService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException("没有该用户[" + username + "]！");
		}
		if (!user.getEnabled()) {
			throw new DisabledAccountException("该用户[" + username + "]未启用！");
		}

		SimpleAuthenticationInfo ai = new SimpleAuthenticationInfo(
				user,
				user.getPassword(),
				getName()
		);
		// 使用 username 作为盐进行比较
		ai.setCredentialsSalt(PasswordHelper.i().buildSalt(user.getUsername()));
		return ai;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo ai = new SimpleAuthorizationInfo(Sets.newHashSet(user.getRole().getCode()));
		ai.setObjectPermissions(Sets.newHashSet(new AllPermission()));
		return ai;
	}

}