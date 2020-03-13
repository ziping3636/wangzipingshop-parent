package com.wangziping.shop.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wangziping.shop.pojo.Order;
import com.wangziping.shop.pojo.OrderDetail;

public interface OrderService {
	
	PageInfo<Order> orderList(int pageNum, int pageSize, Integer userId);
	
	List<OrderDetail> detailList(int orderId, int pageNum, int pageSize);
	
	
}
