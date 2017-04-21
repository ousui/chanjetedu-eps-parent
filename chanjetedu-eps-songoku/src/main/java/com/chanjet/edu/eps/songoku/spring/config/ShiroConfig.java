package com.chanjet.edu.eps.songoku.spring.config;

import com.chanjet.edu.eps.dal.domain.Role;
import com.chanjet.edu.eps.songoku.shiro.SongokuFilterChainDefinitionsService;
import com.chanjet.edu.eps.songoku.shiro.UserRealm;
import com.chanjet.edu.framework.spring.shiro.cache.SpringCacheManagerWrapper;
import com.chanjet.edu.framework.spring.shiro.web.AbstractFilterChainDefinitionsService;
import com.chanjet.edu.framework.spring.shiro.web.filter.AnyRolesAllowedFilter;
import com.chanjet.edu.framework.spring.shiro.web.filter.LastLoginAuthenticationFilter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by shuai.w on 2016/6/1.
 */
@Configuration
public class ShiroConfig {

	private static final String HASH_ALGORITHM = Md5Hash.ALGORITHM_NAME;

	private static final int HASH_ITERATIONS = 1000;

	@Bean
	public AbstractFilterChainDefinitionsService filterChainDefinitionsService(@Qualifier("shiro.factory") FactoryBean shiroFilterFactoryBean) {
		SongokuFilterChainDefinitionsService bean = new SongokuFilterChainDefinitionsService();
		bean.setShiroFilterFactoryBean((ShiroFilterFactoryBean) shiroFilterFactoryBean);
		bean.setRoleFilterName(AnyRolesAllowedFilter.FILTER_NAME);

		Map<String, String> filterChainMap = Maps.newLinkedHashMap();
		filterChainMap.put("/*", DefaultFilter.anon.name()); // 根目录有匿名权限
		filterChainMap.put("/resources/**", DefaultFilter.anon.name()); // 静态资源，系统模块不可以使用 resources 目录
		filterChainMap.put("/public/**", DefaultFilter.anon.name()); // 公共模块，对外开放
		filterChainMap.put("/internal/**", DefaultFilter.anon.name()); // 公共模块，对外开放

		filterChainMap.put("/auth/login", LastLoginAuthenticationFilter.FILTER_NAME); // 登陆
//		filterChainMap.put("/auth/login", DefaultFilter.authc.name()); // 登陆
		filterChainMap.put("/auth/logout", DefaultFilter.logout.name()); // 登出
		filterChainMap.put("/auth/unauthorized", DefaultFilter.anon.name()); // 未认证

		bean.setDefaultfilterChain(filterChainMap);
		return bean;
	}

	@Bean(name = "shiro.factory")
	public FactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

		Map filters = Maps.newHashMap();
		AnyRolesAllowedFilter filterAr = new AnyRolesAllowedFilter();
		filterAr.setAlwaysAllowRoles(Lists.newArrayList(Role.CODE_SONGOKU));
		filters.put(AnyRolesAllowedFilter.FILTER_NAME, filterAr);

		LastLoginAuthenticationFilter filterLLA = new LastLoginAuthenticationFilter();
		filterLLA.addExceptionMapping(IncorrectCredentialsException.class, "密码输入错误！");

		filters.put(LastLoginAuthenticationFilter.FILTER_NAME, filterLLA);
		bean.setFilters(filters);

		bean.setSecurityManager(securityManager);

		bean.setLoginUrl("/auth/login");
		bean.setSuccessUrl("/auth/success");
		bean.setUnauthorizedUrl("/auth/unauthorized");

		SecurityUtils.setSecurityManager(securityManager);
		return bean;
	}


	@Bean
	public SecurityManager securityManager(Realm realm, SessionManager sessionManager, CacheManager cacheManager, RememberMeManager rememberMeManager) {
		DefaultWebSecurityManager bean = new DefaultWebSecurityManager();
//		bean.set
		bean.setSessionManager(sessionManager);
		bean.setRealm(realm);
		// 页面上 remember Me 管理器
		bean.setRememberMeManager(rememberMeManager);
		// 支持多种认证方式同时进行，系统实现无需过于复杂，只用一种即可。
//		bean.setRealms();
		// 由 spring 统一进行 cache 进行管理
//		bean.setCacheManager(cacheManager);
		return bean;
	}

	@Bean
	public SessionManager sessionManager(CacheManager cacheManager) {
		DefaultWebSessionManager bean = new DefaultWebSessionManager();
//		bean.setSessionDAO();
		bean.setGlobalSessionTimeout(1800000);
		bean.setDeleteInvalidSessions(true);
//		bean.setSessionValidationSchedulerEnabled(true);
		bean.setSessionIdCookieEnabled(true);
//		bean.setCacheManager(cacheManager);
		SimpleCookie cookie = new SimpleCookie("sid");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(180000);

		bean.setSessionIdCookie(cookie);
		return bean;
	}


	@Bean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager bean = new CookieRememberMeManager();
		return bean;
	}

	@Bean
	public Realm realm(CacheManager cacheManager, CredentialsMatcher matcher) {
		UserRealm bean = new UserRealm();
//		bean.setCacheManager(cacheManager);
		bean.setCachingEnabled(false);
		bean.setAuthorizationCachingEnabled(false);
		bean.setAuthenticationTokenClass(UsernamePasswordToken.class);

		bean.setCredentialsMatcher(matcher);
		return bean;
	}

	@Bean
	public CredentialsMatcher credentialsMatcher(CacheManager cacheManager) {
		HashedCredentialsMatcher bean = new HashedCredentialsMatcher();
//		RetryLimitCredentialsMatcher bean = new RetryLimitCredentialsMatcher(cacheManager);
		bean.setStoredCredentialsHexEncoded(true); // 使用 16 进制存储
		bean.setHashIterations(HASH_ITERATIONS);
		bean.setHashAlgorithmName(HASH_ALGORITHM);
		return bean;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public SpringCacheManagerWrapper cacheManager(@Qualifier("cache.manager")
														  org.springframework.cache.CacheManager cacheManager) {
		SpringCacheManagerWrapper bean = new SpringCacheManagerWrapper(cacheManager);
		return bean;
	}
}
