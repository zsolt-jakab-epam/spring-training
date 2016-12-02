package com.jazs.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jazs.spring.web.dao.User;
import com.jazs.spring.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { 
		"classpath:com/jazs/spring/web/config/dao-context.xml",
		"classpath:com/jazs/spring/web/config/security-context.xml",
		"classpath:com/jazs/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDAOTests {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			
		jdbc.execute("truncate table offers");
		jdbc.execute("truncate table users");
	}
	
	@Test
	public void testUsers() {
		User user = new User("jazs", "Rambo Zsolti", "1234", "jazs@fake.com", true, "USER_ROLE");
		
		assertTrue("User creation should be true", usersDAO.create(user));
		
		List<User> users = usersDAO.getAllUsers();
		
		assertEquals("Number of users should be 1.", 1, users.size());
		
		assertTrue("User should exists.", usersDAO.exists(user.getUsername()));
		
		assertEquals("Created user should be identical to retreaved user.", user, users.get(0));
	}

}
