package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.domain.Module;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Repository
public interface ModuleMapper extends Mapper<Module> {

	/**
	 * 查询所有模块，关联角色查询
	 *
	 * @return
	 */
	@Select("SELECT t.* FROM module t")
	@Results(value = {
			@Result(column = "id", property = "roles",
					many = @Many(select = "com.chanjet.edu.eps.dal.mapper.RoleMapper.selectByModuleId"))
	})
	List<Module> selectAllWithRoles();

}