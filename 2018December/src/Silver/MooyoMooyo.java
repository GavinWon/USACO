package Silver;

import java.util.*;
import java.io.*;

public class MooyoMooyo {
	
	private static int N, K;
	private static int[][] grid;
	private static boolean gridChanged;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("mooyomooyo.in"));
//		PrintWriter out = new PrintWriter(new File("mooyomooyo.out"));
		BufferedReader in = new BufferedReader(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Silver/mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Silver/mooyomooyo.out"));
		
		StringTokenizer firstLine = new StringTokenizer(in.readLine());
		N = Integer.parseInt(firstLine.nextToken());
		K = Integer.parseInt(firstLine.nextToken());
		
		grid = new int[N][10];
		
		for (int i = 0; i < N; i++) {
			String nextLine = in.readLine();
			for (int j = 0; j < 10; j++) {
				grid[i][j] = Character.getNumericValue(nextLine.charAt(j));
			}
		}
		
//		printGrid();
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < 10; j++) {
//				System.out.print(grid[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		do{
			gridChanged = false;
			reformGrid();
			for (int row = N - 1; row >= 0; row--) {
				for (int col = 0; col < 10; col++) {
					if (!(grid[row][col] == 0)) { 
						boolean[][] visited = new boolean[N][10];
						boolean delete = floodfillcheck(row, col, grid[row][col], 1, visited);
//						System.out.println("Color: " + grid[row][col] + " Row/Col: " + row + "/" + col);
						boolean[][] visited1 = new boolean[N][10];
						if (delete) { 
							floodfilldelete(row, col, grid[row][col], visited1);
							gridChanged = true;
						}
//						printGrid();
					}
				}
			}
//			reformGrid();
		} while (gridChanged);
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
//				System.out.print(grid[i][j]);
				out.print(grid[i][j]);
			}
			out.println();
//			System.out.println();
		}
		out.close();

	}
	
	public static void printGrid (){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------");
	}
	public static boolean floodfillcheck(int row, int  col, int color, int count, boolean[][] visited) {
		if (row == N || row < 0 || col == 10 || col < 0) return false;
		if (grid[row][col] != color) return false;
		if (visited[row][col]) return false;
		visited[row][col] = true;
		if (count == K) return true;
		
		return 
		floodfillcheck(row + 1, col, color, count + 1, visited) ||
		floodfillcheck(row - 1, col, color, count + 1, visited) ||
		floodfillcheck(row, col + 1, color, count + 1,visited) ||
		floodfillcheck(row, col - 1, color, count + 1, visited);
		
	}
	
	public static void floodfilldelete(int row, int col, int color, boolean[][] visited) {
		if (row >= N || row < 0 || col >= 10 || col < 0) return;
		if (grid[row][col] != color) return;
		if (visited[row][col]) return;
		visited[row][col] = true;
		grid[row][col] = 0;
		floodfilldelete(row + 1, col, color, visited);
		floodfilldelete(row - 1, col, color, visited);
		floodfilldelete(row, col + 1, color, visited);
		floodfilldelete(row, col - 1, color, visited);
	}
	
	public static void reformGrid() {
		for (int col = 0; col < 10; col++) {
			Queue<Integer> zero = new LinkedList<Integer>();
			for (int row = N - 1; row >= 0; row--) {
				if (grid[row][col] == 0) zero.add(row);
				else {
					if (!zero.isEmpty()) {
						int r = zero.poll();
						grid[r][col] = grid[row][col];
						grid[row][col] = 0;
						zero.add(row);
					}
				}
			}
		}
	}

}
