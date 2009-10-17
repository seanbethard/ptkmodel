package perceptron;

public class ERC {

	private Candidate winner;
	private Candidate loser;

	private double[] learningVector;
	private LinguisticForm input;

	private ERC equivalentTo;
	private boolean accountedFor = false;

	public final static int E = 0;
	public final static int W = 1;
	public final static int L = -1;

	public Candidate getWinner() {
		return this.winner;
	}

	public Candidate setWinner(Candidate cand) {
		return this.winner = cand;
	}

	public Candidate getLoser() {
		return this.loser;
	}

	public Candidate setLoser(Candidate cand) {
		return this.loser = cand;
	}

	public LinguisticForm getInput() {
		return this.input;
	}

	public LinguisticForm setInput(LinguisticForm form) {
		return this.input = form;
	}

	public double[] getLearningVector() {
		return this.learningVector;
	}

	public double[] setLearningVector(double[] lv) {
		return this.learningVector = lv;
	}

	public boolean hasL() {
		boolean success = false;
		for (int i = 0; i < this.getLearningVector().length; i++) {
			if (this.getLearningVector()[i] == ERC.L) {
				success = true;
				break;
			}
		}
		return success;
	}

	public boolean hasW() {
		boolean success = false;
		for (int i = 0; i < this.getLearningVector().length; i++) {
			if (this.getLearningVector()[i] == ERC.W) {
				success = true;
				break;
			}
		}
		return success;
	}

	public boolean amountsTo(ERC erc) {
		boolean identical = true;
		if (this.getLearningVector().length == erc.getLearningVector().length) {
			for (int i = 0; i < this.getLearningVector().length; i++) {
				if (this.getLearningVector()[i] != erc.getLearningVector()[i])
					identical = false;
			}
		} else {
			identical = false;
		}
		return identical;
	}

	public boolean setAccountedFor(boolean bool) {
		return this.accountedFor = bool;
	}

	public boolean isAccountedFor() {
		return this.accountedFor;
	}

	public ERC getEquivalent() {
		return this.equivalentTo;
	}

	public void setEquivalent(ERC erc) {
		this.equivalentTo = erc;
	}

	public String toString() {
		String str = this.getWinner().getOutput() + " ~ "
				+ this.getLoser().getOutput();
		return str;
	}

}
