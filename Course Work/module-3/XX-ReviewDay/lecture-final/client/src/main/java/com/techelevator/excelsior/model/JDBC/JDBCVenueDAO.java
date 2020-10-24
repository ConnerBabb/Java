package com.techelevator.excelsior.model.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.sql.RowSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.excelsior.model.Venue;
import com.techelevator.excelsior.model.VenueDAO;

public class JDBCVenueDAO implements VenueDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<Venue> selectAllVenues() {
		List<Venue> venues = new ArrayList<Venue>();
		
		String sqlSelect = "SELECT venue.id, venue.name, city.id AS city_id, city.name AS city_name, state_abbreviation AS state, description FROM venue JOIN city ON city.id = venue.city_id JOIN state ON city.state_abbreviation = state.abbreviation";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSelect);
		
		while(rowSet.next()) {
			Venue venue = mapToVenue(rowSet);
			venues.add(venue);
			
		}
		for (Venue venue : venues) {
			rowSet.beforeFirst();
			venue.setCategories(getCatagoryById(venue.getVenueId()));
		}
		return venues;
	}

	@Override
	public Venue getVenueById(int venueId) {
		String sqlSelect = "SELECT venue.id, venue.name, city.id AS city_id, city.name AS city_name, state_abbreviation AS state, description FROM venue JOIN city ON city.id = venue.city_id JOIN state ON city.state_abbreviation = state.abbreviation WHERE venue.id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSelect, venueId);
		Venue venue = new Venue();
		while(rowSet.next()) {
			venue = mapToVenue(rowSet);
			
			
		}
		venue.setCategories(getCatagoryById(venue.getVenueId()));
		return venue;
		
		
	}

	@Override
	public List<Venue> searchVenueByName(String name) {
		List<Venue> venues = new ArrayList<Venue>();
		
		String sqlSelect = "SELECT venue.id, venue.name, city.id AS city_id, city.name AS city_name, state_abbreviation AS state, description FROM venue JOIN city ON city.id = venue.city_id JOIN state ON city.state_abbreviation = state.abbreviation WHERE venue.name = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSelect, name);
		
		while(rowSet.next()) {
			Venue venue = mapToVenue(rowSet);
			venues.add(venue);
			
		}
		for (Venue venue : venues) {
			rowSet.beforeFirst();
			venue.setCategories(getCatagoryById(venue.getVenueId()));
		}
		return venues;
		
	}

	@Override
	public int addVenue(Venue venue) {
		
		return 0;
	}
	
	private String getCatagoryById(int venueId) {
		String sqlSelect = "SELECT category.id, category.name FROM category\n" + 
				"JOIN category_venue ON category.id = category_venue.category_id \n" + 
				"WHERE category_venue.venue_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sqlSelect, venueId);
		String categories = "";
		
		
		while(rowSet.next()) {
			if (categories.equals("")) {
				categories = rowSet.getString("name");
				continue;
			}
			categories = categories + ", " + rowSet.getString("name");
		}
		
		
		return categories;
	}
	
	private Venue mapToVenue(SqlRowSet rowSet) {
		Venue venue = new Venue();
		venue.setVenueId(rowSet.getInt("id"));
		venue.setName(rowSet.getString("name"));
		venue.setCity(rowSet.getString("city_name"));
		venue.setState(rowSet.getString("state"));
		venue.setDescription(rowSet.getString("description"));
		venue.setCityId(rowSet.getInt("city_id"));
		
		
		return venue;
		
		
	}

}
