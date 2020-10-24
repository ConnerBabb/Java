package com.techelevator.excelsior.model.dao;

import java.util.List;

import com.techelevator.excelsior.model.Venue;

public interface VenueDAO {
	List<Venue> selectAllVenues();
	
	Venue getVenueById(int venueId);
	
	List<Venue> searchVenueByName(String name);
	
	int addVenue(Venue venue);
}
