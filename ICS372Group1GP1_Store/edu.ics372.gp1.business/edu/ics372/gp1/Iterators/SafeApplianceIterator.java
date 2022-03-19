/**
 * Safe appliance iterator used to prevent unwanted changes to lists when not needed.
 * @author Kean Jaycox, Justin Pham, Carl Noren
 */
package edu.ics372.gp1.Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ics372.gp1.business.facade.Result;

public class SafeApplianceIterator implements Iterator<Result> {
	private FilteredIterator iterator;
	private Result result = new Result();

	/**
	 * The user of SafeIterator must supply an Iterator to Appliance.
	 * 
	 * @param iterator Iterator<Appliance>
	 */
	public SafeApplianceIterator(FilteredIterator iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Result next() {
		if (iterator.hasNext()) {
			result.setApplianceFields(iterator.next());
		} else {
			throw new NoSuchElementException("No such element");
		}
		return result;
	}

}