package edu.ics372.gp1.business.tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import edu.ics372.gp1.business.facade.Request;
import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.facade.Store;
import edu.ics372.gp1.business.store.Appliance;

public class TesterUI {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static TesterUI testerUI;
	private static Store store = Store.getInstance();
	private int applianceType;
	private static final int EXIT = 0;
	private static final int ADD_SINGLE_MODEL = 1;
	private static final int ADD_SINGLE_CUSTOMER = 2;
	private static final int ADD_STOCK = 3;
	private static final int PURCHASE_MODELS = 4;
	private static final int FULFILL_BACKORDER = 5;
	private static final int ENROLL_CUSTOMER_IN_REPAIR_PLAN = 6;
	private static final int WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN = 7;
	private static final int CHARGE_ALL_REPAIR_PLANS = 8;
	private static final int PRINT_REVENUE = 9;
	private static final int LIST_APPLIANCES = 10;
	private static final int LIST_REPAIR_PLAN_SUBSCRIBERS = 11;
	private static final int LIST_CUSTOMERS = 12;
	private static final int LIST_BACKORDERS = 13;
	private static final int SAVE_DATA = 14;
	private static final int HELP = 15;

	/**
	 * Private singleton constructor. Asks the user if they wish to attempt to
	 * retrieve saved data. If yes, checks for saved data and loads if possible. If
	 * no, checks for an existing UI instance.
	 */
	private TesterUI() {
		if (yesOrNo("Look for saved data and load it?")) {
			retrieve();
		} else {
			testerUI = TesterUI.instance();
		}

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

	private void retrieve() {
		try {
			if (store == null) {
				store = Store.retrieve();
				if (store != null) {
					System.out.println(" The store has been successfully retrieved from the file data.ser\n");
				} else {
					System.out.println("File doesnt exist; creating new store");
					store = Store.getInstance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
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
			addDishwasher();
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

	public void addDishwasher() {
		Result result = store.addDishwasher(Request.instance());
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
		Request.instance().setCustomerID(getToken("Enter the customer ID."));
		do {
			Request.instance().setApplianceID(getToken("Enter the appliance ID."));
			Request.instance().setOrderQuantity(getInt("Enter the quantity to order."));
			Result result = store.purchaseOneOrMoreModels(Request.instance());
			switch (result.getResultCode()) {
			case Result.NO_SUCH_CUSTOMER:
				System.out.println("Invalid customer ID.");
				return;
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
		} else if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Customer already enrolled in the repair plan for this appliance.");
		}
		else if (result.getResultCode() == Result.OPERATION_COMPLETED) {
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
		} else if (result.getResultCode() == Result.OPERATION_FAILED) {
			System.out.println("Customer was not enrolled in this repair plan.");
		}
		else if (result.getResultCode() == Result.OPERATION_COMPLETED) {
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
				double number = Double.valueOf(item);
				return number;
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

	public void getCustomers() {
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
	public void getUsersInRepairPlans() {
		Iterator<Result> iterator = store.getRepairPlans();
		System.out.println("Users enrolled in repair plans ");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			List<Appliance> Appliances = result.getCustomerAppliances();

			System.out.println(result.getCustomerName() + " " + result.getCustomerAddress() + " "
					+ result.getCustomerPhoneNumber() + result.getCustomerID() + result.getCustomerAccountBalance());
			for (Appliance appliance : Appliances) {

				if (appliance.getApplianceID().equals(result.getApplianceID())) {
					System.out.println("Plan for Brand:" + appliance.getBrand() + " Model:" + appliance.getModel());
				}
			}
		}
		System.out.println("End of listing");
	}

	public void getBackorders() {
		Iterator<Result> iterator = store.getBackorders();
		System.out.println("All Backorders:");
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println("ID: " + result.getBackorderID() + " " + "Appliance: " + result.getApplianceBrand() + " "
					+ result.getApplianceModel() + " " + result.getApplianceID() + " Quantity: "
					+ result.getBackorderQuantity() + "Customer: " + result.getCustomerName() + " "
					+ result.getCustomerID());
		}
		System.out.println("End of listing");
	}

	public void getInventory() {
		Iterator<Result> iterator = null;
		System.out.println("1 = furnace");
		System.out.println("2 = Refrigerator");
		System.out.println("3 = Kitchen Range");
		System.out.println("4 = Cloth Dryer");
		System.out.println("5 = Cloth Washer");
		System.out.println("6 = Dishwasher");
		System.out.println("7 = All appliances");
		applianceType = getInt("Enter Appliance Type");

		switch (applianceType) {
		case 1:
			iterator = store.getFurnaces();
			break;
		case 2:
			iterator = store.getRefrigerators();
			break;
		case 3:
			iterator = store.getKitchenRanges();
			break;
		case 4:
			iterator = store.getClothDryers();
			break;
		case 5:
			iterator = store.getClothWashers();
			break;
		case 6:
			iterator = store.getDishwashers();
			break;
		case 7:
			iterator = store.getAllAppliances();
			break;
		}
		while (iterator.hasNext()) {
			Result result = iterator.next();
			System.out.println(result.getApplianceID());
		}
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 12 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_SINGLE_MODEL + " to add a single appliance model");
		System.out.println(ADD_SINGLE_CUSTOMER + " to add a single customer");
		System.out.println(ADD_STOCK + " to add stock for a single model");
		System.out.println(PURCHASE_MODELS + " to order one or more models for a customer");
		System.out.println(FULFILL_BACKORDER + " to fulfill a single backorder");
		System.out.println(ENROLL_CUSTOMER_IN_REPAIR_PLAN + " to enroll a customer in a repair plan");
		System.out.println(WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN + " to withdraw a customer from a repair plan");
		System.out.println(CHARGE_ALL_REPAIR_PLANS + " to charge all repair plans");
		System.out.println(PRINT_REVENUE + " to print the total sales and repair plan revenue");
		System.out.println(LIST_APPLIANCES + " to list all appliance models");
		System.out.println(LIST_REPAIR_PLAN_SUBSCRIBERS + " to print all customers enrolled in a repair plan");
		System.out.println(LIST_CUSTOMERS + " to print all customers");
		System.out.println(LIST_BACKORDERS + " to print all backorders");
		System.out.println(SAVE_DATA + " to  save data");
		System.out.println(HELP + " for help");
	}

	public void addStock() {
		Request.instance().setApplianceID(getName("Enter Appliance ID"));
		Request.instance().setApplianceStock(getInt("Enter Stock Amount"));
		Result result = store.addStock(Request.instance());
		if (result.getResultCode() != Result.OPERATION_COMPLETED) {
			System.out.println("Could not add member");
		} else {
			System.out.println(result.getApplianceID() + " stock is " + result.getApplianceStock());
		}
	}

	public void process() {
		/*
		 * store.addCustomer("Joe", "123 fake st", "5555555555");
		 * store.addCustomer("Moe", "123 fake st", "5555555555");
		 * store.addCustomer("Zoe", "123 fake st", "5555555555");
		 * 
		 * System.out.println(store.getCustomers());
		 * 
		 * getCustomer(); addAppliance(); addAppliance(); addStock(); getInventory();
		 * purchaseOneOrMoreModels();
		 * 
		 * Iterator<Result> itr = store.getCustomers();
		 */
		int command;

		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_SINGLE_MODEL:
				addAppliance();
				break;
			case ADD_SINGLE_CUSTOMER:
				addNewCustomer();
				break;
			case ADD_STOCK:
				addStock();
				break;
			case PURCHASE_MODELS:
				purchaseOneOrMoreModels();
				break;
			case FULFILL_BACKORDER:
				fulfillSingleBackorder();
				break;
			case ENROLL_CUSTOMER_IN_REPAIR_PLAN:
				enrollInRepairPlan();
				break;
			case WITHDRAW_CUSTOMER_FROM_REPAIR_PLAN:
				withdrawFromRepairPlan();
				break;
			case CHARGE_ALL_REPAIR_PLANS:
				chargeAllRepairPlans();
				break;
			case PRINT_REVENUE:
				printRevenue();
				break;
			case LIST_APPLIANCES:
				getInventory();
				break;
			case LIST_REPAIR_PLAN_SUBSCRIBERS:
				getRepairPlans();
				break;
			case LIST_CUSTOMERS:
				getCustomers();
				break;
			case LIST_BACKORDERS:
				getBackorders();
				break;
			case SAVE_DATA:
				saveData();
				break;
			case HELP:
				help();
				break;
			}
		}
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
