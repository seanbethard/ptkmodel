package model.systemicSimplicity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PercentageFileCreator {

	public static String newline = System.getProperty("line.separator");
	
	public PercentageFileCreator() {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Main.totalNumberOfCyclesSoFar ; i++) {
			sb.append(Main.output[i] + "\t" + i + newline);
		}
		String newFileName = ("SysSimpOutputGraph" + ".");
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
