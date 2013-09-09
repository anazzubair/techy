package com.anazzubair.techy.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.anazzubair.techy.business.model.Role;
import com.anazzubair.techy.business.model.User;
import com.anazzubair.techy.business.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getMeAUser(){
		return userRepository.findOne(1L);
	}
	
	@Override
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	//Returns a spring security authentication User object. 
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = findUserByUsername(username);
		
		if(user == null)  {
			throw new UsernameNotFoundException(String.format("No such user: {0}" , username));
		}
		
		if(user.getRoles().isEmpty()) {
			throw new UsernameNotFoundException(String.format("User {0} has no authorities", username));
		}
		
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;

	    return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
	            user.isActive(),
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            getAuthorities(user.getRoles()));
		
	}

	private Collection<? extends GrantedAuthority> getAuthorities(
			List<Role> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
}
