package com.anazzubair.techy.business.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.QUser;
import com.anazzubair.techy.business.model.User;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
@Transactional(readOnly = true, propagation = Propagation.MANDATORY)
public class QueryDslUserRepositoryImpl implements QueryDslUserRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public User findByFirstname(String name) {
		JPAQuery query = new JPAQuery(em);
		QUser user = QUser.user;
		return query.from(user).where(user.firstName.eq(name)).uniqueResult(user);
	}
}
