package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.DAO.UserRepository;
import com.smart.Entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	
	@Autowired
	UserRepository userRepository;
	public UserDetailsServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		//fetching user from database
		User user=userRepository.getUserByUserName(username);
		if(user==null)
			throw new UsernameNotFoundException("Could not Found User");
		
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		return customUserDetails;
		
	}

}
