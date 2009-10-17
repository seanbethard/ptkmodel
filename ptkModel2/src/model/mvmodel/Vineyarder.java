package model.mvmodel;

import java.util.ArrayList;

import perceptron.TableauSet;

import lingNet.Agent;
import lingNet.SocialNetwork;

public class Vineyarder extends Agent {

	public enum Occupation {FISHING,FARMING,OTHER}
	public enum Location {UP_ISLAND,DOWN_ISLAND,TOURIST}
	public enum Age {FROM_14_TO_30,FROM_31_TO_45,FROM_46_TO_60,FROM_60_TO_75,OLDER_THAN_75}
	public enum SpeechProfile {MAINLAND,RAISER,HYPERCORRECTER}

    public Occupation job;
    public Location loc;
    public Age age;
    public SpeechProfile sp;
    
    ArrayList<Integer> connections = new ArrayList<Integer>();
    
    TableauSet ts = new TableauSet();
    
    public Vineyarder(SocialNetwork network, String filePath, String fileName) {
		super(network, filePath, fileName);
		
	}

    public Occupation getJob() {
    	
    }
    
    public void setJob() {
    	
    }
    
    public Location setLoc() {
    	
    }
    
    public void setLoc() {
    	
    }

    public Age getAge() {
    	
    }
    
    public void setAge(int n) {
    	this.age = n;
    }
    
    public SpeechProfile getSP() {
    	
    }
    
    public void setSP () {
    	
    }
}