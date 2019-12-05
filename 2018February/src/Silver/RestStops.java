package Silver;

import java.util.*;
import java.io.*;

public class RestStops {
	private static long L, N, farmerS, cowS;
	private static long tastyUnits = 0;
	private static long xPosition = 0;
	private static long farmerRunTime = 0, cowRunTime = 0;
	
	public static class Rests implements Comparable<Rests>{
		int x, tasty;
		public Rests (int x, int tasty) {
			this.x = x;
			this.tasty = tasty;
		}
		public int compareTo(Rests o) {
			return o.tasty - this.tasty;
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("reststops.in"));
		PrintWriter out = new PrintWriter(new File("reststops.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2018February\\src\\Silver\\reststops.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2018February\\src\\Silver\\reststops.out"));
		
		L = in.nextInt();
		N = in.nextInt();
		farmerS = in.nextInt();
		cowS = in.nextInt();
		
		PriorityQueue<Rests> allRests = new PriorityQueue<Rests>();
		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int tasty = in.nextInt();
			Rests r = new Rests(x, tasty);
			allRests.add(r);
		}
		
		while(!allRests.isEmpty()) {
			Rests next = allRests.remove();
			if (next.x > xPosition) {
				farmerRunTime += (next.x - xPosition) * farmerS;
				cowRunTime += (next.x - xPosition) * cowS;
				xPosition = next.x;
				tastyUnits += (farmerRunTime - cowRunTime) * next.tasty;
				cowRunTime = farmerRunTime;
			}
		}
		
		System.out.println(tastyUnits);
		out.println(tastyUnits);
		out.close();
		
		
		

	}

}
