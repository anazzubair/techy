package com.anazzubair.techy.business.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.User;

@Transactional(propagation = Propagation.MANDATORY)
public interface UserService {

	User getMeAUser();

}
