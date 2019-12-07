package Silver;

import java.io.*;
import java.util.*;
public class Convention {
	
	private static int N, M, C;
	private static int[] input;
	private static long minWaiting = Integer.MIN_VALUE;
	
	public static void solve(String inFile, String outFile) throws IOException {
		Scanner in = new Scanner(new FileReader(inFile));
		PrintWriter out = new PrintWriter(new File(outFile));
//		Scanner in = new Scanner(new FileReader("convention.in"));
//		PrintWriter out = new PrintWriter(new File("convention.out"));
		
		N = in.nextInt();
		M = in.nextInt();
		C = in.nextInt();
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		
		Arrays.sort(input);
		int start = 0;
		int end = 1000000000;
		while(start <= end) {
			int time = (start + end)/2;
			boolean possible = attempt(time);
			if (possible) {
				minWaiting = time;
				end = time - 1;
			} else {
				start = time + 1;
			}
		}
		out.println(minWaiting);
		out.close();
	}
	
	public static boolean attempt(int time) {
//		System.out.println(time);
//		if (time == 6) {
//		}
		ArrayList<Integer> inBus = new ArrayList<Integer>();
		int busesSent = 0;
		for (int i = 0; i < N; i++) {
			inBus.add(input[i]);
			if ((i + 1 < N && input[i + 1] - inBus.get(0) > time) || inBus.size() == C) {
				busesSent++;
				inBus = new ArrayList<Integer>();
			}
			if(busesSent > M) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		//Example: D:\Repos\USACO\2018December\src\Silver\convention_silver_dec18\1.in
		String file = "D:\\Repos\\USACO\\2018December\\src\\Silver\\convention_silver_dec18\\";
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
