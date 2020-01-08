package Gold;

import java.io.*;
import java.util.*;


public class FruitFeast {

	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2015December\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	private static int T, A, B;
	private static boolean dp[][];

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "feast.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "feast.out"));
		int T = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		
		dp = new boolean[2][T + 1];
		dp[0][0] = true;
		
		for (int f = 1; f <= T; f++) {
			boolean orange = f - A >= 0 && dp[0][f - A];
			boolean lemon = f - B >= 0 && dp[0][f - B];
			dp[0][f] = orange || lemon;
		}
		
		for (int f = 1; f <= T; f++) {
			boolean orange = f - A >= 0 && dp[1][f - A];
			boolean lemon = f - B >= 0 && dp[1][f - B];
			boolean drink = 2 * f <= T && dp[0][2 * f] || 2 * f + 1 <= T && dp[0][2 * f + 1];
			dp[1][f] = orange || lemon || drink;
		}
		
//		System.out.println(Arrays.toString(dp[0]));
//		System.out.println(Arrays.toString(dp[1]));
		
		for (int i = T; i >= 0; i--) {
			if (dp[0][i] || dp[1][i]) {
				out.print(i);
				out.close();
				break;
			}
		}


	}

}
