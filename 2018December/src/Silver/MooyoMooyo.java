package Silver;

import java.util.*;
import java.io.*;

public class MooyoMooyo {
	
	private static int N, K;
	private static int[][] grid;
	private static boolean gridChanged;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("D:\\Repos\\USACO\\2018December\\src\\Silver\\mooyomooyo.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2018December\\src\\Silver\\mooyomooyo.out"));
		
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
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		
		do{
			gridChanged = false;
			for (int row = N - 1; row >= 0; row--) {
				for (int col = 0; col < 10; col++) {
					if (!(grid[row][col] == 0)) { 
						boolean delete = floodfillcheck(row, col, grid[row][col], 0);
						System.out.println("Color: " + grid[row][col] + " Row/Col: " + row + "/" + col);
						if (delete) floodfilldelete(row, col, grid[row][col]);
					}
				}
			}
			reformGrid();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < 10; j++) {
					System.out.print(grid[i][j]);
				}
				System.out.println();
			}
			System.out.println("-------------------------");
		} while (gridChanged);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		
		out.close();

	}
	public static boolean floodfillcheck(int row, int  col, int color, int count) {
		if (row >= N || row < 0 || col >= 10 || col < 0) return false;
		if (grid[row][col] != color) return false;
		if (count == K) return true;
		return floodfillcheck(row + 1, col, color, count + 1) ||
		floodfillcheck(row - 1, col, color, count + 1) ||
		floodfillcheck(row, col + 1, color, count + 1) ||
		floodfillcheck(row, col - 1, color, count + 1);
		
	}
	
	public static void floodfilldelete(int row, int col, int color) {
		if (row >= N || row < 0 || col >= 10 || col < 0) return;
		if (grid[row][col] != color) return;
		grid[row][col] = 0;
		gridChanged = true;
		floodfilldelete(row + 1, col, color);
		floodfilldelete(row - 1, col, color);
		floodfilldelete(row, col + 1, color);
		floodfilldelete(row, col - 1, color);
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
