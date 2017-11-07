package com.vacomall.act.bean;

import com.vacomall.act.entity.Role;

public class RoleBeen {
	private Role role;

	private Integer hasRole = 0;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getHasRole() {
		return hasRole;
	}

	public void setHasRole(Integer hasRole) {
		this.hasRole = hasRole;
	}

	public RoleBeen() {
	}

	public RoleBeen(Role role, Integer hasRole) {
		this.role = role;
		this.hasRole = hasRole;
	}
	
	
}
