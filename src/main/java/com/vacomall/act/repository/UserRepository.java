package com.vacomall.act.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacomall.act.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String username);
	
}
