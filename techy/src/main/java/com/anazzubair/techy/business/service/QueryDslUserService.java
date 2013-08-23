package com.anazzubair.techy.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.repository.QueryDslUserRepository;

@Service("QueryDslUserService")
public class QueryDslUserService implements UserService {
	
	@Autowired
	QueryDslUserRepository userRepository;
	
	@Override
	public User getMeAUser(){
		return userRepository.findByFirstname("Anaz");
	}
}
