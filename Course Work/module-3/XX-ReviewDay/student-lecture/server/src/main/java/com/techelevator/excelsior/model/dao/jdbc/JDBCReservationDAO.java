package com.techelevator.excelsior.model.dao.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.excelsior.model.Reservation;
import com.techelevator.excelsior.model.dao.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int makeAReservation(int spaceId, LocalDate startDate, int numberOfDays, int numberOfAttendees, String reservedFor) {
		String sqlAddReservation = "INSERT INTO reservation (space_id, number_of_attendees, start_date, end_date, reserved_for)"
				+ "VALUES (?, ?, ?, ?,?) RETURNING reservation_id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlAddReservation, spaceId, numberOfAttendees, startDate, startDate.plusDays(numberOfDays - 1),
				reservedFor);

		int reservationId = 0;

		if (rows.next()) {
			reservationId = rows.getInt("reservation_id");
			
		}

		return reservationId;

	}
	
	@Override
	public Reservation getReservationById(int reservationId) {
		String sqlSelect= "SELECT reservation_id, space_id, number_of_attendees, start_date, end_date, reserved_for FROM reservation where reservation_id =?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlSelect,reservationId);
		
		Reservation reservation= new Reservation();
		if (rows.next()) {		
			reservation=mapRowToReservation(rows);
		}
		
		return reservation;
	}
	
	@Override
	public List<Reservation> getReservationsInNext30ByVenueId(int venueId) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		String sqlSelect = "SELECT reservation_id, reservation.space_id, number_of_attendees, start_date, end_date, reserved_for, \n" + 
				"(EXTRACT(YEAR FROM NOW())||'-'||EXTRACT(MONTH FROM NOW())||'-'||EXTRACT(DAY FROM NOW()))::date + 30 \n" + 
				"FROM reservation \n" + 
				"JOIN space ON reservation.space_id = space.id \n" + 
				"WHERE (NOW(),(EXTRACT(YEAR FROM NOW())||'-'||EXTRACT(MONTH FROM NOW())||'-'||EXTRACT(DAY FROM NOW()))::date + 30) \n" + 
				"OVERLAPS (start_date, end_date)\n " + 
				" AND space.venue_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlSelect, venueId);
		
		while (rows.next()) {
			Reservation reservation = mapRowToReservation(rows);
			reservations.add(reservation);
		}
		// TODO Auto-generated method stub
		return reservations;
	}

	@Override
	public List<Reservation> getResListByIdAndDate(int spaceId, LocalDate startDate, int numberOfDays) {
		// TODO Auto-generated method stub
		return null;
	}

	private Reservation mapRowToReservation(SqlRowSet row) {

		Reservation reservation = new Reservation();

		reservation.setResId(row.getInt("reservation_id"));
		reservation.setSpaceId(row.getInt("space_id"));
		reservation.setNumberOfAttendees(row.getInt("number_of_attendees"));
		reservation.setStartDate(row.getDate("start_date").toLocalDate());
		reservation.setEndDate(row.getDate("end_date").toLocalDate());
		reservation.setReservedFor(row.getString("reserved_for"));

		return reservation;
	}

	

	

}
