package com.techelevator.excelsior.model;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAO {
	
	public int makeAReservation (int spaceId, LocalDate startDate, int numberOfDays,int numberOfAttendees, String reservedFor);
	
	public List<Reservation> getResListByIdAndDate (int spaceId, LocalDate startDate, int numberOfDays);
	
	public Reservation getReservationById (int reservationId);
	
	public List<Reservation> getReservationsInNext30ByVenueId (int venueId);
}
