package com.caveofprogramming.spring.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/caveofprogramming/spring/test/beans/beans.xml");
		
		OffersDAO offersDAO = (OffersDAO)context.getBean("offersDao");
		
		try {
			Offer albert = new Offer(6, "Alber", "albert@fake.com", "Web design");
			Offer dave = new Offer("Dave", "dave@fake.com", "Happy coding");
			Offer karen = new Offer("Karen", "karen@fake.com", "Coding Java");
			
			offersDAO.update(albert);
			
//			if (offersDAO.create(dave)) {
//				System.out.println("Created offer object");
//			}
			
			List<Offer> offers = new ArrayList<Offer>();
			
			offers.add(new Offer(3,"Steve", "steve@fake.com", "Code for cash"));
			offers.add(new Offer(2,"Jack", "jack@fake.com", "Code from Bronx"));
			
			offersDAO.create(offers);
			
			offers = offersDAO.getOffers();
			
			for (Offer offer : offers) {
				System.out.println(offer);
			}	
			
			Offer offer = offersDAO.getOffer(2);
			System.out.println("Should be Mike: " + offer);
			
			System.out.println(offersDAO.delete(88));
		}
		catch (CannotGetJdbcConnectionException e) {
			System.out.println("Cannot get database connection");
		} 
		catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
			
		((ClassPathXmlApplicationContext)context).close();
	}

}
