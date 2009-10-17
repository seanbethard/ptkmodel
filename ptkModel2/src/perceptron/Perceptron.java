package perceptron;

/**
 * An implementation of the Perceptron learning algorithm.
 * Some code was borrowed from HaLP, it has been documented below.
 * @author Pat Pratt
 *
 */
import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.math.*;

import model.systemicSimplicity.Main;
/**
 * Stuff to add: Evaluation Noise Initial plasticity Replications per Plasticity
 * Plasticity decrement Number of Plasticities Relative Plasticity Spreading
 * Fraction Correct (table)
 * 
 */
public final class Perceptron {

	private static TableauSet ts;

	/**
	 * This is the parameter that defines whether or not there will be
	 * evaluation noise.
	 */
	private static boolean withNoise;
	private static double stdDev;

	public Perceptron(String OTSoftFileName) {
		// Some preliminary work on the file.
		ArrayList<String> OTSoftFile = getPrunedFile(OTSoftFileName);

		// Make a TableauSet from the OTSoft file.
		ts = new TableauSet();
		ts.readOTSoftFile(OTSoftFile);
		withNoise = true;
	}

	public Perceptron(TableauSet input) {
		ts = input;
		withNoise = true;
	}

	/**
	 * public static void main(String[] args) throws Exception{
	 * 
	 * // File input. String OTSoftFileName = "test-files/alderete.txt"; if
	 * (args != null && args.length > 0) OTSoftFileName = args[0]; }
	 */

	public void perceptronSolve() {
		String newline = System.getProperty("line.separator");
		int iterations = 1; // make this a parameter later
		//int interval = 25; // this too
		// ArrayList<LinguisticConstraint> currentCon = ts.getConstraints();
		/*
		 * File output = new File("output.txt");
		 * 
		 * //Write out the first line of the output file try { BufferedWriter
		 * out = new BufferedWriter(new FileWriter(output)); String line = "\t";
		 * for(int i = 0; i < ts.getConstraints().size(); i++){ line +=
		 * ts.getConstraint(i) + "\t"; } line += newline; out.write(line);
		 * //out.close();
		 */

		for (int i = 0; i < iterations; i++) {
			/*
			 * //Write the weights to the output file if((i + 1) % interval ==
			 * 0){ line = (i + 1) + "\t"; //adjust the line number, which is one
			 * more than the counter for(int j = 0; j <
			 * ts.getConstraints().size(); j++){ line +=
			 * ts.getConstraint(j).getRoundedWeight() + "\t"; } line += newline;
			 * out.write(line); }
			 */
			Tableau currentTableau = ts.randomTableau();
			Candidate winner = currentTableau.getWinners().get(0); // there
																	// should
																	// only be
																	// one
																	// winner
			// DEBUG
			// System.out.println(winner);

			for (int j = 0; j < currentTableau.candidateCount(); j++) {
				Candidate loser = currentTableau.getCandidate(j);
				// System.out.println(loser);
				if (!(winner.getName().equals(loser.getName()))) { // sanity
																	// check
					if (withNoise) {
						// TODO
						if (winner.getWeightWithNoise(1) > loser
								.getWeightWithNoise(1)) {
							// DEBUG
							// System.out.println("learning...");
							perceptronLearn(winner, loser);
						}
					} else if (winner.getWeight() > loser.getWeight()) {
						// System.out.println("learning...");
						perceptronLearn(winner, loser);
					}

				}
			}
		}
		// out.close();
		// }// catch (IOException e) {
		// e.printStackTrace();
		// }

		// Iterator test = ts.getConstraints().iterator();
		// while(test.hasNext()){
		// LinguisticConstraint jjjjj = (LinguisticConstraint)test.next();
		// System.out.println(jjjjj);
		// }
		// return ts;
	}

	public static void perceptronLearn(Candidate winner, Candidate loser) {
		// decay
		double n = 0.1;
		Iterator winnerViol = winner.getViolationVector().iterator();
		Iterator loserViol = loser.getViolationVector().iterator();

		Iterator conIter = ts.getConstraints().iterator();
		while (winnerViol.hasNext()) {
			LinguisticConstraint currentCon = (LinguisticConstraint) conIter
					.next();
			double multiplier = 1.0;
			if (currentCon.isMarkedness())
				multiplier = ts.getMarkMultiplier();
			if (currentCon.isIOFaithfulness())
				multiplier = ts.getIOFaithMultiplier();
			if (currentCon.isOOFaithfulness())
				multiplier = ts.getOOFaithMultiplier();

			Cell c = (Cell) winnerViol.next();
			currentCon.setWeight(currentCon.getWeight() - c.getViolation() * n
					* multiplier);
			// System.out.println(multiplier);
			// System.out.println(currentCon.getWeight());
		}
		conIter = ts.getConstraints().iterator();
		while (loserViol.hasNext()) {
			LinguisticConstraint currentCon = (LinguisticConstraint) conIter
					.next();
			double multiplier = 1.0;
			if (currentCon.isMarkedness())
				multiplier = ts.getMarkMultiplier();
			if (currentCon.isIOFaithfulness())
				multiplier = ts.getIOFaithMultiplier();
			if (currentCon.isOOFaithfulness())
				multiplier = ts.getOOFaithMultiplier();

			Cell c = (Cell) loserViol.next();
			// System.out.println("Current Constraint:" + currentCon.getName());
			// System.out.println("Current weight:" + currentCon.getWeight());
			// System.out.println("New weight:" + (currentCon.getWeight() +
			// c.getViolation()*n*multiplier));
			// System.out.println("-----");
			currentCon.setWeight(currentCon.getWeight() + c.getViolation() * n
					* multiplier);
			// System.out.println(multiplier);
			// System.out.println(currentCon.getWeight());
		}

	}

	public static ArrayList<String> getPrunedFile(String fileName) {

		Pattern pat1 = Pattern.compile("^[^\\'\\$\\/\\#]");
		Pattern pat2 = Pattern.compile("^\\s*$");

		ArrayList<String> OTsoftfile = new ArrayList<String>();

		File OTSoft = new File(fileName);
		if (OTSoft.exists() && OTSoft.isFile() && OTSoft.canRead()) {

			try {

				BufferedReader input = new BufferedReader(
						new FileReader(OTSoft));
				String line = null;
				int line_number = 0;
				while ((line = input.readLine()) != null) {
					line_number++;
					Matcher matcher1 = pat1.matcher(line);
					Matcher matcher2 = pat2.matcher(line);
					if (line.length() > 0 && matcher1.find()
							&& !matcher2.find()) {
						OTsoftfile.add(line_number + "\t" + line);
					}
				}
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		} else {

			// System.out.println("File not found, or could not be read.");
		}

		return OTsoftfile;

	}

}
