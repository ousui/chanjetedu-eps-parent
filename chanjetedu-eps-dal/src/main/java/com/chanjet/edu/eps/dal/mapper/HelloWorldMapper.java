package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.domain.HelloWorld;
import com.chanjet.edu.framework.mybatis.scripting.velocity.VelocityLanguageDriver;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Repository
public interface HelloWorldMapper extends Mapper<HelloWorld> {

	@Lang(VelocityLanguageDriver.class)
	@Select("SELECT * FROM hello_world t where t.id = @{id}")
	HelloWorld selectByVelocity(@Param("id") int id);

	@Lang(VelocityLanguageDriver.class)
	@Select("hello_world.vm")
	List<HelloWorld> selectByVmTpl(int gtId);


}