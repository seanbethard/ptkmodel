package lingNet;

import java.util.ArrayList;
import java.util.Random;
public class SocialNetwork {

public SocialNetwork() {    
    }

	// All the agents in the network.
	private ArrayList<Agent> Network = new ArrayList<Agent>();
	
	Random generator = new Random();
	
	public void addAgent(Agent nodeToBeAdded) {
        this.Network.add(nodeToBeAdded);
    }
	
	public Agent getAgent(int nodeNumber) {
        return this.Network.get(nodeNumber);
    }

	public Agent getRandomAgent() {
		return getAgent(generator.nextInt(this.getNetworkSize()));
	}
	
	public int getAgentNumber(Agent node) {
		int nodeNumber = Network.indexOf(node);
	    return nodeNumber;
	}
	
	public int getNetworkSize() {
		return this.Network.size();
	}
	
}
