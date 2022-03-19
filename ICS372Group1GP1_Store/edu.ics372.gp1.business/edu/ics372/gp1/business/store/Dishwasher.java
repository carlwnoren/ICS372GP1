/**
 * Class used to represent a dishwasher.
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */

package edu.ics372.gp1.business.store;

public class Dishwasher extends Appliance {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor to create a new dishwasher.
	 * 
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 */
	public Dishwasher(String brand, String model, double cost, String applianceID) {
		super(brand, model, cost, applianceID);
	}

	@Override
	public String toString() {
		return this.brand + "\t" + this.model + "\t" + this.cost + "\t" + this.stock + "\t" + this.applianceID;
	}
}