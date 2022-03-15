package edu.ics372.gp1.business.store;

import java.io.Serializable;

import edu.ics372.gp1.business.collections.Matchable;

public class Backorder implements Matchable<String>, Serializable{
	String backorderID;
	Appliance appliance;
	int quantity;
	
	public Backorder(Appliance appliance, int quantity, String backorderID) {
		this.appliance = appliance;
		this.quantity = quantity;
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
	
	@Override
	public String toString() {
		return "Backorder ID: " + backorderID + ", Appliance ID: " + appliance.getApplianceID() + " Quantity: " + quantity;
	}
}