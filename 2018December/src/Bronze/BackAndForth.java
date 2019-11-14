package Bronze;

import java.util.*;
import java.io.*;

public class BackAndForth {

	
	
	private static int[] b1 = new int[10];
	private static int emptyb1;
	
	private static int[] b2 = new int[11];
	private static int emptyb2 = -1;
	
	private static int[] bucketsMoved = new int [4];
	private static int[] possibleValues = new int[2001];
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Bronze/backforth.in"));
		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Bronze/backforth.out"));
		
		for (int i = 0; i < 10; i++) {
			b1[i] = in.nextInt();
		}
		
		for (int i = 0; i < 10; i++) {
			b2[i] = in.nextInt();
		}
		tuesday();
		
	}
	
	//going from first barn to second barn
	public static void tuesday() {
		for (int i = 0; i < 10; i++) {
			emptyb1 = i;
			bucketsMoved[0] = b1[emptyb1];
			b2[10] = b1[i];
			wednesday();
		}
	}
	
	//going from second barn to first barn
	public static void wednesday() {
		for (int i = 0; i < 11; i++) {
			emptyb2 = i;
			bucketsMoved[1] = b2[emptyb2];
			b1[emptyb1] = b2[i];
			thursday();
			
		}
	}
	
	//going from first barn to second barn
	public static void thursday() {
		for (int i = 0; i < 10; i++) {
			emptyb1 = i;
			bucketsMoved[2] = b1[emptyb1];
			b2[emptyb2] = b1[emptyb1];
			friday();
		}
	}
	
	public static void friday() {
		for (int i = 0; i < 11; i++) {
			emptyb2 = i;
			bucketsMoved[3] = b2[emptyb2];
			b1[emptyb1] = b2[emptyb2];
			
			int change = bucketsMoved[3] + bucketsMoved[1] - bucketsMoved[2] - bucketsMoved[0];
		}
	}

}
