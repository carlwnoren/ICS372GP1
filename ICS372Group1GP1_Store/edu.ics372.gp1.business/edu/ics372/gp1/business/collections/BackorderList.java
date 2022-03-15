/**
 * This class implements a BackorderList, which stores a list of all avaiable Backorder objects.
 * This class is a singleton.
 * It has the backorderList and backorders fields.
 * It has the getInstance and search methods.
 */
package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.store.Appliance;
import edu.ics372.gp1.business.store.Backorder;

public class BackorderList implements ItemList<Backorder, String>, Serializable {
	/**
	 * This field stores the singleton BackorderList object.
	 */
	private static BackorderList backorderList;
	/**
	 * This field stores all Backorder objects stored in this BackorderList.
	 */
	private List<Backorder> backorders = new LinkedList<Backorder>();
	private static String BACKORDER_STRING = "B";
	private int idCounter = 1000;
	
	private BackorderList() {
		
	}
	/**
	 * This method creates a BackorderList if none yet exists.
	 * @return either the newly created or existing backorderList
	 */
	public static BackorderList getInstance() {
		if (backorderList == null) {
			backorderList = new BackorderList();
		}
		return backorderList;
	}
	
	public String addBackorder(Appliance appliance, int quantity) {
		String backorderID =  BACKORDER_STRING + idCounter++;
		backorders.add(new Backorder(appliance, quantity, backorderID));
		return backorderID;
	}
	
	public void removeBackorder(Backorder backorder) {
		backorders.remove(backorder);
	}
	
	/**
	 * This method searches the appliances field for an Backorder object with a matching backorderID
	 * @return the matching Backorder or null, if no match is found.
	 */
	@Override
	public Backorder search(String backorderID) {
		for (Backorder backorder: backorders) {
			if (backorder.matches(backorderID)) {
				return backorder;
			}
		}
		return null;
	}
	@Override
	public String listAll() {
		 return backorders.toString();
	}
}
