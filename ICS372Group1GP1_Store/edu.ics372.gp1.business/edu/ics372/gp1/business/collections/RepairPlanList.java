/**
 * This class implements a RepairPlanList, which stores a list of all avaiable RepairPlan objects.
 * This class is a singleton.
 * It has the repairPlanList and repairPlans fields.
 * It has the getInstance and search methods.
 */
package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.store.RepairPlan;

public class RepairPlanList implements ItemList<RepairPlan, String>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This field stores the singleton RepairPlanList object.
	 */
	private static RepairPlanList repairPlanList;
	/**
	 * This field stores all Customer objects stored in this RepairPlanList.
	 */
	private List<RepairPlan> repairPlans = new LinkedList<RepairPlan>();

	/**
	 * Private constructor to support the singleton class.
	 */
	private RepairPlanList() {

	}

	/**
	 * This method creates a RepairPlanList if none yet exists.
	 * 
	 * @return either the newly created or existing repairPlanList
	 */
	public static RepairPlanList getInstance() {
		if (repairPlanList == null) {
			repairPlanList = new RepairPlanList();
		}
		return repairPlanList;
	}

	/**
	 * Returns an iterator for the list of repair plans.
	 * 
	 * @return
	 */
	public Iterator<RepairPlan> getRepairPlans() {
		return repairPlans.iterator();
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

	/**
	 * Used to add a repair plan to this list of plans
	 * 
	 * @param repairPlan
	 * @return
	 */
	public boolean addRepairPlan(RepairPlan repairPlan) {
		this.repairPlans.add(repairPlan);
		System.out.println("Success");
		return true;
	}

	/**
	 * List all repair plans
	 * 
	 * @return String, all repair plans
	 */
	public String listAll() {
		return repairPlans.toString();
	}

	/**
	 * returns iterator of repairPlans list
	 * 
	 * @return
	 */
	public Iterator<RepairPlan> iterator() {
		return repairPlans.iterator();
	}

	@Override
	public String toString() {
		return "RepairPlanList [repairPlans=" + repairPlans + "]";
	}

}
