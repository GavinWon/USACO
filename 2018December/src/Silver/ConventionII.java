package Silver;

import java.util.*;
import java.io.*;

public class ConventionII {
	
	public static class Cow implements Comparable<Cow>{
		private int arrivalT;
		private int eatingT;
		private int ranking;
		
		public Cow(int arrival, int eating, int rank) {
			arrivalT = arrival;
			eatingT = eating;
			ranking = rank;
		}
		
		
		public int compareTo(Cow o) {
			return this.ranking - o.ranking; //reverse order, the cows with higher ranking are first, get dequeue first
		}
	}
	
	public static class compareByArrival implements Comparator<Cow> {

		public int compare(Cow o1, Cow o2) {
			return o1.arrivalT - o2.arrivalT; //cow with earlier arrival should be first in array
		}
		
	}
	
	private static int N;
	private static Cow[] cowsByRanking;
	private static Cow[] cowsByArrival;

	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2018December\\src\\Silver\\convention2.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2018December\\src\\Silver\\convention2.out"));
		Scanner in = new Scanner(new FileReader("convention2.in"));
		PrintWriter out = new PrintWriter(new File("convention2.out"));
		
		N = in.nextInt();
		cowsByRanking = new Cow[N];
		cowsByArrival = new Cow[N];
		for (int i = 0; i < N; i++) {
			int arrival = in.nextInt();
			int eating = in.nextInt();
			cowsByRanking[i] = new Cow(arrival, eating, i);
			cowsByArrival[i] = new Cow(arrival, eating, i);
		}
		
//		cowsByArrival = cowsByRanking.clone();
		Arrays.sort(cowsByArrival, new compareByArrival());
		
		
		int currentCow = 0, runningTime = 0;
		int nextCowArrival = 0;
//		boolean cowOnPasture = false;
		int maxWaiting = 0;
		PriorityQueue<Cow> wait = new PriorityQueue<Cow>();
		while (currentCow < N) {
			if (wait.isEmpty()) {
				Cow next = cowsByArrival[nextCowArrival];
				System.out.println(next.arrivalT + " " + next.eatingT + " " + next.ranking);
				runningTime = next.arrivalT + next.eatingT;
				currentCow++;
				nextCowArrival++;
				while(nextCowArrival < N && cowsByArrival[nextCowArrival].arrivalT <= runningTime) {
					wait.add(cowsByArrival[nextCowArrival]);
					nextCowArrival++;
				}
			} else if (!wait.isEmpty()) {
				Cow next = wait.poll();
				System.out.println(next.arrivalT + " " + next.eatingT + " " + next.ranking);
				maxWaiting = Math.max(runningTime - next.arrivalT, maxWaiting);
				System.out.println(maxWaiting);
				runningTime += next.eatingT;
				currentCow++;
				while(nextCowArrival < N && cowsByArrival[nextCowArrival].arrivalT <= runningTime) {
					wait.add(cowsByArrival[nextCowArrival]);
					nextCowArrival++;
				}
			}
		}
		
		System.out.println(maxWaiting);
		out.println(maxWaiting);
		out.close();
		
		

	}

}
