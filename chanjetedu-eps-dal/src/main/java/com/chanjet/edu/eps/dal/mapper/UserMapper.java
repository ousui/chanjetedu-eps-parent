package com.chanjet.edu.eps.dal.mapper;

import com.chanjet.edu.eps.dal.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

}