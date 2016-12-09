package com.jazs.spring.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.jazs.spring.web.dao.Message;
import com.jazs.spring.web.dao.MessagesDAO;
import com.jazs.spring.web.dao.User;
import com.jazs.spring.web.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private MessagesDAO messagesDao;
	
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
	
	public void sendMessage(Message message) {
		messagesDao.saveOrUpdate(message);
	}
	
	public User getUser(String username) {
		return usersDAO.getUser(username);
	}

}
