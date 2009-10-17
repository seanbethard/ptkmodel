package perceptron;

/**
 * Linguistic forms or structures: Strings at present, but could be enriched.
 * This class will hold a plain string in its simplest manifestation. In the
 * future, it will hold complete structured linguistic elements, such as
 * features, prosodic constituents, and morphological structure
 * 
 * @author Michael Becker
 * @version 2.0 created 2007-02; last update 2007-02-19
 */
public class LinguisticForm {

	/*
	 * ================================================================= Class
	 * variables
	 * =================================================================
	 */

	/**
	 * The form itself. this textual representation is only used for reading in
	 * OTSoft files, where no real manipulations are done on the form
	 * */
	private String text;

	/*
	 * =================================================================
	 * Constructors
	 * =================================================================
	 */

	/**
	 * Construct a LinguisticForm from a string.
	 * 
	 * @param text
	 *            The form as a string.
	 */
	public LinguisticForm(String text) {
		this.text = text;
	}

	/** Construct an empty LinguisticForm object. */
	public LinguisticForm() {
	}

	/*
	 * =================================================================
	 * Printing
	 * =================================================================
	 */

	/**
	 * Just returns the text of the form.
	 * 
	 * @return this.text
	 */
	public String toString() {
		return this.text;
	}

	/*
	 * ================================================================= The
	 * form =================================================================
	 */

	/**
	 * Get the form itself (string).
	 * 
	 * @return this.text The text of the form.
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Set the form.
	 * 
	 * @param text
	 *            The new form as a string.
	 * @return true
	 */
	public boolean setText(String text) {
		this.text = text;
		return true;
	}

}
