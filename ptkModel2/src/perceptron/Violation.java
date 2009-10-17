package perceptron;

/**
 * <p>
 * Violation objects for data (interfaces) that don't use numerical values.
 * Currently, this class plays no role in HaLP.
 * </p>
 * 
 * <p>
 * Each cell in a tableau will hold a list of Violation objects, which record
 * the subpart of the candidate that is penalized by a constraint so that
 * violation marks can be attributed to subparts of the candidate.
 * </p>
 * 
 * @author Michael Becker
 * @version 2.0 created 2007-2; last update 2007-02-19
 */

public class Violation {

	/*
	 * ================================================================= Class
	 * variables
	 * =================================================================
	 */

	/** Integer severity. */
	private int severity;

	/** The locus of violation in the LinguisticElement. */
	// private LinguisticElement locus;
	/*
	 * ================================================================= Other
	 * methods =================================================================
	 */

	/**
	 * Get the locus of violation.
	 * 
	 * @return this.locus The LinguisticElement where the violation occurred.
	 */
	// public LinguisticElement getLocus() {
	// return this.locus;
	// }
	/**
	 * Get the severity level.
	 * 
	 * @return this.severity The severity level of the violation.
	 */
	public int getSeverity() {
		return this.severity;
	}

}
