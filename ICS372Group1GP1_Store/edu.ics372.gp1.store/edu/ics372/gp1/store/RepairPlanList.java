/**
 * This class implements a RepairPlanList, which stores a list of all avaiable RepairPlan objects.
 * This class is a singleton.
 * It has the repairPlanList and repairPlans fields.
 * It has the getInstance and search methods.
 */
package edu.ics372.gp1.store;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RepairPlanList implements ItemList<RepairPlan, String>, Serializable {
	/**
	 * This field stores the singleton RepairPlanList object.
	 */
	private static RepairPlanList repairPlanList;
	/**
	 * This field stores all Customer objects stored in this RepairPlanList.
	 */
	private List<RepairPlan> repairPlans = new LinkedList<RepairPlan>();

	private RepairPlanList() {

	}

	/**
	 * This method creates a RepairPlanList if none yet exists.
	 * 
	 * @return either the newly created or existing repairPlanList
	 */
	public RepairPlanList getInstance() {
		if (repairPlanList == null) {
			repairPlanList = new RepairPlanList();
		}
		return repairPlanList;
	}

	/**
	 * This method searches the customers field for an RepairPlan object with a
	 * matching applianceID
	 * 
	 * @return the matching RepairPlan or null, if no match is found.
	 */
	@Override
	public RepairPlan search(String applianceID) {
		for (RepairPlan repairPlan : repairPlans) {
			if (repairPlan.matches(applianceID)) {
				return repairPlan;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "RepairPlanList [repairPlans=" + repairPlans + "]";
	}

}
