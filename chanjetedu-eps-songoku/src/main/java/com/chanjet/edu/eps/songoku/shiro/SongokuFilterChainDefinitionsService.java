package com.chanjet.edu.eps.songoku.shiro;

import com.chanjet.edu.eps.dal.domain.Module;
import com.chanjet.edu.eps.dal.domain.Role;
import com.chanjet.edu.eps.songoku.service.auth.AuthService;
import com.chanjet.edu.framework.spring.shiro.web.AbstractFilterChainDefinitionsService;
import com.google.common.collect.Maps;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by shuai.w on 2016/6/19.
 */
public class SongokuFilterChainDefinitionsService extends AbstractFilterChainDefinitionsService {

	@Setter
	private String roleFilterName = "roles";

	@Resource
	private AuthService authService;


	@Override
	public Map<String, String> getCustomFilterChain() {
		List<Module> modules = authService.selectAllModules();
		logger.debug("all modules: {}", modules);
		Map filterChains = Maps.newLinkedHashMap();
		String match, formated;
		// 遍历所有模块
		for (Module module : modules) {
			match = module.getPathMatch();

			if (Strings.isBlank(match)) {
				match = module.getPath();
				if (!match.endsWith("/")) {
					match += "/";
				}
				match += "**";
			}

			formated = formatRolesString(module.getRoles());
			if(Strings.isNotBlank(formated)){
				// 遍历该模块匹配的角色
				filterChains.put(match, formated);
			}

		}
		return filterChains;
	}

	private String formatRolesString(Collection<Role> roles){
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 如果未關聯角色，则没有任何权限/只有超管权限
		if (CollectionUtils.isEmpty(roles)) {
			sb.append(Role.CODE_SONGOKU);
		} else {// 不为空，则将角色遍历后合并出来
			for (Role role : roles) {
				sb.append(",").append(role.getCode());
			}
			sb.deleteCharAt(0);
		}
		return MessageFormat.format(FILTER_TPL, this.roleFilterName, sb.toString());
	}
}
