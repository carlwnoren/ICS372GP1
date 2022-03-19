/**
 * Appliance Class, used as frame work for all other appliance types. 
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 *
 */
package edu.ics372.gp1.business.store;

import java.io.Serializable;

import edu.ics372.gp1.business.collections.Matchable;

public abstract class Appliance implements Matchable<String>, Serializable {

	private static final long serialVersionUID = 1L;
	protected String brand;
	protected String model;
	protected double cost;
	protected int stock = 0;
	protected String applianceID;

	@Override
	public boolean matches(String customerID) {
		return this.applianceID.equals(customerID);
	}

	/**
	 * Constructor used to create new object.
	 * 
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 */
	public Appliance(String brand, String model, double cost, String applianceID) {
		this.brand = brand;
		this.model = model;
		this.cost = cost;
		this.applianceID = applianceID;
	}

	/**
	 * used to add the number of appliances to stock
	 * 
	 * @param quantity
	 */
	public void addStock(int quantity) {
		stock += quantity;
	}

	/**
	 * used to remove a number of appliances from stock
	 * 
	 * @param quantity
	 */
	public void removeStock(int quantity) {
		if (stock < quantity) {
			throw new IllegalArgumentException("Stock cannot be less than 0.");
		}
		stock -= quantity;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public double getCost() {
		return cost;
	}

	public int getStock() {
		return stock;
	}

	public String getApplianceID() {
		return applianceID;
	}

	public void setApplianceID(String applianceID) {
		this.applianceID = applianceID;
	}

}
