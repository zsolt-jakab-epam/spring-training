package com.jazs.spring.web.test.tests;

import static org.junit.Assert.*;

import java.util.Iterator;
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

import com.jazs.spring.web.dao.Message;
import com.jazs.spring.web.dao.MessagesDAO;
import com.jazs.spring.web.dao.Offer;
import com.jazs.spring.web.dao.OffersDAO;
import com.jazs.spring.web.dao.User;
import com.jazs.spring.web.dao.UsersDAO;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/jazs/spring/web/config/dao-context.xml",
		"classpath:com/jazs/spring/web/config/security-context.xml",
		"classpath:com/jazs/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MessagesDAOTest {
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private MessagesDAO messagesDAO;
	
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
	public void testSave() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);

		Message message1 = new Message("Test subject 1", "Test content 1", "Isaac Newton", "isaac@fake.com", user1.getUsername());
		messagesDAO.saveOrUpdate(message1);
	}
		
}
