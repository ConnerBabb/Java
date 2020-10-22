package com.techelevator.excelsior.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.dao.ReservationDAO;

@RestController
public class ReservationsController {

	@Autowired
	private ReservationDAO reservationDao;
	/*
	 public int makeAReservation (int spaceId, LocalDate startDate, int numberOfDays,int numberOfAttendees, String reservedFor);
	/spaces/:id/reservations     POST
	 
	public Reservation getReservationById (int reservationId);
	/reservations/:id
	
	
	public List<Reservation> getReservationsInNext30ByVenueId (int venueId);
	/venues/:id/reservations
	 */
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path="/spaces/{id}/reservations", method=RequestMethod.POST)
	public Reservation add(@RequestBody Reservation reservation, @PathVariable(name="id") int spaceId) {
		int reservationId = reservationDao.makeAReservation(spaceId, reservation.getStartDate(), reservation.getNumberOfDays(), 
				reservation.getNumberOfAttendees(), reservation.getReservedFor());
		reservation.setSpaceId(spaceId);
		reservation.setResId(reservationId);
		return reservation;
	}
	
	@RequestMapping(path="/reservations/{id}", method=RequestMethod.GET)
	public Reservation getReservation(@PathVariable int id) {
		return reservationDao.getReservationById(id);
	}
	
	@RequestMapping(path="/venues/{id}/reservations", method=RequestMethod.GET)
	public List<Reservation> getReservationsForVenue(@PathVariable(name="id") int venueId) {
		return reservationDao.getReservationsInNext30ByVenueId(venueId);
	}
	
}
