package perceptron;

public class PerceptronTest {

	public static void main(String[] args) {
		// File input.
		String OTSoftFileName = "C:/Python24/StressTyp-Rhythm-trochees-25may-full.txt";
		if (args != null && args.length > 0)
			OTSoftFileName = args[0];

		Perceptron p = new Perceptron(OTSoftFileName);
		p.perceptronSolve();
		System.out.println("test");
	}

}
