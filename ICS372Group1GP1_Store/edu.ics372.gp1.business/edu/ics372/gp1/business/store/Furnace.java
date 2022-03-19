/**
 * This class implements a furnace. It has the maxHeatOutput field unique from Appliance.
 * @author - Carl Noren, Justin Pham, Kean Jay
 */
package edu.ics372.gp1.business.store;

public class Furnace extends Appliance {
	private static final long serialVersionUID = 1L;
	private int maxHeatOutput;

	/**
	 * This constructor call's Appliance's constructor and sets the 
	 * maxHeatOutput field, which is unique to Furnace.
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 * @param maxHeatOutput
	 */
	public Furnace(String brand, String model, double cost, String applianceID, int maxHeatOutput) {
		super(brand, model, cost, applianceID);
		this.maxHeatOutput = maxHeatOutput;
	}

	public int getMaxHeatOutput() {
		return maxHeatOutput;
	}

	public void setMaxHeatOutput(int maxHeatOutput) {
		this.maxHeatOutput = maxHeatOutput;
	}

	@Override
	public String toString() {
		return "Furnace [maxHeatOutput=" + maxHeatOutput + ", brand=" + brand + ", model=" + model + ", cost=" + cost
				+ ", stock=" + stock + ", applianceID=" + applianceID + "]";
	}

}
