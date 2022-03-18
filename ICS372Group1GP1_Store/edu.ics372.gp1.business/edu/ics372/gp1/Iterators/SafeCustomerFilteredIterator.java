package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.business.facade.Result;

public class SafeCustomerFilteredIterator implements Iterator<Result> {
	private CustomerFilteredIterator iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Appliance.
	 * 
	 * @param customerFilteredIterator Iterator<Appliance>
	 */
	public SafeCustomerFilteredIterator(CustomerFilteredIterator customerFilteredIterator) {
		this.iterator = customerFilteredIterator;
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
