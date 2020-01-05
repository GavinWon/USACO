package Silver;
import java.util.*;
import java.io.*;
public class MooBuzz {
	
	private static int N;
	private static int predictedNum;
//	private static double ratio = 15.0/8;

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new FileReader("moobuzz.in"));
		PrintWriter out = new PrintWriter(new File("moobuzz.out"));
		
		N = in.nextInt();
		predictedNum = (int)(N * 15.0 / 8) - 1;
		for (int i = 0; i < 100; i++) {
			int testingNumber = predictedNum + i;
			if (testingNumber % 3 == 0 || testingNumber % 5 == 0) continue;
			int mooCount = testingNumber / 3 + testingNumber / 5 - testingNumber / 15;
			if (testingNumber - mooCount == N) {
				out.println(testingNumber);
				break;
			}
		}
		
		
		out.close();

	}

}
