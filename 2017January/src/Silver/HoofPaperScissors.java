package Silver;

import java.util.*;
import java.io.*;

public class HoofPaperScissors {
	
	private static int[] h;
	private static int[] p;
	private static int[] s;
	private static int[][] hps;
	private static int maxGamesWon = -1;
	private static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("hps.in"));
		PrintWriter out = new PrintWriter(new File("hps.out"));
//		BufferedReader in = new BufferedReader(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2017January/src/Silver/hps.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2017January/src/Silver/hps.out"));
		
		N = Integer.parseInt(in.readLine());
		hps = new int[3][N + 1];
		h = new int[N + 1];
		p = new int[N + 1];
		s = new int[N + 1];
		
		for (int i =1; i <= N; i++) {
			char c = in.readLine().charAt(0);
			if (c == 'S') {
				hps[0][i] = hps[0][i - 1] + 1;
				hps[1][i] = hps[1][i - 1];
				hps[2][i] = hps[2][i - 1];
			}else if (c == 'H') {
				hps[0][i] = hps[0][i - 1];
				hps[1][i] = hps[1][i - 1] + 1;
				hps[2][i] = hps[2][i - 1];
			}else if (c == 'P') {
				hps[0][i] = hps[0][i - 1];
				hps[1][i] = hps[1][i - 1];
				hps[2][i] = hps[2][i - 1] + 1;
			}
		}
		
		
			//going through all 9 possible options
			calculateMaxGamesWon(0, 0); 
			calculateMaxGamesWon(0 ,1); 
			calculateMaxGamesWon(0, 2); 
			calculateMaxGamesWon(1, 0); 
			calculateMaxGamesWon(1, 1); 
			calculateMaxGamesWon(1, 2); 
			calculateMaxGamesWon(2, 0); 
			calculateMaxGamesWon(2, 1); 
			calculateMaxGamesWon(2, 2); 
		
		
		out.print(maxGamesWon);
		System.out.print(maxGamesWon);
		out.close();
	}
	
	
	//0 - Hooves, 1 - Paper, 2 - Scissors
	public static void calculateMaxGamesWon(int type1, int type2) {
		int max = -1;
		for (int i = 1; i <= N; i++) {
			int win1 = hps[type1][i];
			int win2 = hps[type2][N] - hps[type2][i];
			max = Integer.max(max, win1 + win2);
		}
		
		maxGamesWon = Integer.max(max, maxGamesWon);
		
	}
}
