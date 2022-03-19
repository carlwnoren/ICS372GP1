/**
 * Used to get data from instance and send to store class for processing.
 * @author - Carl Noren, Justin Pham, Kean Jay
 */
package edu.ics372.gp1.business.facade;

public class Request extends DataTransfer {
	private static Request request;

	/**
	 * This is a singleton class. Hence the private constructor.
	 */
	private Request() {

	}
	
	/**
	 * This instance method ensures only one request object exists,
	 * supporting the singleton pattern.
	 * @return
	 */
	public static Request instance() {
		if (request == null) {
			request = new Request();
		}
		return request;
	}

}
