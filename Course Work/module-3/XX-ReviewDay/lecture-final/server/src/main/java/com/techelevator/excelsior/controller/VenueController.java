package com.techelevator.excelsior.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.dao.VenueDAO;

/* 
  	List<Venue> selectAllVenues();
  	/venues
	
	Venue getVenueById(int venueId);
	/venues/{id}
	
	
	List<Venue> searchVenueByName(String name);
	/venues?name=x

*/

@RestController
public class VenueController {
	
	private VenueDAO venueDao;
	
	public VenueController(VenueDAO venueDao) {
		this.venueDao = venueDao;
	}
	
	@RequestMapping(path="/venues", method=RequestMethod.GET)
	public List<Venue> list(@RequestParam(required=false) String name) {
		if (name != null) {
			return venueDao.searchVenueByName(name);
		}
		return venueDao.selectAllVenues();
	}
	
	@RequestMapping(path="/venues/{id}", method=RequestMethod.GET)
	public Venue get(@PathVariable(name="id") int venueId) {
		return venueDao.getVenueById(venueId);
	}
	
	
	
	
}
