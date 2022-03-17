package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import edu.ics372.gp1.business.store.Customer;

public class CustomerFilteredIterator implements Iterator<Customer> {
	private Customer item;
	private Predicate<Customer> predicate;
	private Iterator<Customer> iterator;

	/**
	 * Sets the iterator and predicate fields and positions to the first item that
	 * satisfies the predicate.
	 * 
	 * @param iterator  the iterator to the list
	 * @param predicate specifies the test
	 */
	public CustomerFilteredIterator(Iterator<Customer> iterator, Predicate<Customer> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}

	@Override
	public boolean hasNext() {
		return item != null;
	}

	@Override
	public Customer next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		Customer returnValue = item;
		getNextItem();
		return returnValue;
	}

	/*
	 * This method searches for the next item that satisfies the predicate. If none
	 * is found, the item field is set to null.
	 */
	private void getNextItem() {
		while (iterator.hasNext()) {
			item = iterator.next();
			if (predicate.test(item)) {
				return;
			}
		}
		item = null;
	}
}
