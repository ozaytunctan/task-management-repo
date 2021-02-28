package com.tr.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
	public LoggedInUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));

		return new LoggedInUserDto(user);
	}

}
