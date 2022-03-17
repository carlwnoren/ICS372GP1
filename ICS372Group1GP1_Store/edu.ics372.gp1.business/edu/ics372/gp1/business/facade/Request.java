/**
 * Used to get data from instance and send to store class for processing.
 */
package edu.ics372.gp1.business.facade;

public class Request extends DataTransfer {
	private static Request request;

	/**
	 * This is a singleton class. Hence the private constructor.
	 */
	private Request() {

	}

	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

}
