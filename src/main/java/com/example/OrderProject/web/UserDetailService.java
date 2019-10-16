package com.example.OrderProject.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.OrderProject.domain.Login;
import com.example.OrderProject.domain.LoginRepository;

@Service

public class UserDetailService implements UserDetailsService {
	private final LoginRepository repository;
	
	@Autowired
	
	public UserDetailService(LoginRepository loginRepository) {
	this.repository = loginRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws
	UsernameNotFoundException {
	Login curruser = repository.findByUsername(username);
	UserDetails user = new org.springframework.security.core.userdetails.User(username,curruser.getPasswordHash(),
	AuthorityUtils.createAuthorityList(curruser.getRole()));
	return user;
	}
	}
	

