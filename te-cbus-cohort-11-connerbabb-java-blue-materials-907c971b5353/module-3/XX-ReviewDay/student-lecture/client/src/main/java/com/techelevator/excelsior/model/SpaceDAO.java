package com.techelevator.excelsior.model;

import java.time.LocalDate;
import java.util.List;

public interface SpaceDAO {
	
	List<Space> listSpacesByVenueId (int venueId);
	
	List<Space> getSpacesIfAvailable (int venueId, LocalDate startDate, int numberOfDays, int numberOfAttendees);
	
	Space getSpaceById(int spaceId);
}
