package Silver;

import java.util.*;
import java.io.*;

public class DiamondCollector {
	
	private static int N, K;
	private static int[] input, count, reversePreFixMax;
	private static int maxTotal = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException{
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2016March\\src\\Silver\\diamond.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2016March\\src\\Silver\\diamond.out"));
		Scanner in = new Scanner(new FileReader("diamond.in"));
		PrintWriter out = new PrintWriter(new File("diamond.out"));
		
		N = in.nextInt();
		K = in.nextInt();
		input = new int[N];
		count = new int[N];
		reversePreFixMax = new int[N];
		
		for (int i = 0; i < N; i++) {
			input[i] = in.nextInt();
		}
		
		Arrays.sort(input);
		
		//Step#1 fill up count
		for (int i = 0; i < N; i++) {
			int endPos = Arrays.binarySearch(input, input[i] + K); //endPos = the index of input[i] + K or -(insertion point) - 1
			if (endPos < 0) { //input[i] + K doesn't exist in input
				endPos = -(endPos + 1);
				count[i] = endPos - i;
			} else { //input[i] + K does exist in the input
				while (endPos + 1 < N && input[endPos] == input[endPos + 1]) {
					endPos++;
				}
				count[i] = endPos - i + 1;
			}
		}
		System.out.print("Count: ");
		for (int i = 0; i < N; i++) {
			System.out.print(count[i] + " ");
		}
		System.out.println();
		
		//Step#2 fill up reversePreFixMax
		reversePreFixMax[N - 1] = count[N - 1];
		for (int index = N - 2; index >= 0; index--) {
			reversePreFixMax[index] = Math.max(reversePreFixMax[index + 1], count[index]);
		}
		System.out.print("prefixMax: ");
		for (int i = 0; i < N; i++) {
			System.out.print(reversePreFixMax[i] + " ");
		}
		System.out.println();
		
		//Step#3 iterate through all possible options for Left Start + count and find the best left count and right count
		for (int leftStart = 0; leftStart < N; leftStart++) {
			int leftCount = count[leftStart];
			int rightStart = leftStart + leftCount; //rightStart is the position of the starting weight for the 2nd case
			int rightCount = 0;
			if (rightStart < N) { //all of the diamonds are in the first case, (left case)
				rightCount = reversePreFixMax[rightStart];
			} else {
				rightCount = 0;
			}
			if (leftCount + rightCount > maxTotal) {
				System.out.println("LS/RS: " + leftStart + " " + rightStart);	
				System.out.println("LC/RC: " + leftCount + " " + rightCount);
				System.out.println();
			}
			maxTotal = Math.max(leftCount + rightCount, maxTotal);
		}
		
		System.out.println(maxTotal);
		out.println(maxTotal);
		out.close();
		
		

	}

}
