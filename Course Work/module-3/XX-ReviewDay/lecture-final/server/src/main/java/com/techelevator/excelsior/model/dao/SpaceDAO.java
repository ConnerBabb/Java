package com.techelevator.excelsior.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.excelsior.model.Space;

public interface SpaceDAO {
	
	List<Space> listSpacesByVenueId (int venueId);
	
	List<Space> getSpacesIfAvailable (int venueId, LocalDate startDate, int numberOfDays, int numberOfAttendees);
	
	Space getSpaceById(int spaceId);
}
