package edu.ics372.gp1.business.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.ics372.gp1.business.collections.Matchable;

/**
 * @author jpham
 *
 */
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
		// this.id = id; since customerList (line 61) sets id think this can be removed
	}

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
	
	public boolean addRepairPlan(RepairPlan repairPlan) {
		if (!repairPlansEnrolledIn.contains(repairPlan)) {
			repairPlansEnrolledIn.add(repairPlan);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeRepairPlan(RepairPlan repairPlan) {
		if (repairPlansEnrolledIn.contains(repairPlan)) {
			repairPlansEnrolledIn.remove(repairPlan);
			return true;
		} else {
			return false;
		}
	}

	public void charge(double cost) {
		accountBalance += cost;
	}

	public boolean isEnrolledInRepairPlan() {
		return repairPlansEnrolledIn.isEmpty();
	}

	@Override
	public boolean matches(String customerID) {
		return this.id.equals(customerID);
	}

	// may need to modify hashcode and equals
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
