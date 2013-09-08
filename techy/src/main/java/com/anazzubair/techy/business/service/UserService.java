package com.anazzubair.techy.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.QUser;
import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User getMeAUser(){
		return userRepository.findOne(1L);
	}
	
	public User findUserByUsername(String username) {
		QUser user = QUser.user;
		return userRepository.findAll(user.username.eq(username)).iterator().next();
	}
}
