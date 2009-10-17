package perceptron;

import java.text.DecimalFormat;

/**
 * Functions from candidates to violation marks.
 * 
 * @author Michael Becker
 * @version 2.0 created 2007-02; last update 2007-02-19
 */

public class LinguisticConstraint {

	public LinguisticConstraint(TableauSet ts) {
		this.parent = ts;
	}
	
	
	/*
	 * ================================================================= Class
	 * variables
	 * =================================================================
	 */


	/** Names for this constraint. */
	private String name, shortName, HTMLName;

	public TableauSet parent;

	// private String constraintFamily ; // mark / faith / ....

	/** The constraint's weight. The default is 10 */
	private double weight = 10;

	private double initialRanking = 0.0;
	private double noise = 0.0;
	private boolean useLocalNoise = false;
	private double bias = -1;

	// / the value of minConstraintWeight is used only if
	// / useLocalMinConstraintWeight is true. if false,
	// / the value from the TableauSet is used
	private double minConstraintWeight = 0.0;
	private boolean useLocalMinConstraintWeight = false;

	private int stratum = 0;

	protected int type;

	protected final static int OOFAITHFULNESS = 1;
	protected final static int MARKEDNESS = 2;
	protected final static int IOFAITHFULNESS = 3;

	protected final static int BRFAITHFULNESS = 99;
	protected final static int PREC = 99;

	
	public double setBias(double i) {
		return this.bias = -i;
	}
	
	public boolean isMarkedness() {
		return (this.type == LinguisticConstraint.MARKEDNESS);
	}

	public void setMarkedness() {
		this.type = LinguisticConstraint.MARKEDNESS;
	}

	public boolean isIOFaithfulness() {
		return (this.type == LinguisticConstraint.IOFAITHFULNESS);
	}

	public void setIOFaithfulness() {
		this.type = LinguisticConstraint.IOFAITHFULNESS;
	}

	public boolean setNoise(double dbl) {
		if (this.useLocalNoise) {
			this.noise = dbl;
			return true;
		} else {
			return false;
		}
	}
	
	public void setUseLocalNoise(boolean b) {
		this.useLocalNoise = b;
	}
	
	public boolean isOOFaithfulness() {
		return (this.type == LinguisticConstraint.OOFAITHFULNESS);
	}

	public void setOOFaithfulness() {
		this.type = LinguisticConstraint.OOFAITHFULNESS;
	}

	public boolean isFaithfulness() {
		return ((this.type == LinguisticConstraint.IOFAITHFULNESS)
				|| (this.type == LinguisticConstraint.BRFAITHFULNESS) || (this.type == LinguisticConstraint.OOFAITHFULNESS));
	}

	public boolean isPREC() {
		return (this.type == LinguisticConstraint.PREC);
	}

	public boolean setType(int i) {
		this.type = i;
		return true;
	}

	/*
	 * ================================================================= Weights
	 * =================================================================
	 */

	/**
	 * Get the constraint's weight. return weight A Double.
	 */
	public Double getWeight() {
		return this.weight;
	}

	/**
	 * Get a rounded version of the weighted total for this constraint.
	 * 
	 * @return roundedWeight The weight rounded to 3 places.
	 * @see #getWeight()
	 */
	public Double getRoundedWeight() {
		double weight = getWeight();
		DecimalFormat df = new DecimalFormat("0.###");
		String weightString = df.format(weight);
		double roundedWeight = new Double(weightString);
		return roundedWeight;
	}

	/**
	 * Set the consraint's weight.
	 * 
	 * @param weight
	 *            The new weight.
	 * @return true
	 */
	public boolean setWeight(Double weight) {
		this.weight = weight;
		return true;
	}

	public double getMinConstraintWeight() {
		if (this.useLocalMinConstraintWeight) {
			return this.minConstraintWeight;
		} else {
			return this.parent.getMinConstraintWeight();
		}
	}

	public boolean setMinConstraintWeight(double newMin) {
		if (this.useLocalMinConstraintWeight) {
			this.minConstraintWeight = newMin;
			return true;
		} else {
			return false;
		}
	}

	public void setUseLocalMinConstraintWeight(boolean b) {
		this.useLocalMinConstraintWeight = b;
	}

	public TableauSet getParent() {
		return this.parent;
	}

	public void setParent(TableauSet tabs) {
		this.parent = tabs;
	}

	/*
	 * ================================================================= Naming
	 * =================================================================
	 */

	/**
	 * Get the name of the constraint.
	 * 
	 * @return name A string.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name of the constraint.
	 * 
	 * @param name
	 *            The new name for the constraint.
	 * @return true
	 */
	public boolean setName(String name) {
		this.name = name;
		return true;
	}

	/**
	 * Get the short name of the constraint.
	 * 
	 * @return shortName The shortname for the constraint.
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * Set the shortname of the constraint.
	 * 
	 * @param shortName
	 *            The shortname of the constraint.
	 * @return true always
	 */
	public boolean setShortName(String shortName) {
		this.shortName = shortName;
		return true;
	}

	/**
	 * Get the name of the constraint.
	 * 
	 * @return name In html format.
	 */
	public String getHTMLName() {
		return this.HTMLName;
	}

	/**
	 * Set the shortname of the constraint.
	 * 
	 * @param HTMLName
	 *            A string representing the constraint.
	 * @return true
	 */
	public boolean setHTMLName(String HTMLName) {
		this.HTMLName = HTMLName;
		return true;
	}

	// public int eval (LinguisticForm input, LinguisticForm output) {
	// int violations = 0;
	// return violations;
	// }

	/*
	 * =================================================================
	 * Printing
	 * =================================================================
	 */

	/**
	 * Just prints the constraint's name.
	 * 
	 * @return name Just prints the constraint's full name in plain text.
	 */
	public String toString() {
		return this.name;
	}

	public double getBias() {
		return this.bias;
	}

	public double setBias(int i) {
		return this.bias = i;
	}

	public int getStratum() {
		return this.stratum;
	}

	public int setStratum(int i) {
		return this.stratum = i;
	}

	public boolean setInitialRanking(double dbl) {
		this.initialRanking = dbl;
		return true;
	}
	
}
