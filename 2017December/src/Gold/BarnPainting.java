package Gold;

import java.io.*;
import java.util.*;

public class BarnPainting {
	
	
	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2017December\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	private static int N, K;
	private static LinkedList<Integer>[] adjacencyList;
	private static long dp[][];
	private static boolean visited[][];
	private static HashMap<Integer,Integer> painted = new HashMap<Integer,Integer>(); //which barns have already been painted

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "barnpainting.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "barnpainting.out"));
		
		N = in.nextInt();
		K = in.nextInt();
		dp = new long[N + 1][4];
		visited = new boolean[N + 1][4];
		adjacencyList = new LinkedList[N + 1];
		
		for (int i = 1; i <= N - 1; i++) {
			
			int barn1 = in.nextInt();
			int barn2 = in.nextInt();
			
			if (adjacencyList[barn1] == null) {
				adjacencyList[barn1] = new LinkedList<Integer>();
			}
			if (adjacencyList[barn2] == null) {
				adjacencyList[barn2] = new LinkedList<Integer>();
			}
			
			adjacencyList[barn1].add(barn2);
			adjacencyList[barn2].add(barn1);
		}
		
		for (int i = 1; i <= K; i++) {
			int barn = in.nextInt();
			int color = in.nextInt();
			painted.put(barn,color);
		}
		
		//set 1 as the root
		if (painted.containsKey(1)) {
			long p = dp(1, painted.get(1), 0);
			out.println(p % 1000000007);
		} else {
			long total = 0;
			total += dp(1, 1, 0);
			total += dp(1, 2, 0);
			total += dp(1, 3, 0); 
			System.out.println(total % 1000000007);
		}
		out.close();
		
		

	}
	
	public static long dp(int barn, int color, int parent) { //dp[barn][color]
		if (visited[barn][color]) return dp[barn][color];
		
		visited[barn][color] = true;
		if (painted.containsKey(barn)) { //this barn is already prepainted
			if (painted.get(barn) != color) {
				dp[barn][color] = 0;
				return dp[barn][color];
			}
			dp[barn][color] = 1;
			for (int connections : adjacencyList[barn]) {
				if (connections == parent) continue;
				long sum = 0;
				for (int c = 1; c <= 3; c++) {
					if (c != color) {
						sum += dp(connections, c, barn);
					}
				}
				if (sum != 0) {
					dp[barn][color] *= sum; 
					dp[barn][color] %= 1000000007; 
				}
			}
				
		} else {
			dp[barn][color] = 1;
			for (int connections : adjacencyList[barn]) {
				if (painted.containsKey(connections) && painted.get(connections) == color) {
					dp[barn][color] = 0;
					return dp[barn][color];
				}
				if (connections == parent) continue;
				long sum = 0;
				for (int c = 1; c <= 3; c++) {
					if (c != color) sum += dp(connections, c, barn);
				}
				if (sum != 0) {
					dp[barn][color] *= sum;
					dp[barn][color] %= 1000000007; 
				}
			}
		}
		dp[barn][color] %= 1000000007;
		
		if (dp[barn][color] == 0) {
			dp[barn][color] = 1;
		}
		return dp[barn][color];
	}

}