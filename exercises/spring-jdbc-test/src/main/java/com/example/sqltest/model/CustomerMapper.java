package com.example.sqltest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Customer customer = new Customer();
		customer.setId(resultSet.getLong("id"));
		customer.setFirstName(resultSet.getString("firstname"));
		customer.setLastName(resultSet.getString("lastname"));

		customer.setSex(resultSet.getString("sex"));
		customer.setLanguage(resultSet.getString("language"));
		customer.setEngagement(resultSet.getBigDecimal("engagement"));
		customer.setEmail(resultSet.getNString("email"));
		customer.setPhone(resultSet.getNString("phone"));

		return customer;
	}

}