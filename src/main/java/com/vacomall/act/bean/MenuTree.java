package com.vacomall.act.bean;

import java.util.ArrayList;
import java.util.List;

import com.vacomall.act.entity.Menu;

public class MenuTree {

	private Menu menu;
	
	/**
	 * 1-有权限
	 * 0-没有权限
	 */
	private Integer hasAuth = 0;
	
	private List<MenuTree> menuTrees = new ArrayList<MenuTree>();

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<MenuTree> getMenuTrees() {
		return menuTrees;
	}

	public void setMenuTrees(List<MenuTree> menuTrees) {
		this.menuTrees = menuTrees;
	}

	public Integer getHasAuth() {
		return hasAuth;
	}

	public void setHasAuth(Integer hasAuth) {
		this.hasAuth = hasAuth;
	}
}
