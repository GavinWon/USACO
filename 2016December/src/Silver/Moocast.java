package Silver;

import java.util.*;
import java.io.*;

public class Moocast {
	
	private static int N;
	private static int[] x, y, p;
	private static int maxReach = -1;

	public static void main(String[] args) throws IOException{
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2016December\\src\\Silver\\moocast.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2016December\\src\\Silver\\moocast.out"));
		Scanner in = new Scanner(new FileReader("moocast.in"));
		PrintWriter out = new PrintWriter(new File("moocast.out"));
		
		N = in.nextInt();
		x = new int[N];
		y = new int[N];
		p = new int[N];
		for (int index = 0; index < N; index++) {
			x[index] = in.nextInt();
			y[index] = in.nextInt();
			p[index] = in.nextInt();
		}
		for (int i = 0; i < N; i++) {
			boolean[] visited = new boolean[N];
			int reached = floodfill(i, i, visited);
			System.out.println(reached);
			maxReach = Math.max(reached, maxReach);
		}
		
		System.out.println(maxReach);
		out.print(maxReach);
		out.close();
		

	}
	
	public static int floodfill(int original, int curr, boolean[] visited) {
		if (visited[curr] == true || !reachable(original, curr))
			return 0;
		visited[curr] = true;
		int reachedCows = 0;
		for (int nextCow = 0; nextCow < N; nextCow++) {
			if (nextCow == curr) continue;
			reachedCows += floodfill(curr, nextCow, visited);
		}
		return reachedCows + 1;
	}
	
	public static boolean reachable(int fromIndex, int toIndex) {
		int dX = x[fromIndex] - x[toIndex];
		int dY = y[fromIndex] - y[toIndex];
		return Math.sqrt((dX * dX) + (dY * dY)) < p[fromIndex];
	}

}
