package com.vacomall.act.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.Role;
import com.vacomall.act.repository.RoleRepository;
import com.vacomall.act.service.IRoleService;

@Service
public class RoleServiceImpl extends LayuiServiceImpl<RoleRepository, Role, Long> implements IRoleService {

	@Autowired private RoleRepository roleRepository; 
	
	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Set<Role> findByIdIn(List<Long> ids) {
		// TODO Auto-generated method stub
		return roleRepository.findByIdIn(ids);
	}


}
