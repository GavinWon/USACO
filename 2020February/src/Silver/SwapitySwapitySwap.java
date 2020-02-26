package Silver;
import java.util.*;
import java.io.*;

public class SwapitySwapitySwap {

	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2020February/src/Silver/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	private static int N, M, K;
	private static int[][] process;
	private static int[] actualOne;
	private static int[] actual;
	private static int[][] transformation; //row = the power (example one trans 
	//or two trans

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "swap.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "swap.out"));
		
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		process = new int[M][2];
		
		for (int i = 0; i < M; i++) {
			process[i][0] = in.nextInt();
			process[i][1] = in.nextInt();
		}
		
		actualOne = new int[N + 1];
		actual = new int[N + 1];
		transformation = new int[32][N + 1];
		for (int i = 1; i <= N; i++) {
			actualOne[i] = i;
			actual[i] = i;
		}
		fillOne();
		fillTwo();
		completeTransformation();
//		System.out.println(Arrays.toString(actualOne));
//		System.out.println(Arrays.toString(oneTrans));
//		System.out.println(Arrays.toString(twoTrans));
//		System.out.println("----------------------------");
//		for (int i = 0; i < 2; i++) {
//			System.out.println(Arrays.toString(transformation[i]));
//		}
		
		
		String bits = Integer.toBinaryString(K);
		int power = bits.length() - 1;
		for (int i = 0; i < bits.length(); i++) {
			int[] newactualOne = new int[N + 1];
			int num = Character.getNumericValue(bits.charAt(i));
			if (num == 0) {
				power--;
				continue;
			} else {
				for (int j = 1; j <= N; j++) {
					int newLocation = transformation[power][j];
					newactualOne[newLocation] = actual[j];
				}
				power--;
			}
			actual = newactualOne;
		}
		for (int i = 1; i <= N; i++) {
			System.out.println(actual[i]);
		}
		out.close();
	}
	
	public static void fillOne() {
		for (int i = 0; i < M; i++) {
			int L = process[i][0];
			int R = process[i][1];
			while (L < R) {
				int temp = actualOne[L];
				actualOne[L] = actualOne[R];
				actualOne[R] = temp;
				L++;
				R--;
			}
		}
		for (int i = 1; i <= N; i++) {
			transformation[0][actualOne[i]] = i;
		}
	}
	
	public static void fillTwo() {
		for (int i = 1; i <= N; i++) {
			transformation[1][i] = transformation[0][transformation[0][i]];
		}
	}
	
	public static void completeTransformation() {
		for (int i = 2; i <= 31; i++) {
			for (int j = 1; j <= N; j++) {
				transformation[i][j] = transformation[i - 1][transformation[i - 1][j]];
			}
			
		}
	}
	
	
}
