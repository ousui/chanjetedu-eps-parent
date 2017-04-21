package com.chanjet.edu.eps.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@ToString(callSuper = true)
@Table(name = "user", catalog = "eps")
@EqualsAndHashCode
public class User extends IdentityTimestampEnabled {
	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "mobile", unique = true)
	private Long mobile;

	@Column(name = "role_id")
	private Integer roleId;

	@Transient
	private Role role;
}