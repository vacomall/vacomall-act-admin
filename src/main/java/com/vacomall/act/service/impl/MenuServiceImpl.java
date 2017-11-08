package com.vacomall.act.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacomall.act.bean.MenuTree;
import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.Menu;
import com.vacomall.act.entity.User;
import com.vacomall.act.repository.MenuRepository;
import com.vacomall.act.repository.UserRepository;
import com.vacomall.act.service.IMenuService;

@Service
public class MenuServiceImpl extends LayuiServiceImpl<MenuRepository, Menu, Long> implements IMenuService {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<MenuTree> findMenuTree(Long pid, List<Long> roleMenuIds) {
		List<MenuTree> menuTrees = new ArrayList<MenuTree>();
		List<Menu> list = this.findByExample(new Menu().pid(pid));
		if (list.size() > 0) {
			for (Menu menu : list) {
				MenuTree mt = new MenuTree();
				mt.setMenu(menu);
				if (roleMenuIds.contains(menu.getId())) {
					mt.setHasAuth(1);
				}
				if (menu.getDeep().intValue() < 3) {
					mt.setMenuTrees(findMenuTree(menu.getId(), roleMenuIds)); // 递归查询
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

	@Deprecated
	@Override
	public List<MenuTree> findMenuByUid(Long uid) {
		return null;
		// TODO Auto-generated method stub
	/*	User user = userRepository.findOne(uid);
		List<Long> mids = new ArrayList<Long>();
		user.getRoles().forEach(r -> {
			r.getMenus().forEach(m->{
				if(!mids.contains(m.getId())){
					mids.add(uid);
				}
			});
		});		
		List<MenuTree> menuTrees = new ArrayList<>();
		List<Menu> menus = menuRepository.findByIdInAndPidOrderByCodeASC(mids,0L);
		if(!menus.isEmpty()){
			menus.forEach(m->{
				MenuTree mt = new MenuTree();
				mt.setMenu(m);
				if(m.getDeep() <2){
				   
				}
				menuTrees.add(mt);
			});
		}
		return menuTrees;*/
	}
}
