package com.gaurab.crm.service;

import java.util.List;

import com.gaurab.crm.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomer();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomer(String theCriteria, String searchText);

}
