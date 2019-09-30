import java.util.*;
import java.io.*;
public class CowArt{
	
	static char[][] array;
	static boolean[][] visited;
	static int N;
	
	public static void main(String args[]) throws IOException  {
//		BufferedReader f = 	new BufferedReader(new FileReader("/Users/Gavin/Dropbox/USACO/March2014/src/cowart.in"));
//		PrintWriter out = new PrintWriter((new FileWriter("/Users/Gavin/Dropbox/USACO/March2014/src/cowart.out")));
		BufferedReader f = new BufferedReader(new FileReader("cowart.in"));
		PrintWriter out = new PrintWriter((new FileWriter("cowart.out")));
		N = Integer.parseInt(f.readLine());
		array = new char[N][N];
		for (int i = 0; i < N;i++) {
			array[i] = f.readLine().toCharArray();
		}
		
		int human = viewAs("Human");
		int cow = viewAs("Cow");
		
		out.println(human + " " + cow);
		out.close();
		
	}
	
	public static int viewAs(String viewer) {
		visited = new boolean[N][N];
		int count = 0;
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count++;
					dfs(array[i][j], i, j, viewer);
				}
			}
		}
		return count;
	}
	
	public static void dfs(char expectedColor, int row, int col, String viewer) {
		if (row < 0 || col < 0 || row >= N || col >= N) {
			return;
		}
		if (visited[row][col])
			return;
		char currColor = array[row][col];
		boolean sameColor;
		if (viewer.equals("Human"))
		{
			sameColor = currColor == expectedColor;
		} else {
			sameColor = (currColor == expectedColor) || (expectedColor == 'G' && currColor == 'R') || (expectedColor == 'R' && currColor == 'G');
		}
		
		if (sameColor) {
			visited[row][col] = true;
			dfs(expectedColor, row - 1, col, viewer);
			dfs(expectedColor, row + 1, col, viewer);
			dfs(expectedColor, row, col - 1, viewer);
			dfs(expectedColor, row, col + 1, viewer);
		}
	}
}
