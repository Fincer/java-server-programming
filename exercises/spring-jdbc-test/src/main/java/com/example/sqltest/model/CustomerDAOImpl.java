package com.example.sqltest.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Setters

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// Getters
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	// Abstract methods

	@Override
	public void save(Customer customer) {
		String sqlQuery = "INSERT INTO CUSTOMER(" +
				"firstname"  + "," +
				"lastname"   + "," +
				"sex"        + "," +
				"language"   + "," +
				"engagement" + "," +
				"email"      + "," +
				"phone"      +
				") VALUES (?,?,?,?,?,?,?)";

		Object[] sqlData = new Object[] {
				customer.getFirstName(),
				customer.getLastName(),

				customer.getSex(),
				customer.getLanguage(),
				customer.getEngagement(),
				customer.getEmail(),
				customer.getPhone()
		};

		jdbcTemplate.update(sqlQuery, sqlData);
	}

	@Override
	public Customer findById(Long id) {

		String sqlQuery = "SELECT * FROM CUSTOMER WHERE ID = ?";
		Object[] queryData = new Object[] { id };

		RowMapper<Customer> mapper = new CustomerMapper();

		Customer customer = jdbcTemplate.queryForObject(sqlQuery, queryData, mapper);

		return customer;
	}

	@Override
	public List<Customer> findAll() {

		String sqlQuery = "SELECT * FROM CUSTOMER";
		RowMapper<Customer> mapper = new CustomerMapper();
		List<Customer> customers = jdbcTemplate.query(sqlQuery, mapper);

		return customers;
	}

	@Override
	public void dropAll() {
		String sqlQueryDelete = "DELETE FROM CUSTOMER";
		String sqlQueryResetId = "ALTER TABLE CUSTOMER AUTO_INCREMENT = 1";
		jdbcTemplate.execute(sqlQueryDelete);
		jdbcTemplate.execute(sqlQueryResetId);
	}

}