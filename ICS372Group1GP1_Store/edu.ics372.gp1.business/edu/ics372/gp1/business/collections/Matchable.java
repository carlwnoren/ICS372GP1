package edu.ics372.gp1.business.collections;

/**
 * Matchable interface for generic object comparisons.
 * 
 * @param <K>
 */
public interface Matchable<K> {

	/**
	 * Checks if a given parameter matches that field in the calling object.
	 * @param other
	 * @return
	 */
	boolean matches(K other);

}
