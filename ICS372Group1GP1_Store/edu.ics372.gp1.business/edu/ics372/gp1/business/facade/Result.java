/**
 * Class transmit results back to UI and report whether the operation was successful.
 * 
 * @author - Carl Noren, Justin Pham, Kean Jay
 */
package edu.ics372.gp1.business.facade;

public class Result extends DataTransfer {

	public static final int APPLIANCE_NOT_FOUND = 1;
	public static final int OPERATION_COMPLETED = 2;
	public static final int OPERATION_FAILED = 3;
	public static final int NO_SUCH_CUSTOMER = 4;
	public static final int INSUFFICIENT_STOCK = 5;
	public static final int BACKORDER_PLACED = 6;
	public static final int BACKORDER_NOT_FOUND = 7;
	public static final int REPAIR_PLAN_NOT_FOUND = 8;

	/**
	 * Stores the result of the method, that is transmitted to the user interface.
	 */
	private int resultCode;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
