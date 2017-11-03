package com.vacomall.act.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacomall.act.entity.ActCategory;

/**
 * @author Edward.Yao
 * 2017年11月3日上午11:59:32
 */
public interface ActCategoryRepository extends JpaRepository<ActCategory, Long> {
	
	public ActCategory findByCatName(String catName);

}
