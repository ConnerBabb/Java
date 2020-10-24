package com.techelevator.excelsior.model.api;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.ReservationDAO;

public class ApiReservationDao implements ReservationDAO{

	private String baseUrl;
	private RestTemplate restTemplate;
	
	public ApiReservationDao(String baseUrl) {
		this.baseUrl = baseUrl;
		restTemplate = new RestTemplate();
	}

	@Override
	public int makeAReservation(int spaceId, LocalDate startDate, int numberOfDays, int numberOfAttendees,
			String reservedFor) {
		Reservation newReservation = new Reservation();
		newReservation.setStartDate(startDate);
		newReservation.setNumberOfDays(numberOfDays);
		newReservation.setNumberOfAttendees(numberOfAttendees);
		newReservation.setReservedFor(reservedFor);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Reservation> request = new HttpEntity<Reservation>(newReservation, headers);
		
		
		String url = baseUrl + "/spaces/" + spaceId + "/reservations";
		
		newReservation = restTemplate.postForObject(url, request, Reservation.class);
	
		return newReservation.getResId();
	}

	@Override
	public List<Reservation> getResListByIdAndDate(int spaceId, LocalDate startDate, int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		return restTemplate.getForObject(baseUrl + "/reservations/" + reservationId, Reservation.class);
	}

	@Override
	public List<Reservation> getReservationsInNext30ByVenueId(int venueId) {
		return Arrays.asList(restTemplate.getForObject(baseUrl + "/venues/" + venueId + "/reservations", Reservation[].class));
	}
	
	
	
	
}
