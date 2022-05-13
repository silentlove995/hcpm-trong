package com.example.hcpmtrong.sercurity;

import com.example.hcpmtrong.entity.Account;
import com.example.hcpmtrong.repository.AccountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyAccountDetailService implements UserDetailsService {

	@Autowired
	AccountRepository repository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account exitsAccount = repository.findByUsername(username);
		if (exitsAccount == null) throw new UsernameNotFoundException("Account not found.");
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(exitsAccount.getRole()));
		org.springframework.security.core.userdetails.User userSpring
			= new org.springframework.security.core.userdetails.User(
			exitsAccount.getUsername(), exitsAccount.getPassword(), authorities);
		return userSpring;
	}
}
