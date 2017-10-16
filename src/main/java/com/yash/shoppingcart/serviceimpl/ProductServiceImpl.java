package com.yash.shoppingcart.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.shoppingcart.dao.ProductDao;
import com.yash.shoppingcart.daoimpl.ProductDaoImpl;
import com.yash.shoppingcart.entity.Product;
import com.yash.shoppingcart.model.PaginationResult;
import com.yash.shoppingcart.model.ProductInfo;
import com.yash.shoppingcart.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao = null;

	public ProductServiceImpl() {
		productDao = new ProductDaoImpl();
	}

	public Product findProduct(String code) {
		return productDao.findProduct(code);
	}

	public ProductInfo findProductInfo(String code) {
		return productDao.findProductInfo(code);
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage) {
		return productDao.queryProducts(page, maxResult, maxNavigationPage);
	}

	public PaginationResult<ProductInfo> queryProducts(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		return productDao.queryProducts(page, maxResult, maxNavigationPage);
	}

	public void save(ProductInfo productInfo) {
		productDao.save(productInfo);
	}

}
