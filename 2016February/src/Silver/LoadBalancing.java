package Silver;

import java.util.*;
import java.io.*;

public class LoadBalancing {
	
	public static class Cow implements Comparable<Cow> {
		private int x, y;
		public Cow (int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(Cow o) {
			return this.y - o.y;
		}
		
	}
	
	private static int N;
	private static Cow[] input;
	private static TreeMap<Integer, Integer> verticleLines = new TreeMap<Integer, Integer>(); //key - the index of the row line (e.g x = 4) value - cows left of this line
	private static int minMax;

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new FileReader("balancing.in"));
		PrintWriter out = new PrintWriter(new File("balancing.out"));
		
		N = in.nextInt();
		input = new Cow[N];
		minMax = N;
		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			input[i] = new Cow(x, y);
			if (!verticleLines.containsKey(x + 1)) {
				verticleLines.put(x + 1, 1);
			} else {
				verticleLines.put(x + 1, verticleLines.get(x + 1) + 1);
			}
			
		}
		Arrays.sort(input);
		int leftCows = 0, rightCows = N;
		for (Map.Entry<Integer, Integer> entry : verticleLines.entrySet()) {
			int vLine = entry.getKey();
			int numCows = entry.getValue();
			leftCows += numCows;
			rightCows = N - leftCows;
			
			int bottomLeft = 0, bottomRight = 0, topLeft = 0, topRight = 0;
			for (int i = 0; i < N; i++) {
				Cow c = input[i];
				if (c.x > vLine) {
					bottomRight++;
				} else {
					bottomLeft++;
				}
				topLeft = leftCows - bottomLeft;
				topRight = rightCows - bottomRight;
				int max = Math.max(Math.max(bottomLeft, topLeft), Math.max(bottomRight, topRight));
				minMax = Math.min(max, minMax);
			}
		}
		
		System.out.println(minMax);
		out.println(minMax);
		out.close();

	}

}
