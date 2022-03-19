/**
 * This class implements a KitchenRange object. It has no unique fields from Appliance.
 * @author - Carl Noren, Justin Pham, Kean Jay
 */
package edu.ics372.gp1.business.store;


public class KitchenRange extends Appliance {
	private static final long serialVersionUID = 1L;

	/**
	 * This constructor calls the super constructor of Appliance.
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 */
	public KitchenRange(String brand, String model, double cost, String applianceID) {
		super(brand, model, cost, applianceID);
	}

	public String toString() {
		return this.brand + "\t" + this.model + "\t" + this.cost + "\t" + this.stock + "\t" + this.applianceID;
	}
}
