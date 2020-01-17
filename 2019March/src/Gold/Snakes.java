package Gold;

import java.util.*;
import java.io.*;

public class Snakes {
	
	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2019March\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	private static int dp[][]; //dp[i][k]  --> i = first i snake groups || k = how many times switch ==>> value = minimum net size
	private static int N, K;
	private static int[] input;
	private static int[] prefixMax;
	private static int totalGroupSize = 0;
	
	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "snakes.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "snakes.out"));
		N = in.nextInt();
		K = in.nextInt();
		dp = new int[N + 1][K + 1];
		input = new int[N + 1];
		prefixMax = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			input[i] = in.nextInt();
			prefixMax[i] = Math.max(prefixMax[i - 1], input[i]);
			dp[i][0] = i * prefixMax[i]; //base case
			totalGroupSize += input[i];
//			System.out.print(dp[i][0] + " ");
		}
		
		for (int i = 1; i <= K; i++) {
			dp[1][i] = dp[1][0];
		}
//		System.out.println();
//		
//		System.out.println(Arrays.toString(prefixMax));
		
		
		for (int k = 1; k <= K; k++) {
			for (int n = 2; n <= N; n++) {
				int max = input[n];
				dp[n][k] = Integer.MAX_VALUE;
				for (int j = 1; j <= n - 1; j++) {
					max = Math.max(input[n - j + 1], max);
					dp[n][k] = Math.min(dp[n - j][k - 1] + j * max, dp[n][k]);
				}
//				System.out.print(dp[n][k] + " ");
			}
//			System.out.println();
		}
		
		out.println(dp[N][K] - totalGroupSize);
		out.close();
		
		

	}


}
