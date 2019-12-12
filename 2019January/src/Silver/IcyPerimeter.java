package Silver;

import java.io.*;
import java.util.*;

public class IcyPerimeter {
	
	private static int N;
	private static char grid[][];
	private static ArrayList<int[]> blobs = new ArrayList<int[]>(); //Format: "(Area) + " " + (Perimeter)"
	private static boolean[][] visitedA;
	private static boolean[][] visitedP;
	
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("perimeter.in"));
		PrintWriter out = new PrintWriter(new File("perimeter.out"));
//		BufferedReader in = new BufferedReader(new FileReader("D:\\Repos\\USACO\\2019January\\src\\Silver\\perimeter.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2019January\\src\\Silver\\perimeter.out"));
		//reading in the input
		N = Integer.parseInt(in.readLine());
		visitedA = new boolean[N][N];
		visitedP = new boolean[N][N];
		grid = new char[N][N];
		for (int i = 0; i < N; i++) {
			String nextLine = in.readLine();
			for (int j = 0; j < N; j++) {
				grid[i][j] = nextLine.charAt(j);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (grid[i][j] == '#' && !visitedA[i][j]) {	
					int area = floodfillArea(i, j);
					System.out.println("Area: " + area);
					if (area == 13) {
						System.out.println("debug");
					}
					int perimeter = floodfillPerimeter(i, j);
					System.out.println("Perimeter: " + perimeter);
					blobs.add(new int[]{area, perimeter});
					
				}
			}
		}
		int areaPrinting = blobs.get(0)[0], periPrinting = blobs.get(0)[1];
		for (int i = 1; i < blobs.size(); i++) {
			if (blobs.get(i)[0] > areaPrinting) {
				areaPrinting = blobs.get(i)[0];
				periPrinting = blobs.get(i)[1];
			}
			if (blobs.get(i)[0] == areaPrinting) periPrinting = Math.min(periPrinting, blobs.get(i)[1]);
		}
		out.println(areaPrinting + " " + periPrinting);
		out.close();
		
		

	}
	
	public static int floodfillArea(int row, int col) {
		Stack<int[]> stack = new Stack<int[]>();
		int count = 0;
		stack.push(new int[]{row, col});
		while (!stack.isEmpty()) {
			int[] coorPair = stack.pop();
//			System.out.println(coorPair[0] + " " + coorPair[1]);
			if (coorPair[0] >= N || coorPair[1] >= N || coorPair[0] < 0 || coorPair[1] < 0) continue;
			if (visitedA[coorPair[0]][coorPair[1]]) continue;
			if (grid[coorPair[0]][coorPair[1]] == '.') continue;
			visitedA[coorPair[0]][coorPair[1]] = true;
			count++;
			stack.push(new int[] {coorPair[0] + 1, coorPair[1]});
			stack.push(new int[] {coorPair[0] - 1, coorPair[1]});
			stack.push(new int[] {coorPair[0], coorPair[1] + 1});
			stack.push(new int[] {coorPair[0], coorPair[1] - 1});
			
			
		}
		return count;
	}
	
	public static int floodfillPerimeter(int row, int col) {
		Stack<int[]> stack = new Stack<int[]>();
		int perimeterCount = 0;
		stack.push(new int[]{row, col});
		while(!stack.isEmpty()) {
			int[] coorPair = stack.pop();
			int r = coorPair[0];
			int c = coorPair[1];
			if (coorPair[0] >= N || coorPair[1] >= N || coorPair[0] < 0 || coorPair[1] < 0) continue;
			if (visitedP[coorPair[0]][coorPair[1]]) continue;
			if (grid[coorPair[0]][coorPair[1]] == '.') continue;
			visitedP[coorPair[0]][coorPair[1]] = true;
			
			if (r + 1 >= N || grid[r + 1][c] == '.')
				perimeterCount++;
			if (r - 1 < 0 || grid[r - 1][c] == '.')
				perimeterCount++;
			if (c + 1 >= N || grid[r][c + 1] == '.')
				perimeterCount++;
			if (c - 1 < 0 || grid[r][c - 1] == '.')
				perimeterCount++;
			
			stack.push(new int[] {coorPair[0] + 1, coorPair[1]});
			stack.push(new int[] {coorPair[0] - 1, coorPair[1]});
			stack.push(new int[] {coorPair[0], coorPair[1] + 1});
			stack.push(new int[] {coorPair[0], coorPair[1] - 1});
			
		}
		
		

		return perimeterCount;
	}

}
