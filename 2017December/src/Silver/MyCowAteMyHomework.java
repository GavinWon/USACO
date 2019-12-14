package Silver;

import java.util.*;
import java.io.*;

public class MyCowAteMyHomework {
	
	private static int N;
	private static int[] input;
	private static double[] average; //array of two integer values (index0 : average, index1: smallestValue)
	private static ArrayList<Integer> output = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
//		Scanner in = new Scanner(new FileReader("homework.in"));
//		PrintWriter out = new PrintWriter(new File("homework.out"));
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2017December\\src\\Silver\\homework.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2017December\\src\\Silver\\homework.out"));
//		
		//read input
		N = in.nextInt();
		input = new int[N];
		average = new double[N - 1];
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		int runningTotal = input[N - 1];
		int currentLowest = input[N - 1];
		int numbers = 1;
		double maxAverage = Integer.MIN_VALUE;
		
		//calculate prefixSum
		for (int i = N - 2; i >= 0; i--) {
			numbers++;
			currentLowest = Math.min(input[i], currentLowest);
			runningTotal += input[i];
			average[i] = (runningTotal - currentLowest) / (numbers - 1.0);
			if (average[i] > maxAverage) maxAverage = average[i];
			
		}
		for (int i = 0; i < N - 1; i++) {
//			System.out.println(average[i]);
		}
//		System.out.println(maxAverage);
		
		for (int i = 1; i < N - 1; i++) {
			if (average[i] == maxAverage) {
				out.println(i);	
				System.out.println(i);
			}
		}
		
		out.close();
		
		
	}

}
