package Silver;

import java.util.*;
import java.io.*;

public class Teleportation {

	private static int N;
	private static TreeMap<Integer, Integer> slopeChange;
	private static long totalDistanceWithNoSaving = 0;
	
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner( new FileReader("teleport.in"));
		PrintWriter out = new PrintWriter(new File("teleport.out"));
//		Scanner in = new Scanner( new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2018February/src/Silver/teleport.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2018February/src/Silver/teleport.out"));
		
		N = in.nextInt();
		slopeChange = new TreeMap<Integer,Integer>();
		for (int i = 0; i < N; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			totalDistanceWithNoSaving += Math.abs(a -b);
			int maxSaving = Math.abs(a - b) - Math.abs(a);
			if (maxSaving <= 0)
				continue;
			int minY = b - maxSaving;
			int maxY = b + maxSaving;
			
			if (slopeChange.containsKey(b)) {
				slopeChange.put(b, slopeChange.get(b) - 2);
			} else {
				slopeChange.put(b, -2);
			}
			
			if (slopeChange.containsKey(minY)) {
				slopeChange.put(minY, slopeChange.get(minY) + 1);
			} else {
				slopeChange.put(minY , 1);
			}
			
			if (slopeChange.containsKey(maxY)) {
				slopeChange.put(maxY, slopeChange.get(maxY) + 1);
			} else {
				slopeChange.put(maxY, 1);
			}
		}
		
		int slope = 0, last_y = slopeChange.firstKey();
		long saving = 0, maxSaving = 0;
		for (Map.Entry<Integer,Integer> temp : slopeChange.entrySet()) {
			int this_y = temp.getKey();
			saving += slope * (this_y - last_y);
			maxSaving = Math.max(saving, maxSaving);
			last_y = this_y;
			slope += temp.getValue();
		}
		
		System.out.println(totalDistanceWithNoSaving - maxSaving);
		out.print(totalDistanceWithNoSaving - maxSaving);
		out.close();
	}

}
