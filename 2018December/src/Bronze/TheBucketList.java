package Bronze;

import java.util.*;
import java.io.*;
public class TheBucketList {
	
	public static int notInUse;
	private static int inUse;
	
	private static int N;
	private static HashMap<Integer,Integer> endTimeMapBuckets;
	private static HashMap<Integer, Integer> startEndTime;
	private static HashSet<Integer> startingTimes;
	private static HashSet<Integer> endingTimes;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("blist.in"));
		PrintWriter out = new PrintWriter(new File("blist.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2018December\\src\\Bronze\\blist.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2018December\\src\\Bronze\\blist.out"));
		
		N = in.nextInt();
		endTimeMapBuckets = new HashMap<Integer, Integer>();
		startEndTime = new HashMap<Integer, Integer>();
		startingTimes = new HashSet<Integer>();
		endingTimes = new HashSet<Integer>();
		
		for (int i = 0; i < N; i++) {
			int s = in.nextInt();
			int t = in.nextInt();
			int b = in.nextInt();
			
			startEndTime.put(s, t);
			endTimeMapBuckets.put(t, b);
			startingTimes.add(s);
			endingTimes.add(t);
		}
		int clock = 0;
		while (endingTimes.size() > 0) {
			if (startingTimes.contains(clock)) {
				int endTime = startEndTime.get(clock);
				inUse += endTimeMapBuckets.get(endTime);
				if (notInUse > endTimeMapBuckets.get(endTime)) {
					notInUse -= endTimeMapBuckets.get(endTime);
				} else {
					notInUse = 0;
				}
			}
			if (endingTimes.contains(clock)) {
				inUse -= endTimeMapBuckets.get(clock);
				notInUse += endTimeMapBuckets.get(clock);
				endingTimes.remove(clock);
			}
			clock++;
		}
		out.println(notInUse);
		out.close();

	}

}
