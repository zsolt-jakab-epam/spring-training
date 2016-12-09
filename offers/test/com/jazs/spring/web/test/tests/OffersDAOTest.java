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
public class OffersDAOTest {
	
	@Autowired
	private OffersDAO offersDAO;
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private DataSource dataSource;

	private User user1 = new User("jazs", "Rambo Zsolti", "1234", "jazs@fake.com", true, "ROLE_USER");
	private User user2 = new User("ford", "Robert Ford", "dolores", "ford@fake.com", true, "ROLE_ADMIN");
	private User user3 = new User("swigin", "Al Sverengen", "gemm", "al@fake.com", true, "USER_ROLE");
	private User user4 = new User("john", "John Favor", "heart", "angel@fake.com", false, "user");


	private Offer offer1 = new Offer(user1, "This is a test offer.");
	private Offer offer2 = new Offer(user1, "This is another test offer.");
	private Offer offer3 = new Offer(user2, "This is yet another test offer.");
	private Offer offer4 = new Offer(user3, "This is a test offer once again.");
	private Offer offer5 = new Offer(user3, "Here is an interesting offer of some kind.");
	private Offer offer6 = new Offer(user3, "This is just a test offer.");
	private Offer offer7 = new Offer(user4, "This is a test offer for a user that is not enabled.");
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
		
		jdbc.execute("delete from offers");
		jdbc.execute("delete from messages");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testDelete() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		offersDAO.saveOrUpdate(offer2);
		offersDAO.saveOrUpdate(offer3);
		offersDAO.saveOrUpdate(offer4);
		offersDAO.saveOrUpdate(offer5);
		offersDAO.saveOrUpdate(offer6);
		offersDAO.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDAO.getOffer(offer2.getId());
		assertNotNull("Offer with ID " + retrieved1.getId() + " should not be null (deleted, actual)", retrieved1);
		
		offersDAO.delete(offer2.getId());
		
		Offer retrieved2 = offersDAO.getOffer(offer2.getId());
		assertNull("Offer with ID " + retrieved1.getId() + " should be null (deleted, actual)", retrieved2);
	}
	
	@Test
	public void testGetById() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		offersDAO.saveOrUpdate(offer1);
		offersDAO.saveOrUpdate(offer2);
		offersDAO.saveOrUpdate(offer3);
		offersDAO.saveOrUpdate(offer4);
		offersDAO.saveOrUpdate(offer5);
		offersDAO.saveOrUpdate(offer6);
		offersDAO.saveOrUpdate(offer7);
		
		Offer retrieved1 = offersDAO.getOffer(offer1.getId());
		assertEquals("Offers should match", offer1, retrieved1);
		
		Offer retrieved2 = offersDAO.getOffer(offer7.getId());
		assertNull("Should not retrieve offer for disabled user.", retrieved2);
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		
		offersDAO.saveOrUpdate(offer1);
		
		List<Offer> offers1 = offersDAO.getOffers();
		
		assertEquals("Should be six offers for enabled users", 1,  offers1.size());

		assertEquals("Retrieved offer should equal inserted offer.", offer1, offers1.get(0));
		
		offersDAO.saveOrUpdate(offer2);
		offersDAO.saveOrUpdate(offer3);
		offersDAO.saveOrUpdate(offer4);
		offersDAO.saveOrUpdate(offer5);
		offersDAO.saveOrUpdate(offer6);
		offersDAO.saveOrUpdate(offer7);
		
		List<Offer> offers2 = offersDAO.getOffers();
		
		assertEquals("Should be six offers for enabled users", 6,  offers2.size());
	}
	
	@Test
	public void testGetUsername() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		
		offersDAO.saveOrUpdate(offer2);
		offersDAO.saveOrUpdate(offer3);
		offersDAO.saveOrUpdate(offer4);
		offersDAO.saveOrUpdate(offer5);
		offersDAO.saveOrUpdate(offer6);
		offersDAO.saveOrUpdate(offer7);
		
		List<Offer> offers1 = offersDAO.getOffers(user3.getUsername());		
		assertEquals("Should be three offers for this user", 3, offers1.size());
		
		List<Offer> offers2 = offersDAO.getOffers("fhfkasjk");
		assertEquals("Should be zero offers for this user", 0, offers2.size());
		
		List<Offer> offers3 = offersDAO.getOffers(user2.getUsername());
		assertEquals("Should be one offer for this user", 1, offers3.size());
	}
	
	@Test
	public void testUpdate() {
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		
		offersDAO.saveOrUpdate(offer2);
		offersDAO.saveOrUpdate(offer3);
		offersDAO.saveOrUpdate(offer4);
		offersDAO.saveOrUpdate(offer5);
		offersDAO.saveOrUpdate(offer6);
		offersDAO.saveOrUpdate(offer7);		
		
		offer3.setText("This offer has updated text.");
		offersDAO.saveOrUpdate(offer3);
		
		Offer retrieved = offersDAO.getOffer(offer3.getId());
		assertEquals("Retrieved offer should be updated.", offer3, retrieved);
	}	
}
