package com.techelevator.parks.dao;

import java.util.List;

import org.junit.*;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.dao.DAOIntegrationTest;
import com.techelevator.parks.model.State;

public class JdbcParksDaoIntegrationTest extends DAOIntegrationTest {

	private StateDao stateDao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		jdbcTemplate = new JdbcTemplate(getDataSource());
		stateDao = new JdbcStateDao(jdbcTemplate);
	}
	
	@Test
	public void select_states() {
		List<State> states = stateDao.list();
		int originalCount = states.size();
		int added = 3;
		insertStates(added);
		int newCount = stateDao.list().size();
		Assert.assertEquals(originalCount + added, newCount);
	}
	
	private void insertStates(int cnt) {
		String sql = "INSERT INTO states VALUES (?,?)";
		for (int i = 0; i < cnt; i++) {
			jdbcTemplate.update(sql, i, "Test State " + i );
		}
	}
	
}
