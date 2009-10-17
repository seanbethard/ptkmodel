package perceptron;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * <p>
 * A tableau has a single input and some candidate outputs, with a designated
 * subset of winning outputs. Each output is associated with a violation profile
 * (vector of integers). External access should be minimal. The public face of
 * these objects is TableauSet.
 * </p>
 * 
 * @see TableauSet
 * @author Michael Becker
 * @author Christopher Potts
 * @version 2.0 created 2007-02-10; last update 2007-02-19
 */

public class Tableau {

	// private TableauSet parent;

	/*
	 * ================================================================= Class
	 * variables
	 * =================================================================
	 */

	static Random generator = new Random();
	
	/** The input linguistic form. */
	private LinguisticForm input;

	/** The constraint-set for this tableau. */
	private ArrayList<LinguisticConstraint> con;

	/** The candidates for this tableau. */
	private ArrayList<Candidate> candidates;

	private TableauSet parent;

	/*
	 * =================================================================
	 * Constructor
	 * =================================================================
	 */

	/**
	 * Build a new table from a TableauSet object. The constraints are
	 * determined by those of the input ts.
	 */
	public Tableau(TableauSet ts) {
		this.parent = ts;
		this.con = ts.getConstraints();
		ArrayList<Candidate> blank = new ArrayList<Candidate>();
		this.candidates = blank;

	}

	/**
	 * Get the TableauSet that the current Tableau belongs to
	 * 
	 * @return this.parent TableauSet object
	 */
	public TableauSet getParent() {
		return this.parent;
	}

	/*
	 * ================================================================= The
	 * input =================================================================
	 */

	/**
	 * Gets the input.
	 * 
	 * @return this.input The input LinguisticForm.
	 */
	public LinguisticForm getInput() {
		return this.input;
	}

	/**
	 * Set the input for this tableau.
	 * 
	 * @param input
	 *            The new LinguisticForm, set to this.input.
	 * @return true
	 */
	public boolean setInput(LinguisticForm input) {
		this.input = input;
		return true;
	}

	/*
	 * ================================================================= The
	 * constraints
	 * =================================================================
	 */

	/**
	 * The number of constraints for this tableau.
	 * 
	 * @return this.con.size() The size of the constraint set con.
	 */
	public int constraintCount() {
		return this.con.size();
	}

	/**
	 * The constraints for this table. This access is required by Candidate
	 * objects, specifically, by their getWeight() method, and by addTableau()
	 * of TableauSet.
	 * 
	 * @return this.con The list of constraints, as an ArrayList of
	 *         LinguisticConstraint objects.
	 */
	public ArrayList<LinguisticConstraint> getCon() {
		return this.con;
	}
	
	public LinguisticConstraint getRandomConstraint() {
		int numOfConstraints = this.getCon().size();
		return this.getCon().get(generator.nextInt(numOfConstraints));
		
	}
	
	public double getSumOfFrequencies() {
		double sum = 0;
		for (int i = 0; i < this.candidateCount(); i++) {
			sum = sum + this.getCandidate(i).getFrequency();
		}
		return sum;
	}
	
	public void setCon(ArrayList<LinguisticConstraint> consts) {
		this.con = consts;
	}

	// // These methods are commented-out because we want to be very
	// // cautious about manipulating the constraint set of individual
	// // tables. Such changes should be done in terms of TableauSet
	// // objects instead.
	//
	// public LinguisticConstraint getCon (int i) {
	// return this.con.get(i);
	// }
	//	
	// public boolean addConnstraint(Constraint constraint) {
	// return con.add(constraint);
	// }
	//	
	// public boolean setCon(ArrayList<Constraint> con) {
	// this.con = con;
	// return true;
	// }

	/*
	 * ================================================================= The
	 * candidates
	 * =================================================================
	 */

	/**
	 * Get the list of candidates.
	 * 
	 * @return this.candidates The list of candidates.
	 */
	public ArrayList<Candidate> getCandidates() {
		return this.candidates;
	}

	/**
	 * Get the candidate at a specific index.
	 * 
	 * @param i
	 *            The index.
	 * @return The candidate at index i.
	 */
	public Candidate getCandidate(int i) {
		return this.candidates.get(i);
	}
	
	public Candidate getRandomCandidate() {
		Random generator = new Random();
		return getCandidate(generator.nextInt(candidateCount()));
	}

	/**
	 * The number of candididates for this tableau.
	 * 
	 * @return this.candidates.size() The number of candidates.
	 */
	public int candidateCount() {
		return this.candidates.size();
	}

	/**
	 * Add a candidate.
	 * 
	 * @param cand
	 *            The Candidate object to add.
	 * @return true if the addition was successful, else false.
	 */
	public boolean addCandidate(Candidate cand) {
		if (this.candidates == null) {
			this.candidates = new ArrayList<Candidate>();
		}
		return this.candidates.add(cand);
	}

	public Candidate findOptimalCandidate() {

		// Keep a running list of possible most harmonic candidates.
		// In the case of a tie, one will be chosen arbitrarily.
		ArrayList<Candidate> mostHarmonic = new ArrayList<Candidate>();

		Iterator candIter = this.getCandidates().iterator();
		while (candIter.hasNext()) {

			if (mostHarmonic.size() == 0)
				mostHarmonic.add((Candidate) candIter.next());
			else {
				Candidate currentCand = (Candidate) candIter.next();
				double mostHarmonicWeight, currentCandWeight;

				mostHarmonicWeight = mostHarmonic.get(0).getWeight();
				currentCandWeight = currentCand.getWeight();

				// Check to see if the current candidate is most harmonic,
				// and label it as such if it is.
				if (currentCandWeight == mostHarmonicWeight) {
					mostHarmonic.add(currentCand);
				}
				if (currentCandWeight < mostHarmonicWeight) {
					mostHarmonic.clear();
					mostHarmonic.add(currentCand);
				}
			}
		}

		// If only one candidate was most harmonic, return it.
		if (mostHarmonic.size() == 1) {
			this.parent.makeWinner(mostHarmonic.get(0)); // designate it as the
															// winner
			return mostHarmonic.get(0);
		} else { // Pick one at random.
			// System.out.println("More than one selection, picking at random");
			Random r = new Random();
			double selection = r.nextDouble() * mostHarmonic.size();
			// System.out.println(selection);
			this.parent.makeWinner(mostHarmonic.get((int) selection)); // designate
																		// it as
																		// the
																		// winner
			return mostHarmonic.get((int) selection);
		}

	}

	/*
	 * ================================================================= The
	 * winners =================================================================
	 */

	/**
	 * Get the winners. A winner is a candidate with a positive frequency (as
	 * specified in column 3 of the file (index 3 of the candidate list).
	 * 
	 * @return winners The ArrayList of winning Candidate objects.
	 */
	public ArrayList<Candidate> getWinners() {
		ArrayList<Candidate> winners = new ArrayList<Candidate>();
		Iterator candidates = this.candidates.iterator();
		while (candidates.hasNext()) {
			Candidate cand = (Candidate) candidates.next();
			if (cand.getFrequency() > 0) {
				winners.add(cand);
			}
		}
		return winners;
	}

	public ArrayList<Candidate> getLosers() {
		ArrayList<Candidate> losers = new ArrayList<Candidate>();
		Iterator candidates = this.candidates.iterator();
		while (candidates.hasNext()) {
			Candidate cand = (Candidate) candidates.next();
			if (cand.getFrequency() <= 0) {
				losers.add(cand);
			}
		}
		return losers;
	}

	/**
	 * Check that there is at least one winner.
	 * 
	 * @return true if there is a winner, else false.
	 */
	public boolean hasWinners() {
		boolean hasWinners = false;
		Iterator candidates = this.candidates.iterator();
		while (candidates.hasNext()) {
			Candidate cand = (Candidate) candidates.next();
			if (cand.getFrequency() > 0) {
				hasWinners = true;
			}
		}
		return hasWinners;
	}

	/**
	 * Find out if there is more than one winner.
	 * 
	 * @return true if there is exactly one winner, else false.
	 */
	public boolean hasUniqueWinner() {
		int winners = 0;
		Iterator candidates = this.candidates.iterator();
		while (candidates.hasNext()) {
			Candidate cand = (Candidate) candidates.next();
			if (cand.getFrequency() > 0) {
				winners++;
			}
		}
		return (winners == 1);
	}

	/**
	 * The number of winners.
	 * 
	 * @return int The number of winners.
	 */
	public int winnerCount() {
		return getWinners().size();
	}

	public void setUniqueWinner(Candidate cand) {

		if (this.getCandidates().contains(cand)) {
			Iterator it = this.getCandidates().iterator();
			while (it.hasNext()) {
				Candidate temp = (Candidate) it.next();
				if (temp.equals(cand)) {
					cand.setFrequency(1);
				} else {
					temp.setFrequency(0);
				}
			}

		}

	}

}
