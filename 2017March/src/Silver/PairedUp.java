package Silver;

import java.util.*;
import java.io.*;

public class PairedUp {
	private static int N;
	private static cowGroup[] input;
	
	public static class cowGroup implements Comparable<cowGroup> {
		int x;
		int y;
		public cowGroup(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public int compareTo(cowGroup o) {
			return y - o.y;
		}
		
		
	}

	public static void main(String[] args) throws Exception {
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2017March\\src\\Silver\\pairup.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2017March\\src\\Silver\\pairup.out"));
		Scanner in = new Scanner(new FileReader("pairup.in"));
		PrintWriter out = new PrintWriter(new File("pairup.out"));
		N = in.nextInt();
		input = new cowGroup[N];
		for (int i = 0; i < N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			input[i] = new cowGroup(x, y);
		}
		Arrays.sort(input);
		int pointer1 = 0;
		int pointer2 = N - 1;
		
		int maxTime = -1;
		
		//Greedy algorithm
		while(pointer1 < pointer2) {
			cowGroup one = input[pointer1];
			cowGroup two = input[pointer2];
			maxTime = Math.max(maxTime, one.y + two.y);
			if (one.x > two.x) {
				one.x -= two.x;
				pointer2--;
			} else if (one.x < two.x) {
				two.x -= one.x;
				pointer1++;
			} else {
				pointer1++;
				pointer2--;
			}
		}
		
		
		out.print(maxTime);
		out.close();
		
		

	}

}
