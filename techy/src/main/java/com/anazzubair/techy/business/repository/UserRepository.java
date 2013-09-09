package com.anazzubair.techy.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.User;

@Transactional(readOnly = true, propagation = Propagation.MANDATORY)
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

	User findByUsername(String username);
}
