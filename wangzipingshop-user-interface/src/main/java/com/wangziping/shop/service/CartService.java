package com.wangziping.shop.service;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Cart;

public interface CartService {

	/**
	 * @Title: addCart 
	 * @Description: TODO 添加购物车
	 * @param uid
	 * @param skuId
	 * @param buyNum 数量
	 * @return
	 * @return: int
	 */
	int addCart(Integer uid, Integer skuId, Integer buyNum);

	/**
	 * @Title: delCart 
	 * @Description: TODO 删除购物车里的某一部分商品
	 * @param uid
	 * @return
	 * @return: int
	 */
	int delCart(int[] ids);

	/**
	 * @Title: clearCart 
	 * @Description: TODO 清空购物车
	 * @return
	 * @return: int
	 */
	int clearCart(Integer uid);

	PageInfo<Cart> List(int uid, int pageNum, int pageSize);

	int createOrder(Integer uid, int[] cartIds, String address);

}
