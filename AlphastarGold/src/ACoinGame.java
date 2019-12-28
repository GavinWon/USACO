import java.util.*;
@SuppressWarnings("unchecked")
public class ACoinGame {
	
	private static int N;
	private static int[][] dp;
	private static int[] coinSum;
	private static int[] coins;
	private static int totalValue = 0;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		coinSum = new int[N + 1];
		coins = new int[N + 1];
		dp = new int[N + 1][N + 1]; //print out dp[N][1]
		for (int i = 0; i < N; i++) {
			dp[0][i + 1] = 0;
			coins[i + 1] = in.nextInt();
			totalValue += coins[i + 1];
		}
		coinSum[N] = totalValue;
		for (int i = N - 1; i > 0; i--) {
			coinSum[i] = coinSum[i + 1] - coins[i + 1]; //prefix sum
		}
		
		System.out.println(Arrays.toString(coinSum));
		
		for (int c = 1; c <= N; c++) {
			for (int p = 1; p <= N; p++) {
				int temp = dp[c][p - 1];
				if (2 * p > c) {
					break;
				}
				System.out.println(c + " " + p);
				dp[c][p] = Math.max(coinSum[c] - dp[c - (2 * p - 1)][2 * p - 1], coinSum[c] - dp[c - 2 * p][2 * p]); //fix when index turns negative
				dp[c][p] = Math.max(dp[c][p], temp);
			}
		}
		
		System.out.println(dp[N][1]);
		

	}

}
