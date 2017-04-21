package com.chanjet.edu.eps.dal.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by ousui on 16-6-10.
 */
@Data
@ToString(callSuper = true)
@Table(name = "MODULE")
@EqualsAndHashCode
public class Module extends IdentityTimestampEnabled {
	private String name;

	private String path;

	private String pathMatch;

	private Integer pid;

	@Transient
	private Module parent;

	@Transient
	private List<Role> roles;

}
