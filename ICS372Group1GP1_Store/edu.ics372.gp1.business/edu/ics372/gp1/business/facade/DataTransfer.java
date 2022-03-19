/**
 * Data transfer classed used to securely transmit data to UI without exposing business logic. 
 */

package edu.ics372.gp1.business.facade;

import java.util.List;

import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.Backorder;
import edu.ics372.gp1.business.store.ClothDryer;
import edu.ics372.gp1.business.store.ClothWasher;
import edu.ics372.gp1.business.store.Customer;
import edu.ics372.gp1.business.store.Furnace;
import edu.ics372.gp1.business.store.Refrigerator;
import edu.ics372.gp1.business.store.RepairPlan;

public abstract class DataTransfer {

	private String customerName;
	private String customerAddress;
	private String customerPhoneNumber;
	private String customerID;
	private double customerAccountBalance;
	private String applianceBrand;
	private String applianceModel;
	private String applianceID;
	private double applianceCost;
	private int applianceStock;
	private double repairPlanCost;
	private String repairPlanApplianceID;
	private List<Customer> repairPlanSubscribers;
	private int backorderQuantity;
	private int purchaseQuantity;
	private int furnacesOrdered;
	private int orderQuantity;
	private double salesRevenue;
	private double repairPlanRevenue;
	private List<RepairPlan> repairPlansEnrolledIn;
	private List<Appliance> customerAppliances;

	private int maxHeatOutput;
	private int capacity;

	private String backorderID;

	/**
	 * This sets all fields to "none".
	 */

	/**
	 * Sets all fields for a customer
	 * 
	 * @param customer
	 */
	public void setCustomerFields(Customer customer) {
		customerName = customer.getName();
		customerAddress = customer.getAddress();
		customerPhoneNumber = customer.getPhoneNumber();
		customerID = customer.getId();
		customerAccountBalance = customer.getAccountBalance();
		repairPlansEnrolledIn = customer.getRepairPlansEnrolledIn();
		customerAppliances = customer.getAppliances();
	}

	/**
	 * Sets all fields for a generic appliance
	 * 
	 * @param clothDryer
	 */
	public void setApplianceFields(Appliance appliance) {
		applianceBrand = appliance.getBrand();
		applianceModel = appliance.getModel();
		applianceID = appliance.getApplianceID();// what is appliance ID going to be?
		applianceCost = appliance.getCost();
		applianceStock = appliance.getStock();
	}

	/**
	 * Sets all fields for a furnace
	 * 
	 * @param clothDryer
	 */
	public void setFurnaceFields(Furnace furnace) {
		applianceBrand = furnace.getBrand();
		applianceModel = furnace.getModel();
		applianceID = furnace.getApplianceID();// what is appliance ID going to be?
		applianceCost = furnace.getCost();
		applianceStock = furnace.getStock();
		maxHeatOutput = furnace.getMaxHeatOutput();
	}

	/**
	 * Sets all fields for a refrigerator
	 * 
	 * @param clothDryer
	 */
	public void setRefrigeratorFields(Refrigerator refrigerator) {
		applianceBrand = refrigerator.getBrand();
		applianceModel = refrigerator.getModel();
		applianceID = refrigerator.getApplianceID();// what is appliance ID going to be?
		applianceCost = refrigerator.getCost();
		applianceStock = refrigerator.getStock();
		capacity = refrigerator.getCapacity();
	}

	/**
	 * Sets all fields for a cloth washer
	 * 
	 * @param clothDryer
	 */
	public void setClothWasherFields(ClothWasher clothWasher) {
		applianceBrand = clothWasher.getBrand();
		applianceModel = clothWasher.getModel();
		applianceID = clothWasher.getApplianceID();
		applianceCost = clothWasher.getCost();
		applianceStock = clothWasher.getStock();
		repairPlanCost = clothWasher.getRepairPlan().getCost();
	}

	/**
	 * Sets all fields for a cloth dryer
	 * 
	 * @param clothDryer
	 */
	public void setClothDryerFields(ClothDryer clothDryer) {
		applianceBrand = clothDryer.getBrand();
		applianceModel = clothDryer.getModel();
		applianceID = clothDryer.getApplianceID();
		applianceCost = clothDryer.getCost();
		applianceStock = clothDryer.getStock();
		repairPlanCost = clothDryer.getRepairPlan().getCost();
	}

	public void setRepairPlanFields(RepairPlan repairPlan) {
		repairPlanCost = repairPlan.getCost();
		repairPlanApplianceID = repairPlan.getApplianceID();
		repairPlanSubscribers = repairPlan.getSubscribers();
	}

	/**
	 * Sets all fields for a backorder
	 * 
	 * @param backorder
	 */
	public void setBackorderFields(Backorder backorder) {
		backorderID = backorder.getBackorderID();
		applianceID = backorder.getAppliance().getApplianceID();
		applianceBrand = backorder.getAppliance().getBrand();
		applianceModel = backorder.getAppliance().getModel();
		backorderQuantity = backorder.getQuantity();
		customerName = backorder.getCustomer().getName();
		customerID = backorder.getCustomer().getId();
	}

	/**
	 * Sets all String fields to "none"
	 */
	public void reset() {
		customerName = "No such customer";
		customerAddress = "No such customer";
		customerPhoneNumber = "No such customer";
		customerID = "Invalid customer id";
		customerAccountBalance = 0;
		applianceBrand = "No such model";
		applianceModel = "No such model";
		applianceCost = 0;
		applianceStock = 0;
		applianceID = "No such model";
		repairPlanCost = 0;
		repairPlanApplianceID = "No such plan";
		repairPlanSubscribers = null;
		backorderQuantity = 0;
		purchaseQuantity = 0;
		furnacesOrdered = 0;
		orderQuantity = 0;
		salesRevenue = 0;
		repairPlanRevenue = 0;
		repairPlansEnrolledIn = null;
		customerAppliances = null;
		maxHeatOutput = 0;
		capacity = 0;
		backorderID = "No such backorder id";

	}

	public DataTransfer() {
		reset();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerId) {
		this.customerID = customerId;
	}

	public double getCustomerAccountBalance() {
		return customerAccountBalance;
	}

	public void setCustomerAccountBalance(double customerAccountBalance) {
		this.customerAccountBalance = customerAccountBalance;
	}

	public int getMaxHeatOutput() {
		return maxHeatOutput;
	}

	public void setMaxHeatOutput(int maxHeatOutput) {
		this.maxHeatOutput = maxHeatOutput;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public List<Customer> getRepairPlanSubscribers() {
		return repairPlanSubscribers;
	}

	public void setRepairPlanSubscribers(List<Customer> repairPlanSubscribers) {
		this.repairPlanSubscribers = repairPlanSubscribers;
	}

	public double getSalesRevenue() {
		return salesRevenue;
	}

	public void setSalesRevenue(double salesRevenue) {
		this.salesRevenue = salesRevenue;
	}

	public double getRepairPlanRevenue() {
		return repairPlanRevenue;
	}

	public void setRepairPlanRevenue(double repairPlanRevenue) {
		this.repairPlanRevenue = repairPlanRevenue;

	}

	public List<RepairPlan> getRepairPlansEnrolledIn() {
		return repairPlansEnrolledIn;
	}

	public void setRepairPlansEnrolledIn(List<RepairPlan> repairPlansEnrolledIn) {
		this.repairPlansEnrolledIn = repairPlansEnrolledIn;
	}

	public List<Appliance> getCustomerAppliances() {
		return customerAppliances;
	}

	public void setCustomerAppliances(List<Appliance> customerAppliances) {
		this.customerAppliances = customerAppliances;
	}

	public double getRepairPlanCost() {
		return repairPlanCost;
	}

	public void setRepairPlanCost(double repairPlanCost) {
		this.repairPlanCost = repairPlanCost;
	}

	public String getRepairPlanApplianceID() {
		return repairPlanApplianceID;
	}

	public void setBackorderQuantity(int quantity) {
		backorderQuantity = quantity;
	}

	public int getBackorderQuantity() {
		return backorderQuantity;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public int getFurnacesOrdered() {
		return furnacesOrdered;
	}

	public void setFurnacesOrdered(int insufficientFurnaceStock) {
		this.furnacesOrdered = insufficientFurnaceStock;
	}

	public String getBackorderID() {
		return backorderID;
	}

	public void setBackorderID(String backorderID) {
		this.backorderID = backorderID;
	}

	public String getApplianceBrand() {
		return applianceBrand;
	}

	public void setApplianceBrand(String applianceBrand) {
		this.applianceBrand = applianceBrand;
	}

	public String getApplianceModel() {
		return applianceModel;
	}

	public void setApplianceModel(String applianceModel) {
		this.applianceModel = applianceModel;
	}

	public String getApplianceID() {
		return applianceID;
	}

	public void setApplianceID(String applianceID) {
		this.applianceID = applianceID;
	}

	public double getApplianceCost() {
		return applianceCost;
	}

	public void setApplianceCost(double applianceCost) {
		this.applianceCost = applianceCost;
	}

	public int getApplianceStock() {
		return applianceStock;
	}

	public void setApplianceStock(int applianceStock) {
		this.applianceStock = applianceStock;
	}

	public void setRepairPlanApplianceID(String repairPlanApplianceID) {
		this.repairPlanApplianceID = repairPlanApplianceID;
	}

}
