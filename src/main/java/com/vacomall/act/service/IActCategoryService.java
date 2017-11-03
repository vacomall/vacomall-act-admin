package com.vacomall.act.service;

import com.vacomall.act.common.service.LayuiService;
import com.vacomall.act.entity.ActCategory;

/**
 * 
 * @author Edward.Yao
 * 2017年11月3日上午11:59:20
 */
public interface IActCategoryService extends LayuiService<ActCategory, Long> {
	
	public ActCategory findBycatName(String catName);

}
