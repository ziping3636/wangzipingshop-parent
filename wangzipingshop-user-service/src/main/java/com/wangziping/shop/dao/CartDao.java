package com.wangziping.shop.dao;

import java.util.List;

import com.wangziping.shop.pojo.Cart;

public interface CartDao {

	List<Cart> listCart(int uid);

	int delCart(int[] ids);

	int addCart(Cart cart);

	int clear(Integer uid);

	List<Cart> listByIds(int[] cartIds);

	int deleteByIds(int[] cartIds);

}
