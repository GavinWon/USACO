package Bronze;

import java.util.*;
import java.io.*;

public class EscapingTheFarm {
	
	private static int[] input;
	private static int N;
	private static int maxCow = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2011December\\src\\Bronze\\escape.in"));
		PrintWriter out = new PrintWriter(new FileWriter("D:\\Repos\\USACO\\2011December\\src\\Bronze\\escape.out"));
		
		N = in.nextInt();
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		
		addOrContinue(0,0,0);
		
		out.print(maxCow);
		out.close();

	}
	
	public static void addOrContinue(int cowIndex, int numCow, int boatWeight) {
		
		if (cowIndex >= N)
			return;
		
		//add this cow, if you can
		if (noCarry(input[cowIndex], boatWeight)) {
			boatWeight += input[cowIndex];
			numCow++;
			maxCow = Math.max(maxCow, numCow);
			addOrContinue(cowIndex + 1, numCow, boatWeight);
		}
		
		//don't add this cow
		addOrContinue(cowIndex + 1, numCow, boatWeight);
		
	}
	
	public static boolean noCarry(long a, long b) {
		while (a > 0 & b > 0) {
			if (a % 10 + b % 10 >= 10)
				return false;
			a /= 10;
			b /= 10;
		}
		return true;
	}

}
