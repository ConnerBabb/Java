package com.techelevator.parks.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.parks.model.FavoritePark;

@Component
public class JdbcFavoriteParkDao implements FavoriteParkDao {

	private JdbcTemplate db;
	
	public JdbcFavoriteParkDao(JdbcTemplate jdbcTemplate) {
		this.db = jdbcTemplate;
	}
	
	
	@Override
	public List<FavoritePark> list(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(FavoritePark park, String username) {
		String sql = "INSERT INTO favorite_parks (id, name, state, description, url, imageurl) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING id";
		Integer id = db.queryForObject(sql, Integer.class, park.getName(), park.getState(), park.getDescription(), park.getUrl(), park.getImageUrl());
		park.setId(id);
		
		String joinsSql = "INSERT INTO users_favorite_parks VALUES ( (SELECT user_id FROM users WHERE username = ?), ?)";
		db.update(joinsSql, username, id);
	}

}
