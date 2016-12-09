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
public class UsersDAOTest {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private DataSource dataSource;
	
	private User user1 = new User("jazs", "Rambo Zsolti", "1234", "jazs@fake.com", true, "ROLE_USER");
	private User user2 = new User("ford", "Robert Ford", "dolores", "ford@fake.com", true, "ROLE_ADMIN");
	private User user3 = new User("swigin", "Al Sverengen", "gemm", "al@fake.com", true, "USER_ROLE");
	private User user4 = new User("john", "John Favor", "heart", "angel@fake.com", false, "user");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
			
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		
		usersDAO.create(user1);
		
		List<User> users1 = usersDAO.getAllUsers();
		
		assertEquals("One user should have been created and retrieved.", 1, users1.size());
		
		assertEquals("Inserted user should match retrieved.", user1, users1.get(0));
		
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		
		List<User> users2 = usersDAO.getAllUsers();
		
		assertEquals("Should be 4 retrieved users.", 4, users2.size());
	}
	
	
	@Test
	public void testExists() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		
		assertTrue("User should exists.", usersDAO.exists(user1.getUsername()));	
		assertFalse("User should not exists.", usersDAO.exists("fhkjsdkfjaksljf"));
				
	}

}
