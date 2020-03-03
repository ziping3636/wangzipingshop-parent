package com.wangziping.shop.service.impl;

import org.apache.dubbo.config.annotation.Service;

import com.wangziping.shop.service.UserService;

@Service(interfaceClass = UserService.class, version = "1.0.0")
public class UserServiceImpl implements UserService {

	public boolean login(String userName, String password) {
		if ("admin".equals(userName) && "123456".equals(password)) {
			return true;
		}
		return false;
	}

}
