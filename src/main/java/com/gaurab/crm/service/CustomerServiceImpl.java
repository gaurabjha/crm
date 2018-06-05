package com.gaurab.crm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurab.crm.dao.CustomerDAO;
import com.gaurab.crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDao;

	@Override
	@Transactional
	public List<Customer> getCustomer() {
		return customerDao.getCustomer();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDao.saveCustomer(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		return customerDao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDao.deleteCustomer(theId);

	}

	@Transactional
	@Override
	public List<Customer> searchCustomer(String theCriteria, String searchText) {
		return customerDao.searchCustomer(theCriteria, searchText);
	}

}
