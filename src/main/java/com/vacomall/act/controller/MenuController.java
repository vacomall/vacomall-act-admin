package com.vacomall.act.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.entity.Menu;
import com.vacomall.act.service.IMenuService;
import com.vacomall.act.util.StringUtil;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired private IMenuService menuService;
	
	@RequestMapping({"","/","/list"})
	public String index(){
		return "menu/menu-list";
	}

	@RequestMapping("/json")
	@ResponseBody
	public Rest json(
			@RequestParam(value="page",defaultValue="1") Integer page,
			@RequestParam(value="limit",defaultValue="10") Integer size,
			Menu menu
			){
		Sort sort = new Sort(new Order(Direction.ASC,"code"));
		Page<Menu> pageData = menuService.page(page, size,sort,menu);
		for(Menu mu : pageData.getContent()){
			mu.setText(StringUtil.leftAppend("L "+mu.getText(),mu.getCode().length()+5,"&nbsp;"));
		}
	    return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
}
