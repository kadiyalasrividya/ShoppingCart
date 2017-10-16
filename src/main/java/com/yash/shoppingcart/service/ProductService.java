package com.yash.shoppingcart.service;

import com.yash.shoppingcart.entity.Product;
import com.yash.shoppingcart.model.PaginationResult;
import com.yash.shoppingcart.model.ProductInfo;

public interface ProductService {
	public Product findProduct(String code);

	public ProductInfo findProductInfo(String code);

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage);

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage, String likeName);

	public void save(ProductInfo productInfo);

}
