package com.vacomall.act.config;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;
import com.vacomall.act.constant.RoleState;
import com.vacomall.act.constant.UserState;
import com.vacomall.act.entity.Menu;
import com.vacomall.act.entity.Role;
import com.vacomall.act.entity.User;
import com.vacomall.act.repository.MenuRepository;
import com.vacomall.act.repository.RoleRepository;
import com.vacomall.act.repository.UserRepository;
import com.vacomall.act.util.ShiroUtil;

@Configuration
public class InitDB {
	
	@Autowired UserRepository	userRepository;
	@Autowired RoleRepository	roleRepository;
	@Autowired MenuRepository	menuRepository;
	
	@Bean
	@Transactional
	public int initUser(){
		User user = userRepository.findByUserName("admin");
		if(user==null){
			
			//初始化菜单
			Menu menu = new Menu(null,"系统管理","#","system",0L,"",1,1);
			menuRepository.save(menu);
			
			Menu menu1 = new Menu(null,"用户管理","/user/list","",menu.getId(),"",1,2);
			Menu menu2 = new Menu(null,"角色管理","/role/list","",menu.getId(),"",2,2);
			Menu menu3 = new Menu(null,"菜单管理","/menu/list","",menu.getId(),"",3,2);
			Menu menu4 = new Menu(null,"部门管理","/dept/list","",menu.getId(),"",4,2);
			menuRepository.save(menu1);
			menuRepository.save(menu2);
			menuRepository.save(menu3);
			menuRepository.save(menu4);
			
			
			Menu menu11 = new Menu(null,"用户列表","#","user:list",menu1.getId(),"",1,3);
			Menu menu22 = new Menu(null,"新增用户","#","user:add",menu1.getId(),"",2,3);
			Menu menu33 = new Menu(null,"编辑用户","#","user:update",menu1.getId(),"",3,3);
			Menu menu44 = new Menu(null,"删除用户","#","user:delete",menu1.getId(),"",4,3);
			menuRepository.save(menu11);
			menuRepository.save(menu22);
			menuRepository.save(menu33);
			menuRepository.save(menu44);
			
			//初始化角色
			
			Set<Menu> menuSet = Sets.newHashSet(menu1,menu2,menu3,menu4,menu11,menu22,menu33,menu44);
			Role role = new Role(null, "管理员", "admin", "我是超级管理员", RoleState.ON.getState(), menuSet);
			Role role2 = new Role(null, "普通管理员", "keeper", "我是超级管理员", RoleState.ON.getState(),null);
			roleRepository.save(role);
			roleRepository.save(role2);
			
			
			//初始化用户
			user = new User();
			user.setCreateTime(new Date());
			user.setUserName("admin");
			user.setUserState(UserState.ON.getState());
			user.setPassword(ShiroUtil.md51024Pwd("123456", user.getUserName()));
			
			Set<Menu> menuSet2 = Sets.newHashSet(menu44);
			user.setMenus(menuSet2);
			
			Set<Role> roleSet = Sets.newHashSet(role);
			user.setRoles(roleSet);
			
			userRepository.save(user);
		}
		return 1;
	}
	
}
