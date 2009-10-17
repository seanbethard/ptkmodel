package perceptron;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HelloWorld {
	public static void main(String[] args) {
		System.out.print("test");
		File output = new File("output.txt");

		// Write out the first line of the output file
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			String line = "\tTEST";
			// for(int i = 0; i < ts.getConstraints().size(); i++){
			// line += ts.getConstraint(i) + "/t";
			// }
			out.write(line);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
