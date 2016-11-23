package org.yesworkflow.util.uri;

/**
 * This class represents a URI.  It is more flexible than
 * java.net.URI, supporting, e.g., URIs with path portions that
 * do not begin with a slash.
 * 
 * This file is derived from UriBase.java in the org.restflow.data package
 * as of 28Apr2015.
 */
public class Uri extends UriBase implements Comparable<Uri> {

	
	/**
	 * Constructs a new URI object. Delegates parsing of the expression
	 * parameter to the UriBase parent class.
	 * 
	 * @param expression			A string representation of the full 
	 *                           	URI to construct.
	 */
	public Uri(String expression) {
		super(expression);
	}

	/**
	 * @param o		The object to compare with this.
	 * @return 		true if the reduced paths of the two templates are identical, 
	 *              otherwise false.
	 */
	public boolean equals(Object o) {
		
		// return true of this is compared to itself
		if (o == this) return true;

		// return false if o is the wrong type or null
		if (!(o instanceof Uri)) return false;
		
		// return true if this and o have the same (String) expressions
		return (expression.equals(((Uri)o).getExpression()));
	}

	/**
	 * @param u		The Uri object to compare with this.
	 * @return 		Integer representing the relative sort order of this and u.
	 */
	public int compareTo(Uri u) {
		return expression.compareTo(u.getExpression());
	}

	/**
	 * @return 		Hash code of the string representing the full Uri expression.
	 */
	public int hashCode() {
		return expression.hashCode();
	}
}
