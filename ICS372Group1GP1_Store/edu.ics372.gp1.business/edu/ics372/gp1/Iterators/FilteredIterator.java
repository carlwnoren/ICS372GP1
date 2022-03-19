/**
 * Filtered iterator for use by Appliances 
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */
package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import edu.ics372.gp1.business.store.Appliance;

public class FilteredIterator implements Iterator<Appliance> {
	private Appliance item;
	private Predicate<Appliance> predicate;
	private Iterator<Appliance> iterator;

	/**
	 * Sets the iterator and predicate fields and positions to the first item that
	 * satisfies the predicate.
	 * 
	 * @param iterator  the iterator to the list
	 * @param predicate specifies the test
	 */
	public FilteredIterator(Iterator<Appliance> iterator, Predicate<Appliance> predicate) {
		this.predicate = predicate;
		this.iterator = iterator;
		getNextItem();
	}

	@Override
	public boolean hasNext() {
		return item != null;
	}

	@Override
	public Appliance next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No such element");
		}
		Appliance returnValue = item;
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