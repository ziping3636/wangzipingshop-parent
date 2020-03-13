package com.wangziping.shop.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangziping.shop.dao.CartDao;
import com.wangziping.shop.dao.OrderDao;
import com.wangziping.shop.pojo.Cart;
import com.wangziping.shop.pojo.Order;
import com.wangziping.shop.pojo.OrderDetail;
import com.wangziping.shop.service.CartService;

/**
 * @ClassName: CartServiceImpl
 * @Description: TODO 购物车
 * @author: wangziping
 * @date: 2020年3月12日 上午9:45:15
 */
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	@Autowired
	private OrderDao orderDao;

	@Override
	public int addCart(Integer uid, Integer skuId, Integer buyNum) {
		Cart cart = new Cart(uid, skuId, buyNum);
		return cartDao.addCart(cart);
	}

	@Override
	public int delCart(int[] ids) {
		return cartDao.delCart(ids);
	}

	@Override
	public int clearCart(Integer uid) {
		return cartDao.clear(uid);
	}

	@Override
	public PageInfo<Cart> List(int uid, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Cart>(cartDao.listCart(uid));
	}

	@Override
	public int createOrder(Integer uid, int[] cartIds, String address) {
		int addNum = 0;
		// 先根据购物车ID生成主表的数据
		List<Cart> cartList = cartDao.listByIds(cartIds);
		// 计算总价格
		BigDecimal sumTotal = new BigDecimal(0);
		for (Cart cart : cartList) {
			sumTotal = sumTotal.add(cart.getSumTotal());
		}

		// 生成主表的数据
		Order order = new Order();
		order.setUid(uid);
		order.setSumtotal(sumTotal);
		order.setAddress(address);
		addNum += orderDao.add(order);
		// 生成字表的数据
		for (Cart cart : cartList) {
			OrderDetail orderDetail = new OrderDetail(order.getOid(), cart);
			addNum += orderDao.addDetail(orderDetail);
		}
		
		// 从购物车中删除数据
		cartDao.deleteByIds(cartIds);

		return addNum;
	}

}
