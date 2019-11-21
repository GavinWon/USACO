package Silver;

import java.util.*;
import java.io.*;

public class MilkScheduling {
	
	private static int N;
	private static cow[] input;
	private static int finish = -1;
	private static int total = 0;
	
	public static class cow  implements Comparable<cow> {
		int prod;
		int deadline;
		public cow (int prod, int deadline) {
			this.prod = prod;
			this.deadline = deadline;
		}
		public int compareTo(cow o) {
			return o.prod - prod; //reverse order so the cow with higher production has priority
		}
	}
	
	public static class compareByDeadline implements Comparator<cow> {

		public int compare(cow o, cow o1) {
			return o1.deadline - o.deadline; //reverse order so the cow with bigger deadline is first
		}
		
	}

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(new FileReader("msched.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msched.out")));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2013December\\src\\Silver\\msched.in"));
//		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\Repos\\USACO\\2013December\\src\\Silver\\msched.out")));
		
		N = in.nextInt();
		input = new cow[N];
		
		for (int i = 0; i < N; i++) {
			int prod = in.nextInt();
			int deadline = in.nextInt();
			finish = Math.max(finish, deadline);
			input[i] = new cow(prod, deadline);
		}
		
		Arrays.sort(input, new compareByDeadline());
		int pointer = 0;
		
		PriorityQueue<cow> q = new PriorityQueue<cow>();
		int clock = finish;
		
		
		//enqueue and dequeue process
		while(clock >= 1) {
			while(pointer < N && input[pointer].deadline >= clock) {
				q.add(input[pointer]);
				pointer++;
			}
			int nextCowProd;
			if (!q.isEmpty()) {
				nextCowProd = q.remove().prod;
				total += nextCowProd;
			}
			clock--;
			
		}
		
		out.print(total);
		out.close();
		

	}

}
