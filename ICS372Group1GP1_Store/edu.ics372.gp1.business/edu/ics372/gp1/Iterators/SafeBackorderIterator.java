/**
 * Safe Backorder iterator used to prevent unwanted changes to lists when not needed.
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */
package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.business.facade.Result;
import edu.ics372.gp1.business.store.Backorder;

public class SafeBackorderIterator implements Iterator<Result> {
	private Iterator<Backorder> iterator;
	private Result result = new Result();

	/**
	 * The user of the SafeBackorderITerator must supply an iterator
	 * 
	 * @param iterator<backorder>
	 */
	public SafeBackorderIterator(Iterator<Backorder> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setBackorderFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element.");
		}
		return result;
	}

}
