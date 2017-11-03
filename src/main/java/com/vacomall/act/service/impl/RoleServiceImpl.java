package com.vacomall.act.service.impl;

import org.springframework.stereotype.Service;

import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.Role;
import com.vacomall.act.repository.RoleRepository;
import com.vacomall.act.service.IRoleService;

@Service
public class RoleServiceImpl extends LayuiServiceImpl<RoleRepository, Role, Long> implements IRoleService {


}
