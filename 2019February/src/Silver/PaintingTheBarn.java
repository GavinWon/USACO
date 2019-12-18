package Silver;

import java.util.*;
import java.io.*;

public class PaintingTheBarn {
	
	
	private static int N, K;
	private static int[][] grid = new int[1001][1001];
	
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new FileReader("paintbarn.in"));
		PrintWriter out = new PrintWriter(new File("paintbarn.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2019February\\src\\Silver\\paintbarn.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2019February\\src\\Silver\\paintbarn.out"));
		int count = 0;
		N = in.nextInt();
		K = in.nextInt();
		
		//reading input
		for (int i = 0; i < N; i++) {
			int lowerLeftX = in.nextInt();
			int lowerLeftY = in.nextInt();
			int upperRightX = in.nextInt();
			int upperRightY = in.nextInt();
			for (int y = lowerLeftY + 1; y <= upperRightY; y++) {
				grid[y][lowerLeftX] += 1;
				grid[y][upperRightX] -= 1;
//				System.out.println(grid[lowerLeftX][y]);
			}
			
		}
		
		for (int i = 0; i < 1000; i++) {
			int[] next = grid[i];
			int[] prefixSum = new int[1000];
			for (int j = 0; j < 1000; j++) {
				if (j == 0) prefixSum[j] = next[j];
				else {
					prefixSum[j] = prefixSum[j - 1] + next[j];
					if (prefixSum[j] == K) count++;
				}
			}
			
		}
		System.out.println(count);
		out.println(count);
		out.close();
//		System.out.println(count);
		
	}

}
