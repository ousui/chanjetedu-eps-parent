package com.chanjet.edu.eps.dal.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.sql.Timestamp;

/**
 * Created by shuai.w on 2016/5/25.
 */
@Data
@ToString(callSuper = true)
public abstract class IdentityTimestamp extends Identity {

	// 创建者
	@Column(name = "CREATER")
	private String creater;

	// 创建时间
	@Setter(value = AccessLevel.NONE)
	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;

	// 更新者
	@Column(name = "UPDATER")
	private String updater;

	// 更新时间
	@Setter(value = AccessLevel.NONE)
	@Column(name = "UPDATE_TIME", insertable = false)
	private Timestamp updateTime;
}