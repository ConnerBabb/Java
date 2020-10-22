package com.techelevator.tenmo.account.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao {

	private JdbcTemplate template;
	
	public JdbcAccountDao(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Account get(String username) {
		Account account = new Account();
		String sql = "SELECT accounts.account_id, accounts.user_id, accounts.balance FROM accounts " + 
				 "JOIN users ON accounts.user_id = users.user_id " + 
				 "WHERE users.username = ?";
		
		SqlRowSet rows = template.queryForRowSet(sql, username);
		
		
		if (rows.next()) {
			account.setUserId( rows.getInt("user_id") );
			account.setAccountId( rows.getInt("account_id") );
			account.setBalance( rows.getDouble("balance") );
		}
		
		
		return account;
	}
	
	
	
}
