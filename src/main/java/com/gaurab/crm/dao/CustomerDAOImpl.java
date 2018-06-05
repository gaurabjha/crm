package com.gaurab.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gaurab.crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomer() {

		// get the current Hibernate Session

		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> theQuery = currentSession.createQuery("FROM Customer ORDER BY lastName", Customer.class);

		// get the list of customers
		List<Customer> customer = theQuery.getResultList();

		// Return the result
		return customer;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get the Hibernate Session Object
		Session currentSession = sessionFactory.getCurrentSession();

		// Save the Customer
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// Fetch the Customer details based on the ID
		Customer customer = currentSession.get(Customer.class, theId);

		// send back the Customer

		return customer;

	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();
		/*
		 * Customer theCustomer = new Customer(); theCustomer.setId(theId);
		 * currentSession.delete(theCustomer);
		 */
		// delete object with the primary Key
		Query theQuery = currentSession.createQuery("DELETE FROM Customer WHERE id=:customerId");
		theQuery.setParameter("customerId", theId);

		theQuery.executeUpdate();

	}

	@Override
	public List<Customer> searchCustomer(String theCriteria, String searchText) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> theQuery = null;

		if (theCriteria.equals("firstName")) {
			theQuery = currentSession.createQuery("FROM Customer WHERE lower(firstName) LIKE :searchText",
					Customer.class);
		} else if (theCriteria.equals("lastName")) {
			theQuery = currentSession.createQuery("FROM Customer WHERE lower(lastName) LIKE :searchText",
					Customer.class);
		}else if (theCriteria.equals("email")) {
			theQuery = currentSession.createQuery("FROM Customer WHERE lower(email) LIKE :searchText",
					Customer.class);
		}

		theQuery.setParameter("searchText", "%" + searchText.toLowerCase() + "%");
		List<Customer> searchedCustomers = theQuery.list();

		return searchedCustomers;
	}

}
