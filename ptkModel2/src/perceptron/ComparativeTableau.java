package perceptron;

import java.util.ArrayList;
import java.util.Iterator;

public class ComparativeTableau {

	public final static int UNRANKED = 0;
	public final static int NO_RANKING_FOUND = 1;
	public final static int RANKING_FOUND = 2;

	private int status = UNRANKED;
	private ArrayList<ERC> ercs;
	private ArrayList<LinguisticConstraint> con;

	public ComparativeTableau(TableauSet ts) {
		this.con = ts.getConstraints();
		if (ts.getTableaux() != null) {
			Iterator it = ts.getTableaux().iterator();
			while (it.hasNext()) {
				Tableau tabl = (Tableau) it.next();
				readTableau(tabl);
			}
		}
	}

	public void readTableau(Tableau tabl) {
		if (tabl.hasUniqueWinner()) {
			Candidate winner = tabl.getWinners().get(0);
			Iterator it = tabl.getCandidates().iterator();
			while (it.hasNext()) {
				Candidate cand = (Candidate) it.next();
				if (cand != winner
						&& cand.getViolationVector().size() == winner
								.getViolationVector().size()) {

					ERC erc = new ERC();
					erc.setWinner(winner);
					erc.setLoser(cand);
					erc.setInput(tabl.getInput());
					double[] lv = new double[winner.getViolationVector().size()];
					for (int i = 0; i < winner.getViolationVector().size(); i++) {
						int mark = ERC.E;
						if (winner.getViolation(i) < cand.getViolation(i))
							mark = ERC.W;
						if (winner.getViolation(i) > cand.getViolation(i))
							mark = ERC.L;
						lv[i] = mark;
					}
					erc.setLearningVector(lv);
					this.addERC(erc);

				}
			}
		}
	}

	public ArrayList<ERC> getERCsForInput(LinguisticForm form) {
		ArrayList<ERC> currErcs = new ArrayList<ERC>();
		if (this.getERCs() != null) {
			Iterator it = this.getERCs().iterator();
			while (it.hasNext()) {
				ERC erc = (ERC) it.next();
				if (erc.getInput() == form)
					currErcs.add(erc);
			}
		}
		return currErcs;
	}

	public int getStatus() {
		return this.status;
	}

	public int setStatus(int i) {
		return this.status = i;
	}

	public ArrayList<LinguisticConstraint> getCon() {
		if (this.con == null)
			this.con = new ArrayList<LinguisticConstraint>();
		return this.con;
	}

	public ArrayList<ERC> getERCs() {
		if (this.ercs == null)
			this.ercs = new ArrayList<ERC>();
		return this.ercs;
	}

	public boolean addERC(ERC erc) {
		return this.getERCs().add(erc);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		String TABLEO = "<TABLE class='tableau' border='1'>";
		String TABLEC = "</TABLE>";
		String TRO = "<TR>";
		String TRC = "</TR>";
		String TDO = "<TD>";
		String TDOnormal = TDO;
		String TDOunaccounted = "<TD class='unaccountedfor'>";
		String TDC = "</TD>";

		sb.append(TABLEO + TRO + TDO + " input " + TDC + TDO
				+ " winner ~ loser " + TDC);

		Iterator it = this.getCon().iterator();
		while (it.hasNext()) {
			LinguisticConstraint cons = (LinguisticConstraint) it.next();
			sb.append(TDO + cons.getHTMLName() + TDC);
		}

		LinguisticForm prevInput = null;
		it = this.getERCs().iterator();
		while (it.hasNext()) {
			ERC erc = (ERC) it.next();
			if (!erc.isAccountedFor()) {
				TDO = TDOunaccounted;
			}
			sb.append(TRO);
			if (erc.getInput() != prevInput) {
				sb.append("<TD ROWSPAN="
						+ this.getERCsForInput(erc.getInput()).size() + ">"
						+ erc.getInput() + TDC);
			}
			prevInput = erc.getInput();

			sb.append(TDO + erc.getWinner().getOutput() + " ~ ");

			sb.append("<A STYLE='color:333399;' href='HaLP:manualComparative("
					+ erc.getLoser().getParent().getParent()
							.getCandidateNumber(erc.getLoser()) + ")'>"
					+ erc.getLoser().getOutput() + "</a>");

			sb.append(TDC);

			for (int i = 0; i < erc.getLearningVector().length; i++) {
				String mark = "";
				if (erc.getLearningVector()[i] == ERC.L)
					mark = "L";
				if (erc.getLearningVector()[i] == ERC.W)
					mark = "W";
				sb.append(TDO + mark + TDC);
			}
			sb.append(TRC);
			TDO = TDOnormal;
		}
		sb.append(TABLEC);

		return sb.toString();
	}

	public boolean removeERCs(ArrayList<ERC> ercs) {
		boolean success = true;
		if (ercs != null) {
			Iterator it = ercs.iterator();
			while (it.hasNext()) {
				ERC erc = (ERC) it.next();
				if (this.getERCs().contains(erc)) {
					success = this.getERCs().remove(erc);
				} else {
					success = false;
				}
			}
		}
		return success;
	}

}
