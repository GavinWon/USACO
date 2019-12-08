package Silver;

import java.io.*;
import java.util.*;
public class Convention {
	
	private static int N, M, C;
	private static int[] input;
	private static long minWaiting = Integer.MIN_VALUE;

	
	public static boolean attempt(int time) {
//		if (time == 3) {
//			System.out.println("debug");
//		}
		ArrayList<Integer> inBus = new ArrayList<Integer>();
		int busesSent = 0;
		for (int i = 0; i < N; i++) {
			inBus.add(input[i]);
			if ((i + 1 < N && input[i + 1] - inBus.get(0) > time) || inBus.size() == C) {
				busesSent++;
				inBus = new ArrayList<Integer>();
			}
			if(busesSent > M || busesSent == M && !inBus.isEmpty()) return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new FileReader("convention.in"));
		PrintWriter out = new PrintWriter(new File("convention.out"));;
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
	
	


}
