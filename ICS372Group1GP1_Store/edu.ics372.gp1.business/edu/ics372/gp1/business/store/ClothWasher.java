/**
 * ClothWasher class, used to represent a cloth washer and store a repair plan
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 *
 */
package edu.ics372.gp1.business.store;

public class ClothWasher extends Appliance {
	private static final long serialVersionUID = 1L;
	private RepairPlan repairPlan;

	/**
	 * Constructor to create a washer with associated repair plan
	 * 
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 * @param repairPlanCost
	 */
	public ClothWasher(String brand, String model, double cost, String applianceID, double repairPlanCost) {
		super(brand, model, cost, applianceID);
		repairPlan = new RepairPlan(repairPlanCost, applianceID);
	}

	public RepairPlan getRepairPlan() {
		return this.repairPlan;
	}

	@Override
	public String toString() {
		return this.brand + "\t" + this.model + "\t" + this.cost + "\t" + this.stock + "\t" + this.applianceID;
	}
}
