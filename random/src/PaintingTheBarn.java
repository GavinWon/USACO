import java.util.*;
import java.io.*;

public class PaintingTheBarn {
	
	public static class Rectangles {
		int lowerLeftX, lowerLeftY, upperRightX, upperRightY;
		public Rectangles(int llX, int llY, int urX, int urY) {
			lowerLeftX = llX;
			lowerLeftY= llY;
			upperRightX = urX;
			upperRightY = urY;
		}
	}
	
	private static int N, K;
	private static int[][] grid = new int[1001][1001];
	private static Rectangles[] r;
	
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new FileReader("paintbarn.in"));
		PrintWriter out = new PrintWriter(new File("paintbarn.out"));
//		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/Random/src/paintbarn.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/Random/src/paintbarn.out"));
		int count = 0;
		N = in.nextInt();
		K = in.nextInt();
		
		//reading input
		for (int i = 0; i < N; i++) {
			
			
		}
		
		for (int i = 0; i < N; i++) {

		}
		out.println(count);
		out.close();
//		System.out.println(count);
		
	}

}
