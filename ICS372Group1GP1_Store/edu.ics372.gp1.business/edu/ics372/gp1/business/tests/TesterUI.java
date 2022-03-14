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
			System.out.println(result.getApplianceModel() + "'s id is " + result.getCustomerID());
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

	public void process() {
		store.addCustomer("Joe", "123 fake st", "5555555555");
		store.addCustomer("Moe", "123 fake st", "5555555555");
		store.addCustomer("Zoe", "123 fake st", "5555555555");

		System.out.println(store.getCustomers());
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
