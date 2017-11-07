package com.vacomall.act.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 菜单
 * @author jameszhou
 *
 */
@Entity(name="sys_menu")
public class Menu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	
	/**
	 * 菜单名称
	 */
	@Column(nullable = false)
	private String text;
	/**
	 * 菜单URI
	 */
	private String uri;
	/**
	 * 资源名称
	 */
	private String resource;
	
	/**
	 * 商家菜单ID
	 */
	@Column(nullable = false)
	private Long pid;
	
	/**
	 * 菜单图标
	 */
	private String icon;
	
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 深度,级数
	 */
	private Integer deep;
	
	/**
	 * 编码
	 */
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDeep() {
		return deep;
	}

	public void setDeep(Integer deep) {
		this.deep = deep;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Menu() {
	}

	public Menu(Long id, String text, String uri, String resource, Long pid, String icon, Integer sort, Integer deep) {
		this.id = id;
		this.text = text;
		this.uri = uri;
		this.resource = resource;
		this.pid = pid;
		this.icon = icon;
		this.sort = sort;
		this.deep = deep;
	}

	public Menu(Long id) {
		this.id = id;
	}
	
	public Menu pid(Long pid){
		this.pid = pid;
		return this;
	}

	public List<Menu> ids(Long[] mids) {
		// TODO Auto-generated method stub
		List<Menu> menus = new ArrayList<>();
		for(Long l : mids){
			menus.add(new Menu(l));
		}
		return menus;
	}
}
