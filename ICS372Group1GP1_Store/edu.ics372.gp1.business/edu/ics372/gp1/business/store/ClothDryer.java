/**
 * ClothDryer class
 * 
 * @author Kean
 *
 */
package edu.ics372.gp1.business.store;

public class ClothDryer extends Appliance {
	private static final long serialVersionUID = 1L;
	private RepairPlan repairPlan;

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