package Silver;
import java.util.*;
import java.io.*;

public class dumb {

	static int N, M, K;
	static int[][] transition;
	static int[] One;
	static int[] actual;
	static int[][] allTransitions; 

	static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader(new File("swap.in")));
		PrintWriter out = new PrintWriter(new File("swap.out"));
		
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		transition = new int[M][2];
		
		for (int i = 0; i < M; i++) {
			transition[i][0] = in.nextInt();
			transition[i][1] = in.nextInt();
		}
		
		One = new int[N + 1];
		actual = new int[N + 1];
		allTransitions = new int[32][N + 1];
		for (int i = 1; i <= N; i++) {
			One[i] = i;
			actual[i] = i;
		}
		
		firstFill();
		secondFill();
		completeFill();
		
		
		String bits = Integer.toBinaryString(K);
		int p = bits.length() - 1;
		
		for (int i = 0; i < bits.length(); i++)
		{
			int[] new1 = new int[N + 1];
			int num = Character.getNumericValue(bits.charAt(i));
			if (num == 0)
			{
				p -= 1;
				continue;
			} 
			else
			{
				for (int j = 1; j <= N; j++) {
					int newLocation = allTransitions[p][j];
					new1[newLocation] = actual[j];
				}
				p--;
			}
			actual = new1;
		}
		for (int i = 1; i <= N; i++)
		{
			System.out.println(actual[i]);
		}
		out.close();
	}
	
	 static void firstFill() {
		for (int i = 0; i < M; i++) {
			
			int Left = transition[i][0];
			int Right = transition[i][1];
			
			while (Left < Right) {
				int temp = One[Left];
				One[Left] = One[Right];
				One[Right] = temp;
				Left++;
				Right--;
			}
		}
		for (int i = 1; i <= N; i++) {
			allTransitions[0][One[i]] = i;
		}
	}
	
	static void secondFill() {
		for (int i = 1; i <= N; i++) {
			allTransitions[1][i] = allTransitions[0][allTransitions[0][i]];
		}
	}
	
	static void completeFill() {
		for (int i = 2; i <= 31; i++) {
			for (int j = 1; j <= N; j++) {
				allTransitions[i][j] = allTransitions[i - 1][allTransitions[i - 1][j]];
			}
			
		}
	}
	
	
}
