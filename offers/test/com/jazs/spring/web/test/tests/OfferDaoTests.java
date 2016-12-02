package com.jazs.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class OfferDaoTests {
	
	@Autowired
	private OffersDAO offersDAO;
	
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
	public void testOffers() {
		
		User user = new User("jazs", "Rambo Zsolti", "1234", "jazs@fake.com", true, "USER_ROLE");
		
		assertTrue("User creation should be true", usersDAO.create(user));

		Offer offer = new Offer(user, "This is a test offer.");
		
		assertTrue("Offer creation should return true", offersDAO.create(offer));
		
		List<Offer> offers = offersDAO.getOffers();
		
		assertEquals("Should be one offer in database.", 1, offers.size());
		
		assertEquals("Retrieved offer should match created offer.", offer, offers.get(0));
		
		// Get the offer with ID filled in.
		offer = offers.get(0);
		
		offer.setText("Updated offer text.");
		assertTrue("Offer update should return true", offersDAO.update(offer));
		
		Offer updated = offersDAO.getOffer(offer.getId());
		
		assertEquals("Updated offer should match retrieved updated offer", offer, updated);
		
		Offer offer2 = new Offer(user, "This is a test offer");
		
		assertTrue("Offer creation should return true", offersDAO.create(offer2));
		
		List<Offer> userOffers = offersDAO.getOffers(user.getUsername());
		
		assertEquals("Should be two offers for user", 2, userOffers.size());
		
		List<Offer> secondList = offersDAO.getOffers();
		
		for (Offer current : secondList) {
			Offer retrieved = offersDAO.getOffer(current.getId());
			
			assertEquals("Offer by ID should match offer from list", current, retrieved);
		}
		
		offersDAO.delete(offer.getId());
		
		List<Offer> empty = offersDAO.getOffers();
		
		assertEquals("Offers lists should contain one offer.", 1, empty.size());
	}
	
}
