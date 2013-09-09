package com.anazzubair.techy.business.service;

import com.anazzubair.techy.business.model.User;

public interface UserService {

	User getMeAUser();

	User findUserByUsername(String username);

}