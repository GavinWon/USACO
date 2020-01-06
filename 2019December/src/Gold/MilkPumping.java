package Gold;

import java.io.*;
import java.util.*;

public class MilkPumping {

	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2019December\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	public static class connectedJunc {
		int junc;
		int cost;
		int flow;
		public connectedJunc(int j, int c, int f) {
			junc = j;
			cost = c;
			flow = f;
		}
	}
	
	public static class pair implements Comparable<pair>{
		int junc;
		int cost;
		public pair(int j, int c) {
			junc = j;
			cost = c;
		}
		
		public int compareTo(pair p) {
			return cost - p.cost;
		}
	}
	
	private static int N, M;
	private static int[] cost; //same as distance array
	private static int[] flowRate;
	private static double[] ratio;
	private static LinkedList<connectedJunc>[] connections;
	private static PriorityQueue<pair> pq = new PriorityQueue<pair>();

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "pump.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "pump.out"));
		
		N = in.nextInt();
		M = in.nextInt();
		
		cost = new int[N + 1];
		flowRate = new int[N + 1];
		ratio = new double[N + 1];
		connections = new LinkedList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.MAX_VALUE;
			flowRate[i] = Integer.MAX_VALUE;
			connections[i] = new LinkedList<connectedJunc>();
		}
		
		//reading input in
		for (int i = 1; i <= M; i++) {
			int junc1 = in.nextInt();
			int junc2 = in.nextInt();
			int cost = in.nextInt();
			int flow = in.nextInt();
			connections[junc1].add(new connectedJunc(junc2, cost, flow));
			connections[junc2].add(new connectedJunc(junc1, cost, flow));
		}
		
		cost[1] = 0;
		pair p = new pair(1,0);
		pq.add(p);
		while(!pq.isEmpty()) {
			pair next = pq.remove();
//			System.out.println(next.junc + " " + next.cost);
			for (connectedJunc cj : connections[next.junc]) {
				if (cost[cj.junc] == Integer.MAX_VALUE) {
					cost[cj.junc] = cost[next.junc] + cj.cost;
					flowRate[cj.junc] = Math.min(cj.flow, flowRate[next.junc]);
					pq.add(new pair(cj.junc, cost[cj.junc]));
				} else if ((flowRate[cj.junc] + 0.0) / cost[cj.junc] < (Math.min(flowRate[next.junc], cj.flow) + 0.0) / (cost[next.junc] + cj.cost)) {
					cost[cj.junc] = cost[next.junc] + cj.cost;
					flowRate[cj.junc] = Math.min(cj.flow, flowRate[next.junc]);
					pq.add(new pair(cj.junc, cost[cj.junc]));
				}
			}
		}
		
//		System.out.println(Arrays.toString(cost));
//		System.out.println(Arrays.toString(flowRate));
//		System.out.println((int)((flowRate[N] + 0.0) / cost[N] * 1000000));
		out.print((int)((flowRate[N] + 0.0) / cost[N] * 1000000));
		out.close();
		
		
		
		

	}

}
