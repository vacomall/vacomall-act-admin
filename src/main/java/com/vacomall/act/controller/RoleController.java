package com.vacomall.act.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.vacomall.act.bean.MenuTree;
import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.constant.RoleState;
import com.vacomall.act.entity.Role;
import com.vacomall.act.service.IMenuService;
import com.vacomall.act.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired private IRoleService roleService;
	@Autowired private IMenuService menuService;
	
	@RequestMapping({"","/","/list"})
	public String index(){
		return "role/role-list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Rest json(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="limit",defaultValue="10") Integer size,
			Role role){
		Page<Role> pageData = roleService.page(page, size,role);
	    return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
	
	@RequestMapping("/add")
	public String add(){
		return "role/role-add";
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Rest doAdd(Role role){
		if(role.getRoleState() == null){
			role.setRoleState(RoleState.OFF.getState());
		}
		roleService.save(role);
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
		roleService.delete(ids);
		return Rest.ok();
	}
	
	@RequestMapping("/edit")
	public String edit(Long id,Model model){
		model.addAttribute("role", roleService.findOne(id));
		return "role/role-edit";
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Rest doEdit(Role submitRole){
		if(submitRole.getRoleState() == null){
			submitRole.setRoleState(RoleState.OFF.getState());
		}
		roleService.updateById(submitRole, submitRole.getId());
		return Rest.ok();
	}
	
	@RequestMapping("/auth")
	public String auth(Long id,Model model){
		Role role =  roleService.findOne(id);
		model.addAttribute("role", role);
		List<Long> roleMenuIds = Lists.transform(new ArrayList<>(role.getMenus()), r -> r.getId());
		List<MenuTree> menuTrees = menuService.findMenuTree(0L,roleMenuIds);
		model.addAttribute("menuTrees", menuTrees);
		return "role/role-auth";
	}
	
	@ResponseBody
	@RequestMapping("/doAuth")
	public Rest doAuth(Long roleId,@RequestParam(value="mid[]",required=false) Long[] mids){
		Role role = roleService.findOne(roleId);
		if(role != null){
			if(ArrayUtils.isEmpty(mids)){
				role.setMenus(null);
			}else{
				role.setMenus(new HashSet<>(menuService.findByIdIn(Arrays.asList(mids))));
			}
			roleService.updateUseNullById(role, roleId);
			return Rest.ok();
		}
		return Rest.failure("没有发现角色");
	}
}
