package com.techelevator.excelsior.model.dao.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.excelsior.model.Space;
import com.techelevator.excelsior.model.dao.SpaceDAO;

@Component
public class JDBCSpaceDao implements SpaceDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCSpaceDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Space> listSpacesByVenueId(int venueId) {

		String SQLSelect = "SELECT id, venue_id, name, is_accessible, open_from, open_to, daily_rate::decimal, max_occupancy FROM space WHERE venue_id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQLSelect, venueId);

		List<Space> spaces = new ArrayList<Space>();

		while (rowSet.next()) {
			Space space = mapRowToSpace(rowSet);
			spaces.add(space);
		}
		return spaces;
	}

	@Override
	public List<Space> getSpacesIfAvailable(int venueId, LocalDate startDate, int numberOfDays,
			int numberOfAttendees) {

		String SQLSelect = "SELECT space.id, venue_id, name, is_accessible, open_from, open_to, daily_rate::decimal, max_occupancy "
				+ "FROM SPACE "
				+ "WHERE venue_id = ? "
				+ "AND max_occupancy >= ? "
				+ "AND (space.open_from IS NULL OR (space.open_from <= EXTRACT(month FROM ?::date) AND space.open_to >= EXTRACT(month FROM ?::date))) "
				+ "AND id NOT IN( "
				+ "SELECT DISTINCT space.id "
				+ "FROM space "
				+ "LEFT JOIN reservation ON space.id = reservation.space_id "
				+ "WHERE venue_id = ? "
				+ "AND (reservation.start_date, reservation.end_date) OVERLAPS (?, ?))";

		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQLSelect, venueId, numberOfAttendees, startDate, startDate.plusDays(numberOfDays - 1), venueId,
				 startDate.minusDays(1), startDate.plusDays(numberOfDays));

		List<Space> spaces = new ArrayList<Space>();

		while (rowSet.next()) {
			Space space = mapRowToSpace(rowSet);
			spaces.add(space);
		}
		return spaces;
		
	}

	private Space mapRowToSpace(SqlRowSet row) {
		Space space = new Space();

		space.setSpaceId(row.getInt("id"));
		space.setVenueId(row.getInt("venue_id"));
		space.setName(row.getString("name"));
		space.setIsAccessible(row.getBoolean("is_accessible"));
		space.setOpenFrom(row.getInt("open_from"));
		space.setOpenTo(row.getInt("open_to"));
		space.setDailyRate(row.getDouble("daily_rate"));
		space.setMaxOccupancy(row.getInt("max_occupancy"));

		return space;
	}

	@Override
	public Space getSpaceById(int spaceId) {
		String SQLSelect = "SELECT id, venue_id, name, is_accessible, open_from, open_to, daily_rate::decimal, max_occupancy FROM space WHERE id = ?";
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQLSelect, spaceId);
		Space space = new Space();
		if(rowSet.next()) {
			space = mapRowToSpace(rowSet);
		}
		return space;
	}

}
