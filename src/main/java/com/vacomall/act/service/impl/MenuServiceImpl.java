package com.vacomall.act.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacomall.act.bean.MenuTree;
import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.Menu;
import com.vacomall.act.repository.MenuRepository;
import com.vacomall.act.service.IMenuService;

@Service
public class MenuServiceImpl extends LayuiServiceImpl<MenuRepository, Menu, Long> implements IMenuService {

	@Autowired private MenuRepository menuRepository;
	
	@Override
	public List<MenuTree> findMenuTree(Long pid,List<Long> roleMenuIds) {
		List<MenuTree> menuTrees = new ArrayList<MenuTree>();
		List<Menu> list = this.findByExample(new Menu().pid(pid));
		if(list.size() > 0) {
			for(Menu menu : list){
				MenuTree mt = new MenuTree();
				mt.setMenu(menu);
				if(roleMenuIds.contains(menu.getId())){
					mt.setHasAuth(1);
				}
				if(menu.getDeep().intValue() < 3){
					mt.setMenuTrees(findMenuTree(menu.getId(),roleMenuIds)); //递归查询
				}
				menuTrees.add(mt);
			}
		}
		return menuTrees;
	}

	@Override
	public List<Menu> findByIdIn(List<Long> ids) {
		// TODO Auto-generated method stub
		return menuRepository.findByIdIn(ids);
	}
}
