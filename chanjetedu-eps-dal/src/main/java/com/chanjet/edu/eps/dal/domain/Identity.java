package com.chanjet.edu.eps.dal.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Data
public abstract class Identity implements Serializable {

	//	如果需要依靠系统程序生成 id，则需要设置生成值。
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}