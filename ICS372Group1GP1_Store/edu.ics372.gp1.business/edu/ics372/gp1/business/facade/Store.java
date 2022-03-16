
package edu.ics372.gp1.business.facade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import edu.ics372.gp1.business.store.Backorder;
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
	public Result enrollInRepairPlan(Request request) {
		Result result = new Result();
		Customer customer = customerList.search(request.getCustomerID());
		RepairPlan repairPlan = repairPlanList.search(request.getApplianceID());
		if (customer.equals(null)) {
			result.setResultCode(Result.NO_SUCH_CUSTOMER);
		} else if (repairPlan == null) {
			result.setResultCode(Result.REPAIR_PLAN_NOT_FOUND);
		} else {
			repairPlan.enrollCustomer(customer);
			result.setResultCode(Result.OPERATION_COMPLETED);
		}
		return result;
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
		Result tempResult = inventory.addClothDryer(request);
		RepairPlan tempPlan = new RepairPlan(tempResult.getRepairPlanCost(), tempResult.getApplianceID());
		this.repairPlanList.addRepairPlan(tempPlan);
		tempResult.setRepairPlanFields(tempPlan);
		return tempResult;
	}

	public Result addClothWasher(Request request) {
		Result tempResult = inventory.addClothWasher(request);
		RepairPlan tempPlan = new RepairPlan(tempResult.getRepairPlanCost(), tempResult.getApplianceID());
		this.repairPlanList.addRepairPlan(tempPlan);
		tempResult.setRepairPlanFields(tempPlan);
		return tempResult;
	}

	public Result addDishwasher(Request request) {
		// TODO Auto-generated method stub
		return inventory.addDishwasher(request);
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
	public Result withdrawFromRepairPlan(Request request) {
		Result result = new Result();
		Customer customer = customerList.search(request.getCustomerID());
		RepairPlan repairPlan = repairPlanList.search(request.getApplianceID());
		if (customer == null) {
			result.setResultCode(Result.NO_SUCH_CUSTOMER);
		} else if (repairPlan == null) {
			result.setResultCode(Result.REPAIR_PLAN_NOT_FOUND);
		} else {
			repairPlan.enrollCustomer(customer);
			result.setResultCode(Result.OPERATION_COMPLETED);
		}
		return result;
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

	public Result printRevenue() {
		Result result = new Result();
		result.setSalesRevenue(salesRevenue);
		result.setRepairPlanRevenue(repairPlanRevenue);
		return result;
	}

	/**
	 * @return
	 *
	 */
	public Result purchaseOneOrMoreModels(Request request) {
		Result result = new Result();
		Appliance appliance = inventory.search(request.getApplianceID());
		Customer customer = customerList.search(request.getCustomerID());
		int quantity = request.getPurchaseQuantity();
		int stock = appliance.getStock();
		double cost = appliance.getCost();
		if (appliance == null) {
			result.setResultCode(Result.APPLIANCE_NOT_FOUND);
		}
		else if (customer == null) {
			result.setResultCode(Result.NO_SUCH_CUSTOMER);
		}
		else {
			if (stock >= quantity) {
				appliance.removeStock(quantity);
				addSalesRevenue(quantity * cost);
				result.setResultCode(Result.OPERATION_COMPLETED);
			} else {
				if (appliance instanceof Furnace) {
					addSalesRevenue(stock * cost);
					result.setFurnacesOrdered(stock);
					appliance.removeStock(stock);
					result.setResultCode(Result.INSUFFICIENT_STOCK);
				} else {
					addSalesRevenue(quantity * cost);
					appliance.removeStock(stock);
					request.setBackorderQuantity(quantity - stock);
					request.setBackorderID(backorderList.addBackorder(purchase, quantity - stock));
					result.setResultCode(Result.BACKORDER_PLACED);
				}
			}
		}

		return result;
	}

	/**
	 * Fulfills a single backorder, depleting the given appliance's stock by the
	 * amount on backorder.
	 * 
	 * @return the result of the operation
	 */
	public Result fulfillBackorder(Request request) {
		Result result = new Result();
		Backorder backorder = backorderList.search(request.getBackorderID());
		if (backorder == null) {
			result.setResultCode(Result.BACKORDER_NOT_FOUND);
		} else {
			backorder.getAppliance().removeStock(backorder.getQuantity());
			backorderList.removeBackorder(backorder);
			result.setResultCode(Result.OPERATION_COMPLETED);
		}
		return result;
	}

	/**
	 * Serializes the store's data.
	 * 
	 * @return the result of the operation
	 */
	public Result saveData() {
		Result result = new Result();
		try {
			FileOutputStream fileOut = new FileOutputStream("store.ser");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(store);
			objectOut.flush();
			objectOut.close();
			result.setResultCode(Result.OPERATION_COMPLETED);
		} catch (IOException ioe) {
			result.setResultCode(Result.OPERATION_FAILED);
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

	public Iterator<Result> getFurnaces() {
		Predicate<Appliance> p1 = ((Appliance a) -> a instanceof Furnace);
		return new SafeApplianceIterator(new FilteredIterator(inventory.iterator(), p1));
	}

	public Result addStock(Request request) {

		return inventory.addStock(request);
	}

//	public Iterator<Result> getTransactions(Request request) {
//		Member member = members.search(request.getMemberId());
//		if (member == null) {
//			return new LinkedList<Result>().iterator();
//		}
//		return member.getTransactionsOnDate(request.getDate());
//	}

}
