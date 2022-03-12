/**
 * This class implements a CustomerList, which stores a list of all avaiable Customer objects.
 * This class is a singleton.
 * It has the customerList and customers fields.
 * It has the getInstance and search methods.
 */
package edu.ics372.gp1.collections;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.store.Customer;

public class CustomerList implements ItemList<Customer, String>, Serializable {
	/**
	 * This field stores the singleton CustomerList object.
	 */
	private static CustomerList customerList;
	
	private static final String CUSTOMER_STRING = "C";
	private int idCounter = 1000;
	/**
	 * This field stores all Customer objects stored in this CustomerList.
	 */
	private List<Customer> customers = new LinkedList<Customer>();

	private CustomerList() {

	}

	/**
	 * This method creates a CustomerList if none yet exists.
	 * 
	 * @return either the newly created or existing customerList
	 */
	public static CustomerList getInstance() {
		if (customerList == null) {
			customerList = new CustomerList();
		}
		return customerList;
	}

	/**
	 * This method searches the customers field for an Customer object with a
	 * matching customerID
	 * 
	 * @return the matching Customer or null, if no match is found.
	 */
	@Override
	public Customer search(String customerID) {
		for (Customer customer : customers) {
			if (customer.matches(customerID)) {
				return customer;
			}
		}
		return null;
	}

	public void add(Customer customer) {
	    customer.setId(CUSTOMER_STRING + idCounter);
	    customerList.add(customer);
	}
	
	/**
	 * Method to return list of all customers
	 */
	public String listAll() {
		return customers.toString();
	}

	@Override
	public String toString() {
		return "CustomerList [customers=" + customers + "]";
	}

}
