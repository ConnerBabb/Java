package com.techelevator.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcBalanceDao implements BalanceDao {

	private JdbcTemplate template;
	
	public JdbcBalanceDao(JdbcTemplate jdbcTemplate) {
		this.template = jdbcTemplate;
	}
	
	
	@Override
	public Balance getBalance(int id) {
		String sql = "SELECT account_id, balance FROM accounts WHERE user_id = ?";
		
		SqlRowSet rows = template.queryForRowSet(sql, id);
		
		if (rows.next()) {
			return new Balance(rows.getInt("account_id"), rows.getDouble("balance"));
		}
		
		return null;
	}

	
	
	
}
