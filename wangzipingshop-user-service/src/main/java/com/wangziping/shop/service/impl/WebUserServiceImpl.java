package com.wangziping.shop.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangziping.shop.dao.UserDao;
import com.wangziping.shop.pojo.User;
import com.wangziping.shop.service.WebUserService;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO 用户服务, 指的是普通用户, 购物的消费者
 * @author: hasee
 * @date: 2020年3月11日 上午8:59:00
 */
@Service(interfaceClass = WebUserService.class)
public class WebUserServiceImpl implements WebUserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User login(String username, String password) {
		return userDao.login(username, DigestUtils.md5Hex(password));
	}

	@Override
	public User register(User user) {

		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		if (userDao.addUser(user) > 0) {
			return userDao.getUserById(user.getUid());
		}
		return null;
	}

}
