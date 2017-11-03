package com.vacomall.act.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.ActCategory;
import com.vacomall.act.repository.ActCategoryRepository;
import com.vacomall.act.service.IActCategoryService;

/**
 * 
 * @author Edward.Yao
 * 2017年11月3日上午11:59:00
 */
@Service
public class ActCategoryServiceImpl extends LayuiServiceImpl<ActCategoryRepository,ActCategory,Long> implements IActCategoryService{
	
	
	@Autowired
	private ActCategoryRepository actReponsity;

	@Override
	public ActCategory findBycatName(String catName) {
		return actReponsity.findByCatName(catName);
	}
}
