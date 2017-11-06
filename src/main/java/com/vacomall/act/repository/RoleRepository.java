package com.vacomall.act.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacomall.act.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Set<Role> findByIdIn(List<Long> ids);

	
}
