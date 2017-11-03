package com.vacomall.act.service;

import java.util.List;

import com.vacomall.act.bean.MenuTree;
import com.vacomall.act.common.service.LayuiService;
import com.vacomall.act.entity.Menu;

public interface IMenuService extends LayuiService<Menu, Long>{

	List<MenuTree> findMenuTree(Long pid, List<Long> roleMenuIds);

	/**
	 * 根据ID集合获取菜单
	 * @param ids
	 * @return
	 */
	List<Menu> findByIdIn(List<Long> ids);

}
