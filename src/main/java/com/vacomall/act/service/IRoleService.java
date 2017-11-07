package com.vacomall.act.service;

import java.util.List;
import java.util.Set;

import com.vacomall.act.common.service.LayuiService;
import com.vacomall.act.entity.Role;

public interface IRoleService extends LayuiService<Role, Long>{

	List<Role> findAll();

	Set<Role> findByIdIn(List<Long> ids);

}
