package com.yash.shoppingcart.dao;

import java.util.List;

import com.yash.shoppingcart.model.CartInfo;
import com.yash.shoppingcart.model.OrderDetailInfo;
import com.yash.shoppingcart.model.OrderInfo;
import com.yash.shoppingcart.model.PaginationResult;

public interface OrderDao {
	public void saveOrder(CartInfo cartInfo);

	public PaginationResult<OrderInfo> listOrderInfo(int page, int maxResult, int maxNavigationPage);

	public OrderInfo getOrderInfo(String orderId);

	public List<OrderDetailInfo> listOrderDetailInfos(String orderId);
}
