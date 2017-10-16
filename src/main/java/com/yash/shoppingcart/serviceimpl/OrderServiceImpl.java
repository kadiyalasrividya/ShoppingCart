package com.yash.shoppingcart.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.shoppingcart.dao.OrderDao;
import com.yash.shoppingcart.daoimpl.OrderDaoImpl;
import com.yash.shoppingcart.model.CartInfo;
import com.yash.shoppingcart.model.OrderDetailInfo;
import com.yash.shoppingcart.model.OrderInfo;
import com.yash.shoppingcart.model.PaginationResult;
import com.yash.shoppingcart.service.OrderService;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao = null;

	public OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
	}

	public void saveOrder(CartInfo cartInfo) {
		orderDao.saveOrder(cartInfo);
	}

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage) {
		return orderDao.listOrderInfo(page, maxResult, maxNavigationPage);
	}

	public OrderInfo getOrderInfo(String orderId) {
		return orderDao.getOrderInfo(orderId);
	}

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId) {
		return orderDao.listOrderDetailInfos(orderId);
	}
}
