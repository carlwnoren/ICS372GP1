/**
 * Customer classes used store customer info and owned appliances and repairplan
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */
package edu.ics372.gp1.business.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.ics372.gp1.business.collections.Matchable;

public class Customer implements Matchable<String>, Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNumber;
	private String id;
	private double accountBalance;
	private List<Appliance> appliances = new ArrayList<Appliance>();// appliances owned by customer
	private List<RepairPlan> repairPlansEnrolledIn = new ArrayList<RepairPlan>();

	/**
	 * Customer constructor to accept name, address, and phoneNumber. Generates new
	 * member ID off of static variable
	 * 
	 * @param name
	 * @param address
	 * @param phoneNumber
	 */
	public Customer(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accountBalance = 0;
	}

	/**
	 * adds a single appliance to customer's list of appliances
	 * 
	 * @param appliance
	 */
	public void addAppliance(Appliance appliance) {
		appliances.add(appliance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<RepairPlan> getRepairPlansEnrolledIn() {
		return repairPlansEnrolledIn;
	}

	public List<Appliance> getAppliances() {
		return appliances;
	}

	public void setAppliances(List<Appliance> appliances) {
		this.appliances = appliances;
	}

	/**
	 * Adds a repair plan to customer's list of repair plans
	 * 
	 * @param repairPlan
	 * @return
	 */
	public boolean addRepairPlan(RepairPlan repairPlan) {
		if (!repairPlansEnrolledIn.contains(repairPlan)) {
			repairPlansEnrolledIn.add(repairPlan);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * removes a repair plan to customer's list of repair plans
	 * 
	 * @param repairPlan
	 * @return
	 */
	public boolean removeRepairPlan(RepairPlan repairPlan) {
		if (repairPlansEnrolledIn.contains(repairPlan)) {
			repairPlansEnrolledIn.remove(repairPlan);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * charges customer, adds a certain owed balance to their account
	 * 
	 * @param cost
	 */
	public void charge(double cost) {
		accountBalance += cost;
	}

	/**
	 * Checks to see if customer is enrolled in any plans or none at all
	 * 
	 * @return
	 */
	public boolean isEnrolledInRepairPlan() {
		return !repairPlansEnrolledIn.isEmpty();
	}

	/**
	 * Checks to see if parameter given matches a the ID of this Customer.
	 * 
	 * @param customerID
	 */
	@Override
	public boolean matches(String customerID) {
		return this.id.equals(customerID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, name, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + ", id=" + id
				+ ", accountBalance=" + accountBalance + "]" + " Enrolled in plan?: " + this.isEnrolledInRepairPlan();
	}

}
