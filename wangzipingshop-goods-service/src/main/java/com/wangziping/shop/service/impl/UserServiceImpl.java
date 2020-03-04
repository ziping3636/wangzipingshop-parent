package com.wangziping.shop.service.impl;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangziping.shop.config.AdminProperties;
import com.wangziping.shop.service.UserService;

@Service(interfaceClass = UserService.class, version = "1.0.0")
public class UserServiceImpl implements UserService {

	@Autowired
	private AdminProperties adminProperties;

	public boolean login(String userName, String password) {
		// 判断用户名和密码是否与配置文件一致
		return (adminProperties.getAdminName().equals(userName) && adminProperties.getPassword().equals(password));
	}

}
