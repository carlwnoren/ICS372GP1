/**
 * Automated tester class, used to test creation of objects and other functions
 * 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 * 
 */
package edu.ics372.gp1.business.tests;

import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.facade.Store;
import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.Customer;

public class AutomatedTester {

	private String[] names = { "n1", "n2", "n3" };
	private String[] addresses = { "a1", "a2", "a3" };
	private String[] phones = { "1111111111", "2222222222", "3333333333" };
	private Customer[] customers = new Customer[3];
	private Appliance[] appliances = new Appliance[3];
	private String[] applianceModels = { "m1", "m2", "m3" };
	private String[] applianceBrands = { "b1", "b2", "b3" };
	private double[] applianceCosts = { 1, 2, 3 };

	/**
	 * Tests Member creation.
	 *
	 */
	public void testAddCustomer() {
		for (int count = 0; count < customers.length; count++) {
			Request.instance().setCustomerAddress(addresses[count]);
			Request.instance().setCustomerName(names[count]);
			Request.instance().setCustomerPhoneNumber(phones[count]);
			Result result = Store.getInstance().addCustomer(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getCustomerName().equals(names[count]);
			assert result.getCustomerPhoneNumber().equals(phones[count]);
		}
	}

	/**
	 * Test creation of Furnace
	 */
	public void testAddFurnace() {
		for (int count = 0; count < appliances.length - 1; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Request.instance().setMaxHeatOutput(count);
			Result result = Store.getInstance().addFurnace(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
			assert result.getMaxHeatOutput() == count;

		}
	}

	/**
	 * Test creation of Refrigerator
	 */
	public void testAddRefrigerator() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Request.instance().setCapacity(count);
			Result result = Store.getInstance().addRefrigerator(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
			assert result.getCapacity() == count;
		}
	}

	/**
	 * Test creation of Cloth Dryer, which also tests creation of repair plans
	 */
	public void testAddClothDryer() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Request.instance().setRepairPlanCost(count);
			Result result = Store.getInstance().addClothDryer(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
			assert result.getRepairPlanCost() == count;
		}
	}

	/**
	 * Test creation of Cloth washer, which also tests creation of repair plans
	 */
	public void testAddClothWasher() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Request.instance().setRepairPlanCost(count);
			Result result = Store.getInstance().addClothWasher(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
			assert result.getRepairPlanCost() == count;
		}
	}

	/**
	 * Test creation of Dishwasher
	 */
	public void testAddDishwasher() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Result result = Store.getInstance().addDishwasher(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
		}
	}

	/**
	 * Test creation of Dishwasher
	 */
	public void testAddKitchenRange() {
		for (int count = 0; count < appliances.length; count++) {
			Request.instance().setApplianceModel(applianceModels[count]);
			Request.instance().setApplianceBrand(applianceBrands[count]);
			Request.instance().setApplianceCost(applianceCosts[count]);
			Result result = Store.getInstance().addKitchenRange(Request.instance());
			assert result.getResultCode() == Result.OPERATION_COMPLETED;
			assert result.getApplianceModel().equals(applianceModels[count]);
			assert result.getApplianceBrand().equals(applianceBrands[count]);
		}
	}

	/**
	 * Test Addition of stock to a appliance.
	 */
	public void testAddStock() {
		Request.instance().setApplianceID("A1000");
		Request.instance().setApplianceStock(10);
		Result result = Store.getInstance().addStock(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
		assert result.getApplianceID().equals("A1000");
		assert result.getApplianceStock() == 10;
	}

	/**
	 * Test purchase of one or more appliances
	 */
	public void testPurchase() {
		Request.instance().setCustomerID("C1000");
		Request.instance().setApplianceID("A1000");
		Request.instance().setOrderQuantity(2);
		Result result = Store.getInstance().purchaseOneOrMoreModels(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
	}

	/*
	 * Tests to see if backorder process fails
	 */
	public void testBackOrder() {
		Request.instance().setCustomerID("C1000");
		Request.instance().setApplianceID("A1002");
		Request.instance().setOrderQuantity(1);
		Result result = Store.getInstance().purchaseOneOrMoreModels(Request.instance());
		assert result.getResultCode() == Result.OPERATION_COMPLETED;
	}

	/**
	 * Method to call all testing methods.
	 */
	public void testAll() {
		testAddCustomer();
		testAddFurnace();
		testAddRefrigerator();
		testAddClothWasher();
		testAddClothDryer();
		testAddDishwasher();
		testAddKitchenRange();
		testAddStock();
		testPurchase();
		testBackOrder();
		
	}

	/**
	 * Main class used to start tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new AutomatedTester().testAll();
	}

}
