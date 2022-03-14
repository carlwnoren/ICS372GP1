
package edu.ics372.gp1.business.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Predicate;

import edu.ics372.gp1.Iterators.FilteredIterator;
import edu.ics372.gp1.Iterators.SafeApplianceIterator;
import edu.ics372.gp1.Iterators.SafeCustomerIterator;
import edu.ics372.gp1.Iterators.SafeRepairPlanIterator;
import edu.ics372.gp1.business.collections.BackorderList;
import edu.ics372.gp1.business.collections.CustomerList;
import edu.ics372.gp1.business.collections.Inventory;
import edu.ics372.gp1.business.collections.RepairPlanList;
import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.Customer;
import edu.ics372.gp1.business.store.Furnace;
import edu.ics372.gp1.business.store.RepairPlan;

public class Store implements Serializable {
	private static final long serialVersionUID = 1L;
	private Inventory inventory = Inventory.getInstance();
	private BackorderList backorderList = BackorderList.getInstance();
	private CustomerList customerList = CustomerList.getInstance();
	private RepairPlanList repairPlanList = RepairPlanList.getInstance();
	private static Store store;
	private double salesRevenue = 0;
	private double repairPlanRevenue = 0;

	/**
	 * Private constructor for this singleton class.
	 */
	private Store() {
	}

	/**
	 * Creates an instance of the Store class, if the singleton field is null.
	 *
	 * @return the store field, whether it is created
	 */
	public static Store getInstance() {
		if (store == null) {
			return store = new Store();
		} else {
			return store;
		}
	}

	/**
	 * Adds a given customer (by ID) to a repair plan's (by appliance ID) list of
	 * subscribers
	 *
	 * @param customerID
	 * @param applianceID
	 * @return true if customer successfully enrolled in the plan
	 */
	public boolean enrollInRepairPlan(String customerID, String applianceID) {
		Customer customer = customerList.search(customerID);
		RepairPlan repairPlan = repairPlanList.search(applianceID);
		if (customer.equals(null) || repairPlan.equals(null)) {
			return false;
		} else {
			return repairPlan.enrollCustomer(customer);
		}
	}

	/**
	 * Adds customer to list of customers
	 *
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @return customer ID
	 */
	public String addCustomer(String name, String address, String phoneNumber) {
		Customer newCustomer = new Customer(name, address, phoneNumber);
		customerList.add(newCustomer);
		return newCustomer.getId();
	}

	public Result addCustomer(Request request) {
		Result result = new Result();
		Customer customer = new Customer(request.getCustomerName(), request.getCustomerAddress(),
				request.getCustomerPhoneNumber());
		if (customerList.add(customer)) {
			result.setResultCode(Result.OPERATION_COMPLETED);
			result.setCustomerFields(customer);
			return result;
		}
		result.setResultCode(Result.OPERATION_FAILED);
		return result;
	}

	// public boolean addToInventory()

	// public Appliance addSingleModel()

	public Result addFurnace(Request request) {
		return inventory.addFurnace(request);
	}

	public Result addKitchenRange(Request request) {
		return inventory.addKitchenRange(request);
	}

	public Result addRefrigerator(Request request) {
		// TODO Auto-generated method stub
		return inventory.addRefrigerator(request);
	}

	public Result addClothDryer(Request request) {
		// TODO Auto-generated method stub
		return inventory.addClothDryer(request);
	}

	public Result addClothWasher(Request request) {
		// TODO Auto-generated method stub
		return inventory.addClothWasher(request);
	}

	public Result addDishWasher(Request request) {
		// TODO Auto-generated method stub
		return inventory.addDishWasher(request);
	}
	// (String brand, String model, double cost, String applianceID, int
	// maxHeatOutput

	/**
	 * Removes a given customer (by ID) to a repair plan's (by appliance ID) list of
	 * subscribers
	 *
	 * @param customerID
	 * @param applianceID
	 * @return true if customer successfully removed from the plan
	 */
	public boolean withdrawFromRepairPlan(Request request) {
		Customer customer = customerList.search(request.getCustomerID());
		RepairPlan repairPlan = repairPlanList.search(request.getApplianceID());
		if (customer.equals(null) || repairPlan.equals(null)) {
			return false;
		} else {
			repairPlan.enrollCustomer(customer);
			return true;
		}
	}

	/**
	 * Charges each customer for every repair plan, adding the cost to the
	 * customer's account balance and the store's repair plan revenue
	 */
	public void chargeAllRepairPlans() {
		Iterator<RepairPlan> repairPlans = repairPlanList.getRepairPlans();
		while (repairPlans.hasNext()) {
			repairPlans.next().chargePlan();
		}
	}

	public double printRevenue() {
		return salesRevenue + repairPlanRevenue;
	}

	/**
	 * @return
	 *
	 */
	public Result purchaseOneOrMoreModels(Request request) {
		Result result = new Result();
		Appliance purchase = inventory.search(request.getApplianceID());
		int quantity = request.getPurchaseQuantity();
		int stock = purchase.getStock();
		double cost = purchase.getCost();
		if (purchase == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
		} else {
			if (stock >= quantity) {
				purchase.removeStock(quantity);
				addSalesRevenue(quantity * cost);
				result.setResultCode(Result.OPERATION_COMPLETED);
			} else {
				if (purchase instanceof Furnace) {
					addSalesRevenue(stock * cost);
					result.setFurnacesOrdered(stock);
					purchase.removeStock(stock);
					result.setResultCode(Result.INSUFFICIENT_STOCK);
				} else {
					addSalesRevenue(quantity * cost);
					purchase.removeStock(stock);
					request.setBackorderQuantity(quantity - stock);
					request.setBackorderID(backorderList.addBackorder(purchase, quantity - stock));
					result.setResultCode(Result.BACKORDER_PLACED);
				}
			}
		}

		return result;
	}

	/**
	 * Adds a given amount, the cost for a repair plan, to the store's repair plan
	 * revenue.
	 *
	 * @param cost
	 */
	public void addRepairPlanRevenue(double cost) {
		repairPlanRevenue += cost;
	}

	public void addSalesRevenue(double cost) {
		salesRevenue += cost;
	}

	// needs testing
	public void listAllCustomers() {
		customerList.listAll();
	}

	public void listAllCustomersRepairPlans() {
		repairPlanList.listAll();
	}

	public Iterator<Result> getCustomers() {
		return new SafeCustomerIterator(customerList.iterator());
	}

	public Iterator<Result> getRepairPlans() {
		return new SafeRepairPlanIterator(repairPlanList.iterator());

	}

	public Iterator<Result> getAppliances(Predicate<Appliance> predicate) {
		Predicate<Appliance> p1 = ((Appliance a) -> a instanceof Furnace);
		return new SafeApplianceIterator(new FilteredIterator(inventory.iterator(), predicate));
	}

}
