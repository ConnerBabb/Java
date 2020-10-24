package com.techelevator.excelsior.model;

import java.util.List;

public interface VenueDAO {
	List<Venue> selectAllVenues();
	
	Venue getVenueById(int venueId);
	
	List<Venue> searchVenueByName(String name);
	
	int addVenue(Venue venue);
}
