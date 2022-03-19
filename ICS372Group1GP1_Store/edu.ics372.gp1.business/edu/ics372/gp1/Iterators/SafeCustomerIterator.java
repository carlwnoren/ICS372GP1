/**
 * Safe customer filtered iterator used to prevent unwanted changes to lists when not needed, and return filtered results
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */

package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.store.Customer;

public class SafeCustomerIterator implements Iterator<Result> {
	private Iterator<Customer> iterator;
	private Result result = new Result();

	/**
	 * The user of customerFilteredIterator must supply an Iterator to Appliance.
	 * 
	 * @param SafeCustomerIterator Iterator<Customer>
	 */
	public SafeCustomerIterator(Iterator<Customer> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setCustomerFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}
}
