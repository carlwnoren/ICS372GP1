/**
 * This class implements an Inventory, which stores a list of all avaiable Appliance models.
 * This class is a singleton.
 * It has the inventory and appliances fields.
 * It has the getInstance and search methods.
 */
package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.ClothDryer;
import edu.ics372.gp1.business.store.ClothWasher;
import edu.ics372.gp1.business.store.Dishwasher;
import edu.ics372.gp1.business.store.Furnace;
import edu.ics372.gp1.business.store.KitchenRange;
import edu.ics372.gp1.business.store.Refrigerator;

public class Inventory implements ItemList<Appliance, String>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This field stores the singleton Inventory object.
	 */
	private static Inventory inventory;
	/**
	 * This field stores all Appliance objects stored in this Inventory.
	 */
	private List<Appliance> appliances = new LinkedList<Appliance>();

	private static final String APPLIANCE_STRING = "A";
	private int idCounter = 1000;

	private Inventory() {

	}

	/**
	 * This method creates an Inventory if none yet exists.
	 * 
	 * @return either the newly created or existing inventory
	 */
	public static Inventory getInstance() {
		if (inventory == null) {
			inventory = new Inventory();
		}
		return inventory;
	}

	/**
	 * This method searches the appliances field for an Appliance object with a
	 * matching applianceID
	 * 
	 * @return the matching appliance or null, if no match is found.
	 */
	@Override
	public Appliance search(String applianceID) {
		for (Appliance model : appliances) {
			if (model.matches(applianceID)) {
				return model;
			}
		}
		return null;
	}

	public Iterator<Appliance> iterator() {
		return appliances.iterator();
	}

	/**
	 * Specific function to add a furnace to inventory
	 * 
	 * @param request
	 * @return
	 */
	public Result addFurnace(Request request) {
		Result result = new Result();
		Furnace furnace = new Furnace(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++, request.getMaxHeatOutput());
		if (appliances.add(furnace)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setFurnaceFields(furnace);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Function to add KitchenRange to inventory
	 * 
	 * @param request
	 * @return
	 */
	public Result addKitchenRange(Request request) {
		Result result = new Result();
		KitchenRange kitchenRange = new KitchenRange(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++);
		if (appliances.add(kitchenRange)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setApplianceFields(kitchenRange);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Function to add ClothDryer to inventory, accounts for repair Plan
	 * 
	 * @param request
	 * @return
	 */
	public Result addClothDryer(Request request) {
		Result result = new Result();
		ClothDryer clothDryer = new ClothDryer(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++, request.getRepairPlanCost());

		if (appliances.add(clothDryer)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setClothDryerFields(clothDryer);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Function to add ClothWasher to inventory, accounts for repair Plan
	 * 
	 * @param request
	 * @return
	 */
	public Result addClothWasher(Request request) {
		Result result = new Result();
		ClothWasher clothWasher = new ClothWasher(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++, request.getRepairPlanCost());
		if (appliances.add(clothWasher)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setClothWasherFields(clothWasher);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Function to add dishwaser to inventory
	 * 
	 * @param request
	 * @return
	 */
	public Result addDishwasher(Request request) {
		Result result = new Result();
		Dishwasher dishWasher = new Dishwasher(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++);
		if (appliances.add(dishWasher)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setApplianceFields(dishWasher);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Function to add refrigerator to inventory, accounts for capacity
	 * 
	 * @param request
	 * @return
	 */
	public Result addRefrigerator(Request request) {
		Result result = new Result();
		Refrigerator refrigerator = new Refrigerator(request.getApplianceBrand(), request.getApplianceModel(),
				request.getApplianceCost(), APPLIANCE_STRING + idCounter++, request.getCapacity());
		if (appliances.add(refrigerator)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setRefrigeratorFields(refrigerator);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	/**
	 * Used to add stock to a specific appliance.
	 * 
	 * @param request
	 * @return
	 */
	public Result addStock(Request request) {
		Result result = new Result();
		for (Appliance model : appliances) {
			if (model.matches(request.getApplianceID())) {
				model.addStock(request.getApplianceStock());
				result.setResultCode(Result.OPERATION_COMPLETED);
				result.setApplianceFields(model);
				return result;
			}
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	public List<Appliance> getAppliancesList() {
		return appliances;
	}
}