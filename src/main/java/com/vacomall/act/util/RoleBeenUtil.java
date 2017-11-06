package com.vacomall.act.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vacomall.act.bean.RoleBeen;
import com.vacomall.act.entity.Role;

public class RoleBeenUtil {

	public static List<RoleBeen> formRoles(Set<Role> roles, List<Role> roles2) {
		// TODO Auto-generated method stub
		List<RoleBeen> roleBeens = new ArrayList<RoleBeen>();
		for(Role role : roles2){
			roleBeens.add(new RoleBeen(role, roles.contains(role)?1:0));
		}
		return roleBeens;
	}

}
