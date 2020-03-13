package com.wangziping.shop.service;

import com.wangziping.shop.pojo.User;

/** 
 * @ClassName: UserService 
 * @Description: TODO
 * @author: wangziping
 * @date: 2020年3月11日 上午8:51:51  
 */
public interface WebUserService {

	User login(String username, String password);
	
	User register(User user);
}
