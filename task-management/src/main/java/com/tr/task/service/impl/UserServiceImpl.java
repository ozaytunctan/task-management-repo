package com.tr.task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tr.task.dto.UserDto;
import com.tr.task.entity.User;
import com.tr.task.repository.UserRepository;
import com.tr.task.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto save(UserDto user) {
		User userEntity=new User();
		userEntity.setFullName(user.getFullName());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity.setUserName(user.getUserName());
		
		userEntity=this.userRepository.save(userEntity);
		
		user.setId(userEntity.getId());
		return user;
	}

}
