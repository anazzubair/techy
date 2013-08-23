package com.anazzubair.techy.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class RepositoryUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getMeAUser(){
		return userRepository.findOne(1L);
	}
}
