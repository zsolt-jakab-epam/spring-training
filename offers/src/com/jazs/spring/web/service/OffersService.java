package com.jazs.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.jazs.spring.web.dao.Offer;
import com.jazs.spring.web.dao.OffersDAO;

@Service("offersService")
public class OffersService {
	
	private OffersDAO offersDAO;
	
	@Autowired
	public void setOffersDAO(OffersDAO offersDAO) {
		this.offersDAO = offersDAO;
	}

	public List<Offer> getCurrent() {
		return offersDAO.getOffers();
	}

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	public void create(Offer offer) {
		offersDAO.saveOrUpdate(offer);		
	}

	public boolean hasOffer(String username) {
		
		if(username == null) {
			return false;
		}
		
		List<Offer> offers = offersDAO.getOffers(username);
		
		if (offers.size() == 0) {
			return false;
		}
		
		return true;
	}

	public Offer getOffer(String username) {
		
		if(username==null) {
			return null;
		}
		
		List<Offer> offers = offersDAO.getOffers(username);
		
		if(offers.size() == 0) {
			return null;
		}
		
		return offers.get(0);
	}

	public void saveOrUpdate(Offer offer) {
		offersDAO.saveOrUpdate(offer);
	}

	public void delete(int id) {
		offersDAO.delete(id);
	}
}
