//package Bronze;

import java.util.*;
import java.io.*;


public class Haywire {
	
	private static int minLength = Integer.MAX_VALUE;
	private static int N;
	private static int positions[];
	private static int[][] friends;
	
	public static void main(String args[]) throws IOException {
		Scanner in = new Scanner(new FileReader("haywire.in"));
		PrintWriter out = new PrintWriter(new File("haywire.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2013April\\src\\Bronze\\haywire.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2013April\\src\\Bronze\\haywire.out"));
		N = in.nextInt();
		positions = new int[N + 1];
		friends = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			friends[i][0] = in.nextInt();
			friends[i][1] = in.nextInt();
			friends[i][2] = in.nextInt();
	}
		
		assign(1, 0, 0, 0);
		
		out.print(minLength);
		System.out.print(minLength);
		out.close();
		
		
	}
	
	public static void assign (int position, int lenSoFar, int upComingLinks, int upComingLen) {
		if (position == N + 1) {
			minLength = Math.min(minLength, lenSoFar);
			return;
		}
		
		for (int cow = 1; cow <= N; cow++) {	
			if (positions[cow] == 0) {
				positions[cow] = position;
				int lenForThisCow = 0, existingFriends = 0, upComingFriends = 0;
				for (int friend : friends[cow]) {
					if (positions[friend] != 0) { //friend is already in
						lenForThisCow += positions[cow] - positions[friend];
						existingFriends++;
						
					} else { //friend is not in yet
						upComingFriends++;
					}
				}
				if (lenSoFar + upComingLen < minLength) assign(position + 1, lenSoFar + lenForThisCow, upComingLinks + upComingFriends - existingFriends,
						upComingLen - lenForThisCow + upComingLinks);
//				System.out.println(Arrays.toString(positions));
				positions[cow] = 0;
			}
			
		}
	}
}
