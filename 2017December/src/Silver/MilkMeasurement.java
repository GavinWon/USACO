package Silver;

import java.util.*;
import java.io.*;

public class MilkMeasurement {
	
	public static class Measurement implements Comparable<Measurement>{
		int day, cowID, change;
		public Measurement(int day, int cowID, int change) {
			this.day = day; 
			this.cowID = cowID;
			this.change = change;
		}
		public int compareTo(Measurement m) {
			return day - m.day;
		}
	}
	
	private static int N, G;
	private static Measurement[] input;
	private static int[] change;
	//private static HashSet<Integer> cowDisplayed = new HashSet<Integer>(); //adds the cowID if this cowID is getting displayed
	private static Stack<Integer> previousMaxes = new Stack<Integer>();
	private static int maxChange = -1;
	private static int count = 0;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2017December\\src\\Silver\\measurement.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2017December\\src\\Silver\\measurement.out"));
		N = in.nextInt();
		G = in.nextInt();
		input = new Measurement[N];
		change = new int[N + 1];
		for (int i = 0; i < N; i++) {
			input[i] = new Measurement(in.nextInt(), in.nextInt(), in.nextInt());
		}
		Arrays.sort(input);
		
		for (int i = 0; i < N; i++) {
			Measurement next = input[i];
			if (input[i].day == 9) {
				System.out.println("debug");
			}
			change[input[i].cowID] += input[i].change;
			if (change[input[i].cowID] > maxChange) {
				
				maxChange = change[input[i].cowID];
				
				count++;
				System.out.println(input[i].day);
			} else if (change[input[i].cowID] == maxChange) {
				count++;
				System.out.println(input[i].day);
			}
		}
		System.out.println(count);
		out.println(count);
		out.close();

	}

}
