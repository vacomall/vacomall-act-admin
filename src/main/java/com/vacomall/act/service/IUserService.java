package com.vacomall.act.service;

import com.vacomall.act.common.service.LayuiService;
import com.vacomall.act.entity.User;

public interface IUserService extends LayuiService<User, Long>{

	User findByName(String username);

}
