package edu.ics372.gp1.business.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.facade.Store;

public class TesterUI {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static TesterUI testerUI;
	private static Store store = Store.getInstance();
	private int applianceType;

	private TesterUI() {

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static TesterUI instance() {
		if (testerUI == null) {
			return testerUI = new TesterUI();
		} else {
			return testerUI;
		}
	}

//	private String applianceBrand;
//	private String applianceModel;
//	private String applianceID;
//	private double applianceCost;
//	private int applianceStock;
	public void addAppliance() {
		Request.instance().setApplianceBrand(getName("Enter Appliance Brand"));
		Request.instance().setApplianceModel(getName("Enter Appliance Model"));
		Request.instance().setApplianceCost(getDouble("Enter Cost"));
		System.out.println("1 = furnace");
		System.out.println("2 = Refrigerator");
		System.out.println("3 = Kitchen Range");
		System.out.println("4 = Cloth Dryer");
		System.out.println("5 = Cloth Washer");
		System.out.println("6 = Dishwasher");
		applianceType = getInt("Enter Appliance Type");

		switch (applianceType) {
		case 1:
			addFurnace();
			break;
		case 2:
			addRefrigerator();
			break;
		case 3:
			addKitchenRange();
			break;
		case 4:
			addClothDryer();
			break;
		case 5:
			addClothWasher();
			break;
		case 6:
			addDishWasher();
			break;
		}

	}

	public void addFurnace() {
		Request.instance().setMaxHeatOutput(Integer.parseInt(getName("Enter Max Heat Output")));
		Result result = store.addFurnace(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getApplianceModel() + "'s id is " + result.getApplianceID());
		}
	}

	public void addRefrigerator() {
		Request.instance().setCapacity(Integer.parseInt(getName("Enter Capacity")));
		Result result = store.addRefrigerator(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(
					result.getApplianceModel() + "'s id is " + result.getApplianceID() + " " + result.getCapacity());
		}
	}

	public void addKitchenRange() {

		Result result = store.addKitchenRange(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getApplianceModel() + "'s id is " + result.getApplianceID());
		}
	}

	public void addClothDryer() {
		Request.instance().setRepairPlanCost(getDouble("Enter repair plan cost"));
		Result result = store.addClothDryer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getApplianceModel() + "'s id is " + result.getApplianceID() + " "
					+ result.getRepairPlanCost());
		}
	}

	public void addClothWasher() {
		Request.instance().setRepairPlanCost(getDouble("Enter repair plan cost"));
		Result result = store.addClothWasher(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(
					result.getApplianceModel() + "'s id is " + result.getApplianceID() + result.getRepairPlanCost());
		}
	}

	public void addDishWasher() {
		Result result = store.addDishWasher(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getApplianceModel() + "'s id is " + result.getApplianceID());
		}
	}

	public void addNewCustomer() {
		Request.instance().setCustomerName(getName("Enter member name"));
		Request.instance().setCustomerAddress(getName("Enter address"));
		Request.instance().setCustomerPhoneNumber(getName("Enter phone"));
		Result result = store.addCustomer(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getCustomerName() + "'s id is " + result.getCustomerID());
		}
	}

	/**
	 * Purchases a model and the quantity of that model based on input from the
	 * user. The user is then asked if they wish to order another model.
	 */
	public void purchaseOneOrMoreModels() {
		do {
			Request.instance().setApplianceID(getToken("Enter the appliance ID."));
			Request.instance().setOrderQuantity(getInt("Enter the quantity to order."));
			Result result = store.purchaseOneOrMoreModels(Request.instance());
			switch (result.getResultCode()) {
			case Result.APPLIANCE_NOT_FOUND:
				System.out.println("Invalid appliance ID.");
				break;
			case Result.INSUFFICIENT_STOCK:
				System.out.println(
						"Insufficient furnaces stocked. Only " + result.getFurnacesOrdered() + " units were ordered.");
				break;
			case Result.BACKORDER_PLACED:
				System.out.println("Order partially fulfilled. "
						+ (Request.instance().getOrderQuantity() - result.getBackorderQuantity()) + " units ordered. "
						+ result.getBackorderQuantity() + " units were placed as backorder " + result.getBackorderID()
						+ ".");
				break;
			case Result.OPERATION_COMPLETED:
				System.out
						.println("Order successfully placed for " + Request.instance().getOrderQuantity() + " units.");
			}
		} while (yesOrNo("Would you like to order another model?"));
	}

	/**
	 * Charges all repair plans.
	 */
	public void chargeAllRepairPlans() {
		store.chargeAllRepairPlans();
		System.out.println("All repair plans have been charged.");
	}

	/**
	 * Enrolls a customer, by given customer ID, in a repair plan, by given
	 * appliance ID.
	 */
	public void enrollInRepairPlan() {
		Request.instance().setCustomerID(getToken("Enter the customer ID to be enrolled. "));
		Request.instance().setApplianceID(getToken("Enter the appliance ID for the repair plan. "));
		Result result = store.enrollInRepairPlan(Request.instance());
		if (result.getResultCode() == Result.REPAIR_PLAN_NOT_FOUND) {
			System.out.println("No repair plan exists for the given appliance ID.");
		} else if (result.getResultCode() == Result.NO_SUCH_CUSTOMER) {
			System.out.println("Invalid customer ID.");
		} else if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Customer " + Request.instance().getCustomerID()
					+ " enrolled in repair plan for appliance " + Request.instance().getApplianceID() + ".");
		}
	}

	/**
	 * Withdraws the customer given by the user, by customer ID, from the repair
	 * plan given by the user, by appliance ID.
	 */
	public void withdrawFromRepairPlan() {
		Request.instance().setCustomerID(getToken("Enter the customer ID to be enrolled. "));
		Request.instance().setApplianceID(getToken("Enter the appliance ID for the repair plan. "));
		Result result = store.withdrawFromRepairPlan(Request.instance());
		if (result.getResultCode() == Result.REPAIR_PLAN_NOT_FOUND) {
			System.out.println("No repair plan exists for the given appliance ID.");
		} else if (result.getResultCode() == Result.NO_SUCH_CUSTOMER) {
			System.out.println("Invalid customer ID.");
		} else if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Customer " + Request.instance().getCustomerID()
					+ " withdrew from repair plan for appliance " + Request.instance().getApplianceID() + ".");
		}
	}

	/**
	 * Fulfills a single backorder given by the user. The stock for that appliance
	 * is reduced by the amount on backorder.
	 */
	public void fulfillSingleBackorder() {
		Request.instance().setBackorderID(getToken("Enter the backorder ID."));
		Result result = store.fulfillBackorder(Request.instance());
		if (result.getResultCode() == Result.BACKORDER_NOT_FOUND) {
			System.out.println("Invalid backorder ID.");
		} else if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Backorder " + Request.instance().getBackorderID() + " fulfilled.");
		}
	}

	/**
	 * Prints the sales and repair plan revenue from the store.
	 */

	public void printRevenue() {
		Result result = store.printRevenue();
		System.out.println("Revenue from sales: $" + result.getSalesRevenue());
		System.out.println("Revenue from repair plans: $" + result.getRepairPlanRevenue());
	}

	/**
	 * Saves the data for the store.
	 */
	public void saveData() {
		Result result = store.saveData();
		if (result.getResultCode() == Result.OPERATION_COMPLETED) {
			System.out.println("Store data successfully saved.");
		} else {
			System.out.println("Store data was not saved.");
		}
	}

	public String getName(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				return line;
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);

	}

	public double getDouble(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	public int getInt(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	public void getCustomer() {
		Iterator<Result> iterator = store.getCustomers();
		System.out.println("List of members (name, address, phone, id)");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getCustomerName() + " " + result.getCustomerAddress() + " "
					+ result.getCustomerPhoneNumber() + " " + result.getCustomerID());
		}
		System.out.println("End of listing");
	}

	/**
	 * Method to print all repair plans with all customer info.
	 */
	public void getRepairPlans() {
		Iterator<Result> iterator = store.getRepairPlans();
		System.out.println("RepairPlans");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getRepairPlanApplianceID() + " " + result.getRepairPlanCost()
					+ result.getRepairPlanSubscribers());
		}
		System.out.println("End of llisting");
	}

	public void getInventory() {
		applianceType = getInt("Enter Appliance Type");

		switch (applianceType) {
		case 1:
			store.getFurnaces();
			break;
		case 2:
			addRefrigerator();
			break;
		case 3:
			addKitchenRange();
			break;
		case 4:
			addClothDryer();
			break;
		case 5:
			addClothWasher();
			break;
		case 6:
			addDishwasher();
			break;
		case 7:

			break;
		}

	}

	public void process() {
		store.addCustomer("Joe", "123 fake st", "5555555555");
		store.addCustomer("Moe", "123 fake st", "5555555555");
		store.addCustomer("Zoe", "123 fake st", "5555555555");

		System.out.println(store.getCustomers());

		getCustomer();
		addAppliance();
		addAppliance();
		getRepairPlans();

		Iterator<Result> itr = store.getCustomers();

		getCustomer();
		addAppliance();

	}

	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		TesterUI.instance().process();
	}
}
