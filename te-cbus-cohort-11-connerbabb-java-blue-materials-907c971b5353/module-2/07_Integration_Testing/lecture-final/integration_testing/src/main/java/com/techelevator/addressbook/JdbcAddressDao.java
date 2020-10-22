package com.techelevator.addressbook;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcAddressDao implements AddressDao {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcAddressDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Address getById(int addressId) {
		Address address = null;
		String sql = "SELECT address_id, street, line_two, city, postal_code, district, address_type, type_other_description FROM address WHERE address_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, addressId);
		if (rows.next()) {
			address = mapRowToAddress( rows );
		}
		return address;
	}

	@Override
	public List<Address> getByPersonId(int personId) {
		List<Address> addresses = new ArrayList<Address>();
		
		String sql = "SELECT a.address_id, street, line_two, city, postal_code, district, address_type, type_other_description " + 
				"FROM address a " + 
				"JOIN person_address pa ON a.address_id = pa.address_id " + 
				"WHERE person_id = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, personId);
		
		while (rows.next()) {
			addresses.add( mapRowToAddress(rows) );
		}

		return addresses;
	}

	@Override
	public void save(Address address, int personId) {
		String sql = "INSERT INTO address (address_id, street, line_two, city, postal_code, district, address_type, type_other_description)"
					+ " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?) RETURNING address_id";
		
		Integer addressId = jdbcTemplate.queryForObject(sql, Integer.class, address.getStreet(), address.getLineTwo(), 
				address.getCity(), address.getPostalCode(), address.getDistrict(), address.getAddressType(), address.getTypeOtherDescription() );
		address.setAddressId(addressId);
		savePersonAddress(personId, addressId);
	}
	
	private void savePersonAddress(int personId, int addressId) {
		String sql = "INSERT INTO person_address (person_id, address_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, personId, addressId);
	}
	

	@Override
	public void update(Address address) {
		
		String sql = "UPDATE address SET street = ?, line_two = ?, city = ?, district = ?, postal_code = ?, "
					+ "address_type = ?, type_other_description = ? WHERE address_id = ?";
		jdbcTemplate.update(sql, address.getStreet(), address.getLineTwo(), 
				address.getCity(), address.getDistrict(), address.getPostalCode(),
				address.getAddressType(), address.getTypeOtherDescription(), address.getAddressId());
	}

	@Override
	public void deleteFromPerson(int addressId, int personId) {
		deleteFromPersonAddress(addressId, personId);
		if ( !hasExistingRelationships(addressId) ) {
			String sql = "DELETE FROM address WHERE address_id = ?";
			jdbcTemplate.update(sql, addressId);
		}
	}
	
	private boolean hasExistingRelationships(int addressId) {
		String sql = "SELECT * FROM person_address WHERE address_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, addressId);
		if (rows.next()) {
			return true;
		}
		return false;
	}
	
	private void deleteFromPersonAddress(int addressId, int personId) {
		String sql = "DELETE FROM person_address WHERE person_id = ? AND address_id = ?";
		jdbcTemplate.update(sql);
	}

	private Address mapRowToAddress(SqlRowSet row) {
		Address address = new Address();
		address.setAddressId( row.getInt("address_id"));
		address.setStreet( row.getString("street"));
		address.setLineTwo( row.getString("line_two"));
		address.setCity( row.getString("city"));
		address.setDistrict( row.getString("district") );
		address.setPostalCode( row.getString("postal_code") );
		address.setAddressType( row.getString("address_type") );
		address.setTypeOtherDescription( row.getString("type_other_description") );
		return address;
	}
	
}
