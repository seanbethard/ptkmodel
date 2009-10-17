package model.mvmodel;

import java.io.File;
import java.util.Random;

import lingNet.*;

public class Main {

	static Random generator = new Random();
    
	static String otsoftFile;
	static String filePath;
	static String fileName;
	
    static SocialNetwork mv = new SocialNetwork();
    
	// ####################### START DATASET VALUES ###########################
    /* The following values are currently hard-coded, eventually they should be 
    set through the command line */
	// Occupation
    int numOfFishing = 20;
    int numOfFarming = 80;
    int numOfOther = 460;
    // Location
    // 20 Fisherman, 60 Farmers, 90 Others
    int numOfUpIslanders = 170;
    // 20 Farmers, 370 Others
    int numOfDownIslanders = 390;
    // Ages
    int numOf14to30 = 102;
    int numOf31to45 = 170;
    int numOf46to60 = 131;
    int numOf61to75 = 112;
    int numOf75plus = 45;
    // Connections
    // 50 is in keeping with Dunbar's number assuming each Agent represents 10 people.
    int maxNumOfConnections = 15; 
    // ######################## END DATASET VALUES ############################
    
	public static void main(String[] args) {
		otsoftFile = args[0];
		File f = new File(otsoftFile);
		filePath = f.getAbsolutePath();
		fileName = f.getName();
		// Make this a parameter.
		Date d = new Date(1925);
		Main m = new Main();
	}

	public void createDataSet() {
        /* According to Labov there were 1,717 up-islanders, 
        3,846 down-islanders and 42,000 seasonal tourists. 
        After rounding: ~1,700, ~3,900, and ~42,000
        If each node represents 10 people the dataset should contain 170 
        up-islanders, 390 down-islanders and 4,200 tourists.
        */
       
		// TOTAL: 560 Islanders
		for (int i = 0; i < 560; i++) {
			Vineyarder nvydr = new Vineyarder(mv,filePath,fileName);
			
			
		}
		//Create Network
		
		
	}

	public void assignJob() {
		
	}

	public boolean getJobAvail(int randomJob) {
		return true;
	}
	
	public void assignLocation() {
		
	}

	public boolean getLocAvail(int randomLoc) {
		return true;
	}
	
	public void assignOccupation() {
		
	}

	public boolean getOccAvail(int randOcc) {
		return true;
	}

}