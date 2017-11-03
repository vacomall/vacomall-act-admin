package com.vacomall.act.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacomall.act.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	List<Menu> findByIdIn(List<Long> ids);

	
}
