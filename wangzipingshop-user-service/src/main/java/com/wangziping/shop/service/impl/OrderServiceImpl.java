package com.wangziping.shop.service.impl;

import java.util.List;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wangziping.shop.dao.OrderDao;
import com.wangziping.shop.pojo.Order;
import com.wangziping.shop.pojo.OrderDetail;
import com.wangziping.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Override
	public PageInfo<Order> orderList(int pageNum, int pageSize, Integer userId) {
		PageHelper.startPage(pageNum, pageSize);

		return new PageInfo<Order>(orderDao.orderList(userId));
	}

	@Override
	public List<OrderDetail> detailList(int orderId, int pageNum, int pageSize) {
		return orderDao.detailList(orderId);
	}

}
