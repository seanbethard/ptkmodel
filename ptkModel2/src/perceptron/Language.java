package perceptron;

import java.util.ArrayList;

public class Language {

	private ArrayList<Candidate> lang;
	private int LPStatus;
	private int RCDstatus;

	public Language(ArrayList<Candidate> cands) {
		this.lang = cands;
	}

	public ArrayList<Candidate> getCandidates() {
		if (lang == null)
			lang = new ArrayList<Candidate>();
		return lang;
	}

	public int setLPStatus(int i) {
		return this.LPStatus = i;
	}

	public int getLPStatus() {
		return this.LPStatus;
	}

	public int setRCDStatus(int i) {
		return this.RCDstatus = i;
	}

	public int getRCDStatus() {
		return this.RCDstatus;
	}

	public void add(Candidate cand) {
		if (this.lang == null)
			this.lang = new ArrayList<Candidate>();
		this.lang.add(cand);
	}

	@SuppressWarnings("unchecked")
	public Language clone() {

		ArrayList<Candidate> candidates = (ArrayList<Candidate>) this
				.getCandidates().clone();
		Language lang = new Language(candidates);
		lang.setLPStatus(this.getLPStatus());
		return lang;
	}

	public String toString() {

		return "" + this.getCandidates();
	}

}
