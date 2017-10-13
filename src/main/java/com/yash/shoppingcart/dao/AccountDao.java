package com.yash.shoppingcart.dao;

import com.yash.shoppingcart.entity.Account;

public interface AccountDao {
	
	public Account findAccount(String userName);

}
