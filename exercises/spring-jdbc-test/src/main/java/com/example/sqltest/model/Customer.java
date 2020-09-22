package com.example.sqltest.model;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

//@Entity
public class Customer {

	private Long       id;

	private String     firstName;
	private String     lastName;

	private String     sex;

	private String     language;

	@Min((long) 0.00)
	@Max((long) 10.00)
	private BigDecimal engagement;

	private String     email;
	private String     phone; //Yes, phone number can have other symbols than numbers. Do regex check for input validation
	// TODO add street address table
	// TODO add invoices data table (requires a joining table between CUSTOMER & INVOICE tables)

	// Setters

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setEngagement(BigDecimal engagement) {
		this.engagement = engagement;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getters

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSex() {
		return sex;
	}

	public String getLanguage() {
		return language;
	}

	public BigDecimal getEngagement() {
		return engagement;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	// Constructors

	public Customer() {
		this.id = (long) 0;
		this.firstName = null;
		this.lastName = null;

		this.sex = null;
		this.language = null;
		this.engagement = null;
		this.email = null;
		this.phone = null;
	}

	public Customer(
			Long id,
			String firstName,
			String lastName,

			String sex,
			String language,
			BigDecimal engagement,
			String email,
			String phone
			) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;

		this.sex = sex;
		this.language = language;
		this.engagement = engagement;
		this.email = email;
		this.phone = phone;
	}

	public Customer(
			String firstName,
			String lastName,

			String sex,
			String language,
			BigDecimal engagement,
			String email,
			String phone
			) {

		this.id = (long) 0;
		this.firstName = firstName;
		this.lastName = lastName;

		this.sex = sex;
		this.language = language;
		this.engagement = engagement;
		this.email = email;
		this.phone = phone;
	}

	// Overrides

	@Override
	public String toString() {
		return "[" +
	"id: "         + this.id         + ", " +
	"firstname: "  + this.firstName  + ", " +
	"lastname: "   + this.lastName   + ", " +
	"sex: "        + this.sex        + ", " +
	"language: "   + this.language   + ", " +
	"engagement: " + this.engagement + ", " +
	"email: "      + this.email      + ", " +
	"phone: "      + this.phone      +
	"]";

	}

}