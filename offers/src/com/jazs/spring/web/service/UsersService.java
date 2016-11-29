package com.jazs.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.jazs.spring.web.dao.User;
import com.jazs.spring.web.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	
	private UsersDAO usersDAO;
	
	@Autowired
	public void setOffersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void create(User user) {
		usersDAO.create(user);		
	}

	public boolean exists(String username) {
		return usersDAO.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return usersDAO.getAllUsers();
	}

}
