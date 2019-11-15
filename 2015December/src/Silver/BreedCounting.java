package Silver;

import java.io.*;
import java.util.*;
public class BreedCounting {
	
	private static int N, Q;
	private static int[] count1, count2, count3;
	private static int[][] prefixAB;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("bcount.in"));
		PrintWriter out = new PrintWriter(new File("bcount.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2015December\\src\\Silver\\bcount.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2015December\\src\\Silver\\bcount.out"));
		
		N = in.nextInt();
		Q = in.nextInt();
		count1 = new int[N + 1];
		count2 = new int[N + 1];
		count3 = new int[N + 1];
		prefixAB = new int[Q][2];
		
		for (int i = 1; i <= N; i++) {
			int temp = in.nextInt();
			if (temp == 1) {
				count1[i] = count1[i - 1] + 1;
				count2[i] = count2[i - 1];
				count3[i] = count3[i - 1];
			} else if (temp == 2) {
				count1[i] = count1[i - 1];
				count2[i] = count2[i - 1] + 1;
				count3[i] = count3[i - 1];
			} else if (temp == 3) {
				count1[i] = count1[i - 1];
				count2[i] = count2[i - 1];
				count3[i] = count3[i - 1] + 1;
			}
		}
		
		for (int i = 0; i < Q; i++) {
			for (int j = 0; j < 2; j++) {
				prefixAB[i][j] = in.nextInt();
			}
		}
		
		for (int i = 0; i < Q; i++) {
			int a = prefixAB[i][0];
			int b = prefixAB[i][1];
			out.print(count1[b] - count1[a - 1] + " ");
			out.print(count2[b] - count2[a - 1] + " ");
			out.println(count3[b] - count3[a - 1]);
		}
		
		out.close();
		
		

	}

}
