package com.techelevator.excelsior.model.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;

public class ApiVenueDao implements VenueDAO {

	private String baseUrl;
	private RestTemplate restTemplate;
	
	public ApiVenueDao(String baseUrl) {
		this.baseUrl = baseUrl;
		restTemplate = new RestTemplate();
	}
	
	
	@Override
	public List<Venue> selectAllVenues() {
		Venue[] venueArray = restTemplate.getForObject(baseUrl + "/venues", Venue[].class );
		List<Venue> venueList = Arrays.asList(venueArray);
		return venueList;
	}

	@Override
	public Venue getVenueById(int venueId) {
		return restTemplate.getForObject(baseUrl + "/venues/" + venueId, Venue.class);
	}

	@Override
	public List<Venue> searchVenueByName(String name) {
		String url =  baseUrl + "/venues?name=" + name;
		Venue[] venueArray = restTemplate.getForObject(url, Venue[].class );
		return Arrays.asList(venueArray);
	}

	@Override
	public int addVenue(Venue venue) {
		// TODO Auto-generated method stub
		return 0;
	}

}
