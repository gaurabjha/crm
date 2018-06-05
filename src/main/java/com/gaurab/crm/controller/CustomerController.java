package com.gaurab.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gaurab.crm.entity.Customer;
import com.gaurab.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// get or inject The Customer Service
	@Autowired
	private CustomerService customerService;

	@GetMapping(value = "/list")
	public String listCustomers(Model theModel) {
		// get the customers
		List<Customer> theCustomers = customerService.getCustomer();

		// add the customers to the model
		theModel.addAttribute("customer", theCustomers);

		return "list-customers";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String customerHome(Model theModel) {

		return "home";
	}

	@GetMapping(value = "showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// prepare the customer object for the form
		Customer theCustomer = new Customer();

		theModel.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

		// save customer using Customer Service
		customerService.saveCustomer(theCustomer);

		return "redirect:/customer/list";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {

		// get the customer from the database using the service

		Customer theCustomer = customerService.getCustomer(theId);

		// set the customer as a model attribute to pre populate the form
		theModel.addAttribute("customer", theCustomer);
		System.out.println(theCustomer);
		// send over to the customer form
		return "customer-form";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		// pass the ID to customer Service and delete It
		customerService.deleteCustomer(theId);

		return "redirect:/customer/list";
	}

	@RequestMapping("/search")
	public String search(@RequestParam("searchCriteria") String theCriteria , @RequestParam("searchText") String searchText, Model theModel) {
		// get the customers
		searchText = searchText.trim();
		System.out.println(searchText.length());
		List<Customer> theCustomers;
		if (searchText != null && searchText.length() != 0) {
			System.out.println("I'm searching");
			theCustomers = customerService.searchCustomer(theCriteria,searchText);
		} else {
			System.out.println("I'm Redirecting to List");
			return "redirect:/customer/list";
		}
		// add the customers to the model
		theModel.addAttribute("customer", theCustomers);

		return "list-customers";
	}

	@RequestMapping("/err")
	public String err() {
		return "error-page";
	}
	
	
}
