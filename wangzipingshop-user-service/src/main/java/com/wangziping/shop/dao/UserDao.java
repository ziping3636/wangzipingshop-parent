package com.wangziping.shop.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wangziping.shop.pojo.User;

public interface UserDao {

	User login(@Param("username")String username, @Param("password")String password);

	int addUser(User user);

	@Select("SELECT * FROM hg_user WHERE uid = #{value}")
	User getUserById(Integer uid);

}
