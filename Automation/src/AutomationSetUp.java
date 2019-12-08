import java.util.*;
import java.io.*;

public class AutomationSetUp {

	public static void solve(String inFile, String outFile) throws IOException {
		Scanner in = new Scanner(new FileReader(inFile));
		PrintWriter out = new PrintWriter(new File(outFile));
	}
	public static void main(String[] args) throws IOException{
		String file = ""; //fileName
		for (int i = 1; i <= 10; i++) {
			System.out.print("Test#" + i + " ... ");
			String inFile = file + i + ".in";
			String myOutFile = file + i + ".myout";
			solve(inFile, myOutFile);
			String correctOutFile = file + i + ".out";
			compare(correctOutFile, myOutFile);
		}
	}
	
	public static void compare (String correctOutFile, String myOutFile) throws IOException {
		Scanner correctOutputScanner = new Scanner(new File(correctOutFile));
		Scanner myOutputScanner = new Scanner(new File(myOutFile));
		boolean passed = true;
		int lineNum = 1;
		while(correctOutputScanner.hasNextLine()) {
			String correctoutputLine = correctOutputScanner.nextLine();
			if(!myOutputScanner.hasNextLine()) {
				System.out.print(" failed at line#" + lineNum);
				System.out.println("...Expected: " + correctoutputLine + ", Yours: missing");
				return;
			} else {
				String myOutputLine = myOutputScanner.nextLine();
				if (!correctoutputLine.equals(myOutputLine)) {
					System.out.print(" failed at line#" + lineNum);
					System.out.println("...Expected: " + correctoutputLine + ", Yours: " + myOutputLine);
					return;
				}
			}
			lineNum++;
		}
		System.out.println(" passed. ");
		
	}

}
