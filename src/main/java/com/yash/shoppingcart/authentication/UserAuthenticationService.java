package com.yash.shoppingcart.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yash.shoppingcart.dao.AccountDao;
import com.yash.shoppingcart.entity.Account;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	private AccountDao accountDao;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDao.findAccount(username);
		System.out.println("Account= " + account);
		if (account == null) {
			throw new UsernameNotFoundException("User " 
					+ username + " was not found in the database");
		}	
		String role = account.getUserRole();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		grantList.add(authority);
		boolean enabled = account.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		UserDetails userDetails = (UserDetails) new User(account.getUserName(), 
				account.getPassword(), enabled, accountNonExpired, 
				credentialsNonExpired, accountNonLocked, grantList);
		return userDetails;
	}
}
