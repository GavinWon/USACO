package Bronze;
import java.util.*;
import java.io.*;

public class CowGymnastics {

	private static int N, K;
	private static int[][] input;
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("gymnastics.in"));
		PrintWriter out = new PrintWriter(new File("gymnastics.out"));
		
		//reading input
		K = in.nextInt();
		N = in.nextInt();
		input = new int[K][N + 1];
		for (int i = 0; i < K; i++) {
			for (int j = 1; j <= N; j++) {
				input[i][j] = in.nextInt();
			}
		}
		
		//calculating the number of pairs
		int pairs = 0;
		for (int currentCow = 1; currentCow <= N; currentCow++) {
			for (int comparingCow = currentCow + 1; comparingCow <= N; comparingCow++) {
				ArrayList<Integer> firstCow = new ArrayList<Integer>();
				for (int row = 0; row < K; row++) {
					int[] next = input[row];
					for (int col = 0; col < N; col++) {
						if(next[col] == currentCow || next[col] == comparingCow) {
							firstCow.add(next[col]);
							break;
						}
					}
				}
				
				int cow = firstCow.get(0);
				boolean validPair = true;
				for (int temp : firstCow) {
					if (temp != cow)
						validPair = false;
				}
				if (validPair) {
					pairs++;
					System.out.println(currentCow + " " + comparingCow);
				}
			}
		}
		System.out.println(pairs);
		out.println(pairs);
		out.close();

	}

}
