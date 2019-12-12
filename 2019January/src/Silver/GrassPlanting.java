package Silver;

import java.util.*;
import java.io.*;
public class GrassPlanting {
	
	private static int N;
	private static int degreeForNode[];

	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new File("planting.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2019January\\src\\Silver\\planting.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2019January\\src\\Silver\\planting.out"));
		N = in.nextInt();
		degreeForNode = new int[N + 1];
		for (int i = 1; i < N; i++) {
			int first = in.nextInt();
			int second = in.nextInt();
			degreeForNode[first]++;
			degreeForNode[second]++;
		}
		
		int maxDegree = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			maxDegree = Math.max(degreeForNode[i], maxDegree);
		}
		
		out.println(maxDegree + 1);
		System.out.println(maxDegree + 1);
		out.close();
		

	}

}
