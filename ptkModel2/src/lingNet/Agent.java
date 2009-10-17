package lingNet;

import perceptron.*;

import java.util.Random;
import java.util.ArrayList;
import model.*;

public class Agent {

	public Agent(SocialNetwork network,String filePath,String fileName) {
		// Set parent of node to the network it belongs to.
		this.parent = network;
		this.ts = readTableaux(filePath, fileName);
	}
	
	Random generator = new Random();

	public SocialNetwork parent;

	/*
	 * Each integer in this ArrayList points to the index of another node within
	 * the SocialNetwork.
	 */
	ArrayList<Integer> connections = new ArrayList<Integer>();

	// Each agent has their own TableauSet.
	public TableauSet ts = new TableauSet();

	public SocialNetwork getParent() {
		return this.parent;
	}

	public void addConnection(int con) {
		this.connections.add(con);
	}

	public int getConnection(int con) {
		return this.connections.get(con);
	}
	
	public int getRandomConnection() {
		return getConnection(generator.nextInt(this.connections.size()));
	}
	
	public void removeConnection(int con) {
		this.connections.remove(con);
	}

	public int getPositionInNetwork() {
		return this.parent.getAgentNumber(this);
	}
	
	public TableauSet readTableaux(String filePath, String fileName) {
		ArrayList<String> OTSoftFile = TableauSet.getPrunedFile("" + filePath);
		ts = new TableauSet();
		ts.readOTSoftFile(OTSoftFile);
		
		String shortName = "" + filePath;
		ts.setFileName(shortName);
		if (shortName.length() > 60) {
			if (shortName.substring(shortName.length() - 60).indexOf("/") > 60) {
				shortName = shortName.substring(shortName.length() - 60);
				shortName = "..." + shortName.substring(shortName.indexOf("/"));
			}
		}
		return ts;
	}	
}