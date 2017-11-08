package com.vacomall.act.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.constant.ActPageState;
import com.vacomall.act.entity.ActCategory;
import com.vacomall.act.entity.ActPage;
import com.vacomall.act.entity.User;
import com.vacomall.act.service.IActCategoryService;
import com.vacomall.act.service.IActPageService;
import com.vacomall.act.util.ShiroUtil;

@Controller
@RequestMapping(value = "/actpage")
public class ActPageController {

	@Autowired
	private IActPageService actPageService;

	@Autowired
	private IActCategoryService actcategoryService;

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

	@RequestMapping(value = "/add")
	public String add(Model model) {
		ActCategory actCategory = new ActCategory();
		List<ActCategory> list = actcategoryService.findByExample(actCategory);
		model.addAttribute("categoryList", list);
		return "actpage/actpage-add";
	}

	@RequestMapping(value = "/doAdd")
	@ResponseBody
	public Rest doAdd(ActPage actpage) {
		User user = ShiroUtil.getSessionUser();
		actpage.setCreateDate(new Date());
		actpage.setUser(user);
		actpage.setUpdateCount(0L);
		actPageService.save(actpage);
		return Rest.ok();
	}

	@RequestMapping("/edit")
	public String edit(Long id, Model model) {
		ActCategory actCategory = new ActCategory();
		List<ActCategory> list = actcategoryService.findByExample(actCategory);
		model.addAttribute("categoryList", list);
		model.addAttribute("actPage", actPageService.findOne(id));
		return "actpage/actpage-edit";

	}
	
	@RequestMapping("/doEdit")
	@ResponseBody
	public Rest doEdit(ActPage actPage) {
		actPage.setUpdateCount(actPage.getUpdateCount() + 1L);
		actPage.setUser(ShiroUtil.getSessionUser());
		actPageService.updateById(actPage, actPage.getId());
		return Rest.ok();
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Rest delete(@RequestParam("ids[]")  Long ids[]) {
		ActPage actpage = new ActPage();
		actpage.setActState(ActPageState.已删除.getState());
		actPageService.updateById(actpage, ids[0]);
		return Rest.ok();
	}

}
