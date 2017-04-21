package com.chanjet.edu.eps.dal.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Data
@ToString(callSuper = true)
public abstract class IdentityTimestampEnabled extends IdentityTimestamp {

	/**
	 * 是否启用
	 */
	@Column(name = "ENABLED")
	private Boolean enabled;
}