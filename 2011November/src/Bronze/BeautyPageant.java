package Bronze;

import java.util.*;
import java.io.*;

public class BeautyPageant {
	
	private static int rows, cols;
	public static HashSet<String> region1 = new HashSet<String>();
	public static HashSet<String> region2 = new HashSet<String>();
	private static int[][] hide;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("pageant.in"));
//		PrintWriter out = new PrintWriter("pagent.out");
		BufferedReader in = new BufferedReader(new FileReader("D:\\Repos\\USACO\\2011November\\src\\Bronze\\pageant.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2011November\\src\\Bronze\\pageant.out"));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		rows = Integer.parseInt(st.nextToken());
		cols = Integer.parseInt(st.nextToken());
		for (int i = 0; i < rows; i++) {
			String line = in.readLine();
			for (int j = 0; j < cols; j++) {
				if (line.charAt(j) == 'X') {
					String temp = new String(i + " " + j);
					region1.add(temp);
				}
					
			}
		}
		String start = region1.iterator().next();
		dfs(Integer.parseInt(start.split(" ")[0]), Integer.parseInt(start.split(" ")[1]));
		
		int minDistance = Integer.MAX_VALUE;
		for (String p1 : region1) {
			int x1 = Integer.parseInt(p1.split(" ")[0]);
			int y1 = Integer.parseInt(p1.split(" ")[1]);
			for (String p2 : region2) {
				int x2 = Integer.parseInt(p2.split(" ")[0]);
				int y2 = Integer.parseInt(p2.split(" ")[1]);
				int distance = Math.abs(x2 - x1) + Math.abs(y2 - y1);
				if (distance < minDistance) {
					minDistance = distance;
				}
			}
			
		}
		out.println(minDistance - 1);
		out.close();

	}
	
	public static void dfs (int i, int j) {
		String currentSquare = new String(i + " " + j);
		if (!region1.contains(currentSquare))
			return;
		region1.remove(currentSquare);
		region2.add(currentSquare);
		dfs(i + 1, j);
		dfs(i - 1, j);
		dfs(i, j + 1);
		dfs(i, j - 1);
	}
		

}

