package com.techelevator.parks.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import com.techelevator.parks.dao.FavoriteParkDao;
import com.techelevator.parks.dao.StateDao;
import com.techelevator.parks.model.FavoritePark;
import com.techelevator.parks.model.State;

@RestController
@CrossOrigin
public class ParksController {

	private StateDao stateDao;
	private FavoriteParkDao parkDao;
	
	public ParksController(StateDao stateDao, FavoriteParkDao parkDao) {
		this.stateDao = stateDao;
		this.parkDao = parkDao;
	}
	
	// /states
	@RequestMapping(path="/states", method=RequestMethod.GET)
	public List<State> getStates() {
		return stateDao.list();
	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path="/parks", method=RequestMethod.POST)
	public FavoritePark addPark(@RequestBody FavoritePark park, Principal user) {
		parkDao.add(park, user.getName());
		return park;
	}
	
	
	
}
