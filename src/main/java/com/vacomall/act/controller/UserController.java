package com.vacomall.act.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.constant.RoleState;
import com.vacomall.act.constant.UserState;
import com.vacomall.act.entity.Role;
import com.vacomall.act.entity.User;
import com.vacomall.act.service.IRoleService;
import com.vacomall.act.service.IUserService;
import com.vacomall.act.util.RoleBeenUtil;
import com.vacomall.act.util.ShiroUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired private IUserService userService;
	@Autowired private IRoleService roleService;
	
	@RequestMapping({"","/","/list"})
	public String index(){
		return "user/user-list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Rest json(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="limit",defaultValue="10") Integer size,
			User user
			){
		Page<User> pageData = userService.page(page, size,user);
	    return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		
		Role role = new Role();
		role.setRoleState(RoleState.ON.getState());
		model.addAttribute("roleList", roleService.findByExample(role));
		return "user/user-add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(User user,String confPassword,@RequestParam(value="roleId[]",required=false) Long[] roleIds){
		user.setCreateTime(new Date());
		if(user.getUserState() == null){
			user.setUserState(UserState.OFF.getState());
		}
		if(!confPassword.equals(user.getPassword())){
			return Rest.failure("两次输入的密码不一致");
		}
		user.setPassword(ShiroUtil.md51024Pwd(user.getPassword(), user.getUserName()));
		if(!ArrayUtils.isEmpty(roleIds)){
			Set<Role> roles = roleService.findByIdIn(Arrays.asList(roleIds));
			if(!roles.isEmpty()){
				user.setRoles(roles);
			}
		}
		userService.save(user);
		return Rest.ok();
	}
	
	/**
	 * 执行删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Rest delete(@RequestParam("ids[]") Long[] ids){
		if(ArrayUtils.isEmpty(ids)){
			return Rest.failure("客户端传入对象id为空");
		}
		userService.delete(ids);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(Long id,Model model){
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		List<Role> roles = roleService.findAll();
		model.addAttribute("roleBeens", RoleBeenUtil.formRoles(user.getRoles(),roles));
		return "user/user-edit";
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(User submitUser,@RequestParam(value="roleId[]",required=false) Long[] roleIds){
		if(submitUser.getUserState() == null){
			submitUser.setUserState(0);
		}
		if(!ArrayUtils.isEmpty(roleIds)){
			Set<Role> roles = roleService.findByIdIn(Arrays.asList(roleIds));
			if(!roles.isEmpty()){
				submitUser.setRoles(roles);
			}
		}
		userService.updateById(submitUser, submitUser.getId());
		return Rest.ok();
	}
}
