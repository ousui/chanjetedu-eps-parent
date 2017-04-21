package com.chanjet.edu.eps.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by shuai.w on 2016/6/8.
 */
@Data
@ToString(callSuper = true)
@Table(name = "role")
@EqualsAndHashCode
public class Role extends IdentityTimestampEnabled {

	public static final String CODE_SONGOKU = "SONGOKU";
	public static final String CODE_SPIRIT = "SPIRIT";

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

}
