package com.yash.shoppingcart.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.shoppingcart.dao.AccountDao;
import com.yash.shoppingcart.daoimpl.AccountDaoImpl;
import com.yash.shoppingcart.entity.Account;
import com.yash.shoppingcart.service.AccountService;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao = null;

	public AccountServiceImpl() {
		accountDao = new AccountDaoImpl();
	}

	public Account findAccount(String userName) {
		return accountDao.findAccount(userName);
		
	}
	

}
