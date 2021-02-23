package com.tr.task.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tr.task.dto.LoggedInUserDto;
import com.tr.task.entity.User;
import com.tr.task.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> findByUserName = this.userRepository.findByUserName(username);
		
		if (!findByUserName.isPresent()) 
			throw new UsernameNotFoundException("");
		
		return new LoggedInUserDto(findByUserName.get());
	}
	
	

}
