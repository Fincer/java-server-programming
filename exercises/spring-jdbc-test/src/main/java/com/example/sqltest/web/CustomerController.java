package com.example.sqltest.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.sqltest.model.Customer;
import com.example.sqltest.model.CustomerDAO;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping(
			value = "/customers",
			method = RequestMethod.GET
			)
	public String customerWebFormGet(Model dataModel) {

		List<Customer> customers = customerDAO.findAll();
		dataModel.addAttribute("customers", customers);
		return "customers";
	}

	@RequestMapping("*")
	public String redirectWebForm() {
		return "redirect:/customers";
	}
}