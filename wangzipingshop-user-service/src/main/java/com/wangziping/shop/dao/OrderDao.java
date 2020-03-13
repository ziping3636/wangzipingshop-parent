package com.wangziping.shop.dao;

import java.util.List;

import com.wangziping.shop.pojo.Order;
import com.wangziping.shop.pojo.OrderDetail;

public interface OrderDao {

	int add(Order order);

	int addDetail(OrderDetail orderDetail);

	List<Order> orderList(Integer userId);

	List<OrderDetail> detailList(int orderId);

}
