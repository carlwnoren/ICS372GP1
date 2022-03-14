package edu.ics372.gp1.business.facade;

import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.Customer;
import edu.ics372.gp1.business.store.Furnace;
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
	private int backorderQuantity;
	private int purchaseQuantity;
	private int furnacesOrdered;
	private int orderQuantity;

	private int maxHeatOutput;
	private int capacity;

	private String backorderID;

	// needs repairplan fields

	/**
	 * This sets all fields to "none".
	 */
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

	public void setCustomerFields(Customer customer) {
		customerName = customer.getName();
		customerAddress = customer.getAddress();
		customerPhoneNumber = customer.getPhoneNumber();
		customerID = customer.getId();
		customerAccountBalance = customer.getAccountBalance();
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

	public void setApplianceFields(Appliance appliance) {
		applianceBrand = appliance.getBrand();
		applianceModel = appliance.getModel();
		applianceID = " ";// what is appliance ID going to be?
		applianceCost = appliance.getCost();
		applianceStock = appliance.getStock();
	}

	public void setFurnaceFields(Furnace furnace) {
		applianceBrand = furnace.getBrand();
		applianceModel = furnace.getModel();
		applianceID = furnace.getApplianceID();// what is appliance ID going to be?
		applianceCost = furnace.getCost();
		applianceStock = furnace.getStock();
		maxHeatOutput = furnace.getMaxHeatOutput();
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

	public void setRepairPlanApplianceID(String repairPlanApplianceID) {
		this.repairPlanApplianceID = repairPlanApplianceID;
	}

	public void setRepairPlanFields(RepairPlan repairPlan) {
		// needs work
		repairPlanCost = repairPlan.getCost();
		repairPlanApplianceID = repairPlan.getApplianceID();
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

	}
}
