package edu.ics372.gp1.business.store;

import java.io.Serializable;

import edu.ics372.gp1.business.collections.Matchable;

public class Backorder implements Matchable<String>, Serializable{
	String backorderID;
	Appliance appliance;
	Customer customer;
	int quantity;
	
	public Backorder(Appliance appliance, int quantity, Customer customer, String backorderID) {
		this.appliance = appliance;
		this.quantity = quantity;
		this.customer = customer;
		this.backorderID = backorderID;
	}
	
	@Override
	public boolean matches(String match) {
		return backorderID.equals(match);
	}

	public Appliance getAppliance() {
		return appliance;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getBackorderID() {
		return backorderID;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	@Override
	public String toString() {
		return "Backorder ID: " + backorderID + ", Appliance ID: " + appliance.getApplianceID() + " Quantity: " + quantity;
	}
}