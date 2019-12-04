package Silver;

import java.util.*;
import java.io.*;

public class DiamondCollector {
	
	private static int N, K;
	private static int[] input1, input2;
	private static int[] diamondsNearSize;
	private static int size1 = Integer.MIN_VALUE, size2 = Integer.MIN_VALUE;
	private static int case1 = Integer.MIN_VALUE, case2 = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2016March/src/Silver/diamond.in"));
		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2016March/src/Silver/diamond.out"));
		
		N = in.nextInt();
		K = in.nextInt();
		input1 = new int[N];
		input2 = new int[50001];
		diamondsNearSize = new int[50001];
		
		for (int i = 0; i < N; i++) {
			int temp = in.nextInt();
			input1[i] = temp;
			input2[temp] += 1;
		}
		
		Arrays.sort(input1);
		
		
		for (int i = 0; i < N; i++) {
			if (i > 0 && input1[i] == input1[i - 1]) continue;
			int surroundings = numOfSurrDiamonds(input1[i]);
			diamondsNearSize[input1[i]] = surroundings;
		}
		
		for (int i = 0; i < N; i++) {
			int size = input1[i];
			if (input2[size] + diamondsNearSize[size] > case1) {
					if (Math.abs(size2 - size) > K) {
						case2 = case1;
						size2 = size1;
					}
					case1 = input2[size]  + diamondsNearSize[size];
					size1 = size;
			} else if (input2[size] + diamondsNearSize[size] > case2) {
				if (Math.abs(size1 - size) > K) {
					case2 = input2[size] + diamondsNearSize[size];
					size2 = size;
				}
			}
		}
		System.out.println(size1);
		System.out.println(size2);
		System.out.println(case1);
		System.out.println(case2);
		out.println(case1 + case2);
		out.close();

	}
	
	public static int numOfSurrDiamonds(int size) {
		int count = 0;
		
		for (int add = -K; add <= K; add++) {
			if (size + add > 1000000000 || size + add < 1 || add == 0) continue;
			count += input2[size + add];
		}
		System.out.println(size + " " + count);
		return count;
	}

}
