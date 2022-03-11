package edu.ics372.gp1.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.ics372.gp1.facade.Store;

/**
 * Repair plan object class
 * 
 * @author jpham
 *
 */
public class RepairPlan implements Matchable<String>, Serializable{

	private double cost;
	private String applianceID;
	private List<Customer> subscribers = new ArrayList<Customer>();

	public RepairPlan(double cost, String applianceID, List<Customer> subscribers) {
		super();
		this.cost = cost;
		this.applianceID = applianceID;
		this.subscribers = subscribers;
	}

	/** Charges all subscribers of this plan and adds the revenue to the store
	 */
	public void chargePlan() {
		for (Customer customer: subscribers) {
			Store.getInstance().addRepairPlanRevenue(cost);
			customer.charge(cost);
		}
	}

	/**
	 * Adds a customer to the list of customers subscribed to this specific plan
	 * @param customer
	 * @return true if customer was not already subscribed, false if already subscribed
	 */
	public boolean enrollCustomer(Customer customer) {
		if (!subscribers.contains(customer)) {
		subscribers.add(customer);
		return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Removes the given customer from the list of subscribers.
	 * @param customer
	 * @return true if valid customer was removed, false if customer was not a subscriber
	 */
	public boolean withdrawCustomer(Customer customer) {
		if (subscribers.contains(customer)) {
			subscribers.remove(customer);
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public boolean matches(String customerID) {
		return this.applianceID.equals(customerID);
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

	// need to implement save
}
