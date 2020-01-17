package Gold;
// 9/10 (10-timeout)


import java.io.*;
import java.util.*;

public class Teamwork {
	
	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Gold/";
	private static int N, K;
	private static int input[];
	private static int dp[];
	private static int[][] maxNumber;
	

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "teamwork.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "teamwork.out"));
		
		N = in.nextInt();
		K = in.nextInt();
		input = new int[N + 1];
		dp = new int[N + 1]; //state[i] --> using the first i elements ||| value[i] the maximum possible sum
		//dp[i] = dp[i - j] + j * max[j, i];
		maxNumber = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			input[i] = in.nextInt();
		}
		dp[1] = input[1]; //base case
		
		for (int i = 2; i <= N; i++) {//loop for state
			for (int j = 1; j <= K; j++) {//loop for j (transition)
				if (i - j < 0) {
					break;
				}
				dp[i] = Math.max(dp[i - j] + j * max(i - j + 1, i), dp[i]);
			}
		}
//		System.out.println(Arrays.toString(input));
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[N]);
		out.print(dp[N]);
		out.close();

	}
	public static int max(int min, int max) {//both parameters are inclusive
		if (maxNumber[min][max] != 0) {
			return maxNumber[min][max];
		} else if (max > min && maxNumber[min][max - 1] != 0) {
			maxNumber[min][max] = Math.max(maxNumber[min][max - 1], input[max]);
		} else if (min < max && maxNumber[min + 1][max] != 0) {
			maxNumber[min][max] = Math.max(maxNumber[min + 1][max], input[max]);
		}
		int maximum = Integer.MIN_VALUE;
		for (int i = min; i <= max; i++) {
			maximum = Math.max(maximum, input[i]);
		}
		maxNumber[min][max] = maximum;
		return maximum;
	}

}
