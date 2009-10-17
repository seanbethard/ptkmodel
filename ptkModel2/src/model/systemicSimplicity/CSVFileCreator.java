package model.systemicSimplicity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import perceptron.*;

public class CSVFileCreator {
	
	static int agentNumber;
	static int stageNumber;
	public static String newline = System.getProperty("line.separator");
	
	public CSVFileCreator(TableauSet ts, int agntNumber, int stgNumber) {
		
		agentNumber = agntNumber;
		stageNumber = stgNumber;
		
		StringBuilder sb = new StringBuilder();

		sb.append("\t\t\t");
		for (int i = 0; i < ts.getConstraints().size(); i++) {
			double weight =(ts.getConstraint(i).getRoundedWeight());
			sb.append(weight + "\t");
		}
		sb.append(newline);
		sb.append("\t\t\t");
		for (int i = 0; i < ts.getConstraints().size(); i++) {
			sb.append(ts.getConstraint(i).getShortName() + "\t");
		}
		sb.append(newline);
		for (int i = 0; i < ts.getTableaux().size(); i++) {
			for (int j = 0; j < ts.getTableau(i).candidateCount(); j++) {
				if (j == 0) {
					sb.append(ts.getTableau(i).getInput().toString() + "\t");
				} else {
					sb.append("\t");
				}
				//System.out.println(sb);
				sb.append(ts.getTableau(i).getCandidate(j).getName() + "\t");
				sb.append(ts.getTableau(i).getCandidate(j).getFrequency() + "\t");
				for (int k = 0; k < ts.getTableau(i).getCandidate(j).getViolationVector().size(); k++) {
					sb.append(ts.getTableau(i).getCandidate(j).getViolation(k) + "\t");
				}
				sb.append(newline);
			}	
		}	
	System.out.println(sb);
	String newFileName = ("SysSimpOutput_agent" + agentNumber + "_stage" + stageNumber + ".");
	newFileName = newFileName.substring(0,newFileName.lastIndexOf(".")) + ".txt";
	File output = new File(newFileName);
	BufferedWriter out;
	try {
		out = new BufferedWriter(new FileWriter(output));
		out.write(sb.toString());
		out.close();
		System.out.println("Output file successfully created.");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
}
