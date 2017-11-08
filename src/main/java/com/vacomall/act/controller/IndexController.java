package com.vacomall.act.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vacomall.act.service.IMenuService;
import com.vacomall.act.util.ShiroUtil;

/**
 * 首页
 * @author jameszhou
 *
 */
@Controller
public class IndexController {
	
	@Autowired private IMenuService menuService;
	
	/**
	 * 首页
	 * @param model
	 * @return
	 */
	@RequestMapping({"/","/index",""})
	public String index(Model model){
		model.addAttribute("name", "Admin");
		model.addAttribute("menus", menuService.findMenuByUid(ShiroUtil.getSessionUid()));
		return "index";
	}
	/**
	 * 欢迎页
	 * @param model
	 * @return
	 */
	@RequestMapping({"/welcome"})
	public String welcome(Model model){
		return "welcome";
	}
	
}
