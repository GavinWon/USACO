import java.util.*;
@SuppressWarnings("unchecked")
public class CowDigitGame {
	
	private static int G;
	private static int[] dp;

	public static void main(String[] args) throws Exception{
		Scanner in = new Scanner(System.in);
		G = in.nextInt();
		dp = new int[1000001];
		for (int i = 1; i <= 9; i++) {
			dp[i] = 1;
		}
		
		for (int number = 10; number <= 100000; number++) {
			String temp = Integer.toString(number);
			dp[number] = 0;
			int[] digits = new int[temp.length()];
			
			int min = digits[0];
			int max = digits[0];
			for (int i = 1; i < temp.length(); i++)
			{
				digits[i] = temp.charAt(i) - '0';
				min = Math.min(min, digits[i]);
				max = Math.max(max, digits[i]);
			    
			}
			if (min != 0 && dp[number - min] == 0 || max != min && dp[number - max] == 0) {
				dp[number] = 1;
			} else {
				dp[number] = 0;
			}
//			if (number == 515) {
//				System.out.println(min);
//				System.out.println(max);
//			}

		}
//		System.out.println(dp[10]);
//		System.out.println(dp[510]);
//		System.out.println(dp[520]);
//		System.out.println(dp[530]);
//		System.out.println(dp[540]);
//		System.out.println(dp[550]);
//		System.out.println(dp[560]);
		for (int i = 1; i <= G; i++) {
			int nextNum = in.nextInt();
			if (dp[nextNum] == 1) {
				System.out.println("YES");
			}
			else  {
				System.out.println("NO");
			}
		}
		
		

	}

}
