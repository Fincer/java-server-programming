package com.example.sqltest;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.example.sqltest.model.Customer;
import com.example.sqltest.model.CustomerDAOImpl;

@SpringBootApplication
public class SqlTestApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(SqlTestApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SqlTestApplication.class, args);
	}

	@Bean
	public CommandLineRunner CustomerRunner(CustomerDAOImpl customerDAO) {

		return (args) -> {

			logger.info("Deleting old database table entries");
			customerDAO.dropAll();

			logger.info("Creating new database table entries");

			customerDAO.save(new Customer(
					"Daniel", "Thyssenlauf",
					"man", "English",
					new BigDecimal("0.45"),
					"danthyf@gmail.com", null
					));

			customerDAO.save(new Customer(
					"Janina", "Riikanen",
					"woman", "Finnish",
					new BigDecimal("1.74"),
					"janskuuu@yahoo.com", "+358405341242"
					));

			logger.info("Created a new database table with the following values");
			for (Customer customer : customerDAO.findAll()) {
				logger.info("CUSTOMER table: {}", customer.toString());
			}

		};

	}
}
