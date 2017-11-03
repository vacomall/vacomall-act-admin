package com.vacomall.act.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="tb_act_page")
public class ActPage  implements Serializable{

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
	 * 活动标题
	 */
	@Column(nullable = false,length=255)
	private String actName;
	
	/**
	 * 活动描述
	 */
	@Column(length=255)
	private String actDesc;
	/**
	 * 活动分类
	 */
	@ManyToOne
	@JoinColumn(name="actCategory")
	private ActCategory actCategory;
	
	/**
	 * 内容
	 */
	@Lob
	@Column(columnDefinition="TEXT", nullable=false)
	private String actContent;
	
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern="yyyy-MM-dd HH:mm") //输出日期格式化
	private Date createDate;
	
	/**
	 * 创建用户
	 */
	@ManyToOne
	@JoinColumn(name="user_id")   
	private User user;
	
	/**
	 * 编辑次数
	 */
	private Long updateCount;
	
	/**
	 * 活动状态，1-已上线,2-维护中,3-已下线,4-已删除
	 */
	private Integer actState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	public String getActContent() {
		return actContent;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUpdateCount() {
		return updateCount;
	}

	public void setUpdateCount(Long updateCount) {
		this.updateCount = updateCount;
	}

	public Integer getActState() {
		return actState;
	}

	public void setActState(Integer actState) {
		this.actState = actState;
	}

	public ActCategory getActCategory() {
		return actCategory;
	}

	public void setActCategory(ActCategory actCategory) {
		this.actCategory = actCategory;
	}	
	
}
