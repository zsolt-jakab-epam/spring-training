package com.caveofprogramming.spring.test;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/caveofprogramming/spring/test/beans/beans.xml");
		
		OffersDAO offersDAO = (OffersDAO)context.getBean("offersDao");
		
		List<Offer> offers = offersDAO.getOffers();
		
		for (Offer offer : offers) {
			System.out.println(offer);
		}
		
		((ClassPathXmlApplicationContext)context).close();
	}

}
