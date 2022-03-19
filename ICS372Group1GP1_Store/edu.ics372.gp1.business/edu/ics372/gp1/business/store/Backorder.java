/**
 * Backorder class, used to store number of backorders when an appliance stock is too low to complete sale
 * 
 *  @author Kean Jaycox, Justin Pham, Carl Noren
 */

package edu.ics372.gp1.business.store;

import java.io.Serializable;

import edu.ics372.gp1.business.collections.Matchable;

@SuppressWarnings("serial") //warning not needed, as object serializes without ID
public class Backorder implements Matchable<String>, Serializable {

	protected String backorderID;
	protected Appliance appliance;
	protected Customer customer;
	int quantity;

	/**
	 * This creates a new instance of backorder.
	 * 
	 * @param appliance
	 * @param quantity
	 * @param customer
	 * @param backorderID
	 */
	public Backorder(Appliance appliance, int quantity, Customer customer, String backorderID) {
		this.appliance = appliance;
		this.quantity = quantity;
		this.customer = customer;
		this.backorderID = backorderID;
	}

	/**
	 * Used to see if given parameter matches field in backorder object
	 */
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
		return "Backorder ID: " + backorderID + ", Appliance ID: " + appliance.getApplianceID() + " Quantity: "
				+ quantity;
	}
}