package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.domain.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role> {

	@Select("SELECT t.* FROM " +
			"role t LEFT JOIN module_role a ON t.id = a.role_id " +
			"WHERE a.module_id = #{id}")
	List<Role> selectByModuleId(Integer id);
}