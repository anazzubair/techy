package com.anazzubair.techy.business.repository;

import com.anazzubair.techy.business.model.User;

public interface QueryDslUserRepository {

	User findByFirstname(String name);

}
