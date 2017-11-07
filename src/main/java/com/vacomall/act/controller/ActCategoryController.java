package com.vacomall.act.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import com.vacomall.act.common.bean.Rest;
import com.vacomall.act.entity.ActCategory;
import com.vacomall.act.service.IActCategoryService;

/**
 * 活动分类控制器
 * 
 * @author Edward.Yao 2017年11月3日下午3:02:54
 */
@Controller
@RequestMapping(value = "/actcategory")
public class ActCategoryController {

	@Autowired
	private IActCategoryService actCategoryService;

	@RequestMapping(value = { "", "/" })
	public String index() {
		return "actcategory/actcategory-list";
	}

	/**
	 * 活动分类首页列表
	 * 
	 * @param page
	 * @param size
	 * @param actCategory
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Rest list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "limit", defaultValue = "10") Integer size, ActCategory actCategory) {
		Page<ActCategory> pageData = actCategoryService.page(page, size, actCategory);
		return Rest.okCountData(pageData.getTotalElements(), pageData.getContent());
	}

	@RequestMapping("/add")
	public String add() {
		return "actcategory/actcategory-add";
	}

	@RequestMapping("/doAdd")
	@ResponseBody
	public Rest doAdd(ActCategory actCategory) {
		actCategoryService.save(actCategory);
		return Rest.ok();
	}

	@RequestMapping(value = "/edit")
	public String edit(Long id, Model model) {
		model.addAttribute("actcategory", actCategoryService.findOne(id));
		return "actcategory/actcategory-edit";
	}

	@RequestMapping(value = "/doEdit")
	@ResponseBody
	public Rest doEdit(ActCategory actCategory) {
		actCategoryService.updateById(actCategory, actCategory.getId());
		return Rest.ok();

	}

	/**
	 * 删除操作
	 * 
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Rest delete(@RequestParam("ids[]") Long[] ids) {
		if (ArrayUtils.isEmpty(ids)) {
			return Rest.failure("客户端传入对象id为空");
		}
		try {
			actCategoryService.delete(ids);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException("该活动分类已被使用,不可删除");
		}
		return Rest.ok();
	}

}
