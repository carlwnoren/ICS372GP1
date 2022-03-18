package edu.ics372.gp1.business.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import edu.ics372.gp1.business.collections.Matchable;
import edu.ics372.gp1.business.facade.Store;

/**
 * Repair plan object class
 * 
 * @author jpham
 *
 */
public class RepairPlan implements Matchable<String>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double cost;
	private String applianceID;
	private List<Customer> subscribers = new ArrayList<Customer>();

	public RepairPlan(double cost, String applianceID) {
		super();
		this.cost = cost;
		this.applianceID = applianceID;
	}

	/**
	 * Charges all subscribers of this plan and adds the revenue to the store
	 */
	public void chargePlan() {
		for (Customer customer : subscribers) {
			Store.getInstance().addRepairPlanRevenue(cost);
			customer.charge(cost);
		}
	}

	/**
	 * Adds a customer to the list of customers subscribed to this specific plan
	 * 
	 * @param customer
	 * @return true if customer was not already subscribed, false if already
	 *         subscribed
	 */
	public boolean enrollCustomer(Customer customer) {
		if (!subscribers.contains(customer)) {
			subscribers.add(customer);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Removes the given customer from the list of subscribers.
	 * 
	 * @param customer
	 * @return true if valid customer was removed, false if customer was not a
	 *         subscriber
	 */
	public boolean withdrawCustomer(Customer customer) {
		if (subscribers.contains(customer)) {
			subscribers.remove(customer);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCustomerEnrolled(Customer customer) {
		return subscribers.contains(customer);
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getApplianceID() {
		return applianceID;
	}

	public void setApplianceID(String applianceID) {
		this.applianceID = applianceID;
	}

	public List<Customer> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(List<Customer> subscribers) {
		this.subscribers = subscribers;
	}

	@Override
	public boolean matches(String customerID) {
		return this.applianceID.equals(customerID);
	}

	public String listAll() {
		return subscribers.toString();
	}

	// may need to modify hashcode and equals
	@Override
	public int hashCode() {
		return Objects.hash(applianceID, cost, subscribers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepairPlan other = (RepairPlan) obj;
		return Objects.equals(applianceID, other.applianceID)
				&& Double.doubleToLongBits(cost) == Double.doubleToLongBits(other.cost)
				&& Objects.equals(subscribers, other.subscribers);
	}

	@Override
	public String toString() {
		return "RepairPlan [cost=" + cost + ", applianceId=" + applianceID + ", subscribers=" + subscribers.toString()
				+ "]";
	}

	/**
	 * Safe to string statement to hide subscriber information.
	 * 
	 * @return
	 */
	public String safeToString() {
		return "RepairPlan for Appliance" + applianceID + " Cost: " + cost;
	}
}
