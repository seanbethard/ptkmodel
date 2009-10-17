package perceptron;

/**
 * <p>
 * A cell is an intersection of a constraint and a candidate, the place that
 * holds stars and exclamation marks in a traditional tableau. This simple kind
 * of cell only holds an integer (the number of stars).
 * </p>
 * 
 * <p>
 * One day, this class will get extended to a more advanced thing that holds a
 * list of Violation object (see the Violation class for detail).
 * </p>
 * 
 * @author Michael Becker
 * @version 2.0 created 2007-02; last update 2007-02-19
 */

public class Cell {

	/*
	 * ================================================================= Class
	 * variables
	 * =================================================================
	 */

	/** The violation count, initialized to 0. */
	private double violations = 0;

	/*
	 * =================================================================
	 * Constructor
	 * =================================================================
	 */

	/** Construct a new cell from an input integer. */
	public Cell(double i) {
		this.violations = i;
	}

	/*
	 * =================================================================
	 * Violations
	 * =================================================================
	 */

	/**
	 * Returns the number of violations in this cell.
	 * 
	 * @return violations The number of violations.
	 */
	public double getViolation() {
		return violations;
	}

	/**
	 * Specify an integer-valued violation count.
	 * 
	 * @param viol
	 *            The number of violations to be set.
	 * @return true
	 */
	public boolean setViolation(double viol) {
		this.violations = viol;
		return true;
	}

}
