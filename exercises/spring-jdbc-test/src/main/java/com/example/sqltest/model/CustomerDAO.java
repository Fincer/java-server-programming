package com.example.sqltest.model;

import java.util.List;

public interface CustomerDAO {

	public void save(Customer customer);

	public Customer findById(Long id);
	public List<Customer> findAll();
	public void dropAll();

}