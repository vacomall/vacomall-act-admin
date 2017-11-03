package com.vacomall.act.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.entity.ActPage;
import com.vacomall.act.service.IActPageService;

@Controller
@RequestMapping(value = "/actpage")
public class ActPageController {

	@Autowired
	private IActPageService actPageService;

	@RequestMapping(value = { "", "/" })
	public String index() {
		return "actpage/actpage-list";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Rest list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer size, ActPage actPage) {
		Page<ActPage> pageData = actPageService.page(page, size, actPage);
		return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}
	
	@RequestMapping(value="/add")
	public String add(){
		return "actpage/actpage-add";
	}

}
