package com.vacomall.act.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.User;
import com.vacomall.act.repository.UserRepository;
import com.vacomall.act.service.IUserService;

@Service
public class UserServiceImpl extends LayuiServiceImpl<UserRepository, User, Long> implements IUserService {

	@Autowired private UserRepository userRepository;
	
	@Override
	public User findByName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(username);
	}

}
