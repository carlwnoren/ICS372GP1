/**
 * ClothDryer class
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 *
 */
package edu.ics372.gp1.business.store;

public class ClothDryer extends Appliance {
	private static final long serialVersionUID = 1L;
	private RepairPlan repairPlan;

	/**
	 * Constructor used to create a cloth dryer with associated repair plan
	 * 
	 * @param brand
	 * @param model
	 * @param cost
	 * @param applianceID
	 * @param repairPlanCost
	 */
	public ClothDryer(String brand, String model, double cost, String applianceID, double repairPlanCost) {
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