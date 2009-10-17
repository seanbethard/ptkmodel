package model.systemicSimplicity;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

import lingNet.Agent;
import lingNet.SocialNetwork;
import perceptron.*;

public class Main {
	
	// These are default parameters taken from Joe's presentation slides.
	static int numOfChildren = 2;
	static int numOfChildLearning = 200, numOfAdolLearning = 10000;
	public static double learningRate = 0.01;
	public static double noise = 0.2;
	static boolean parametersSet = false;
	
	// Non-parameter & stuff
	static int totalNumberOfCyclesSoFar = 0;
	static SocialNetwork network;
	Random generator = new Random();
	static String[] output = new String[1000000];

	static String filePath;
	static String fileName;
	static boolean fileSelected = false;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setVisible(true);
		do {
			// Empty loop
		} while (fileSelected == false);
		gui.createParameterWindow();
		do {
			// Empty loop
		} while (parametersSet == false);
		Main model = new Main();
		model.network = model.createSocialNetwork(numOfChildren);
		System.out.println("Beginning Parent --> Child Learning.");
		for (int i = 0; i < numOfChildLearning; i++) {
			totalNumberOfCyclesSoFar++;
			model.learnFromDistribution();
			
		}
		for (int i = 0; i < network.getNetworkSize(); i++) {
			CSVFileCreator fw = new CSVFileCreator(network.getAgent(i).ts,i,1);
		}
		System.out.println("Beginning Child <--> Child Learning.");
		for (int i = 0; i < numOfAdolLearning; i++) {
			totalNumberOfCyclesSoFar++;
			model.learnFromOtherAgents();
			
		}
		for (int i = 0; i < network.getNetworkSize(); i++) {
			CSVFileCreator fw = new CSVFileCreator(network.getAgent(i).ts,i,2);
			PercentageFileCreator pfw = new PercentageFileCreator();
		}
		// TODO File output
	}
	
	public SocialNetwork createSocialNetwork(int numOfAgents) {
		SocialNetwork net = new SocialNetwork();
		for (int i = 0; i < numOfAgents; i++) {
			Agent newChild = new Agent(net,filePath,fileName);
			net.addAgent(newChild);	
		}
		// Setup one connection from each agent to another agent.
		for (int i = 0; i < net.getNetworkSize(); i++) {
			Agent current = net.getAgent(i);
			Agent agentToConnectTo;
			do {
				agentToConnectTo = net.getRandomAgent();
			} while (net.getAgent(i) == agentToConnectTo);
			current.addConnection(agentToConnectTo.getPositionInNetwork());
		}
		return net;
	}
	
	public void learnFromDistribution() {
			
			for (int j = 0; j < network.getNetworkSize(); j++) {
			Agent currentAgent = network.getAgent(j);
			TableauSet currentTS = currentAgent.ts;
			Perceptron learner = new Perceptron(currentTS);
			for (int k = 0; k < currentTS.getNumberOfTableaux(); k++) {
				Tableau currentTableau = currentTS.getTableau(k);
				Candidate randomCandidate = selectCandidateFromProbDist(currentTableau);
				learner.perceptronLearn(randomCandidate, currentTableau.findOptimalCandidate());
				learner.perceptronSolve();
			}
			output[totalNumberOfCyclesSoFar + 1] = Double.toString(percentCorrect(j));
		}
	}
	
	public void learnFromOtherAgents() {
			
			for (int j = 0; j < network.getNetworkSize(); j++) {
			Agent currentAgent = network.getAgent(j);
			TableauSet currentTS = currentAgent.ts;
			Perceptron learner = new Perceptron(currentTS);
			for (int k = 0; k < currentTS.getNumberOfTableaux(); k++) {
				Tableau currentTableau = currentTS.getTableau(k);
				int agentToConnectTo = currentAgent.getRandomConnection();
				TableauSet connectingTS = network.getAgent(agentToConnectTo).ts;
				Tableau connectingTableau = connectingTS.getTableau(k);
				Candidate newCandidate = connectingTableau.findOptimalCandidate();
				learner.perceptronLearn(newCandidate, currentTableau.findOptimalCandidate());
				learner.perceptronSolve();
			}	
			output[totalNumberOfCyclesSoFar + 1] = Double.toString(percentCorrect(j));
		}
	}
	
	// Selects a random candidate based on a probability distribution.
	public Candidate selectCandidateFromProbDist(Tableau tab) {
		ArrayList<Candidate> distribution = new ArrayList<Candidate>();
		for (int i = 0; i < tab.getCandidates().size(); i++) {
			if (!(tab.getCandidate(i).getFrequency() <= 0)) {
				for (int j = 0; j < tab.getCandidate(i).getFrequency(); j++) {
					distribution.add(tab.getCandidate(i));
				}
			}
		}
		int constraintToSelect = generator.nextInt(distribution.size());
		return distribution.get(constraintToSelect);
	}

	public double percentCorrect(int agentToCheck) { 
		TableauSet agentTS = network.getAgent(agentToCheck).ts;
		int numberOfAgentTableaux = agentTS.getTableaux().size();
		int numberOfCorrectOutputs = 0;
		for (int i = 0; i < numberOfAgentTableaux; i++) {
			//System.out.println(agentTS.getTableau(i).getInput().toString());
			//System.out.println(agentTS.getTableau(i).findOptimalCandidate().getOutput().toString());
			//System.out.println("------");
			if (agentTS.getTableau(i).getInput().toString().equals(agentTS.getTableau(i).findOptimalCandidate().getOutput().toString())) {
				numberOfCorrectOutputs++;				
			}
		}
		double percentCorrect = 0.0;
		percentCorrect = (double) numberOfCorrectOutputs / (double) numberOfAgentTableaux;
		//System.out.println(numberOfCorrectOutputs);
		//System.out.println(numberOfAgentTableaux);
		//System.out.println(percentCorrect);
		return percentCorrect;
	}
	
}