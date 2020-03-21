package Gold;

import java.util.*;
import java.io.*;

public class IWouldWalk500Miles {
	
	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2019March\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}

	public static class Node implements Comparable<Node>  {
		int cow; int distance;
		public Node(int Cow, int Distance) {
			cow = Cow;
			distance = Distance;
		}
		public int compareTo(Node n) {
			return distance - n.distance;
		}
	}
	
	public static class Edge {
		
	}
	
	private static int N, K;
	private static boolean[] added;
	private static boolean[] inPQ;
	private static Node[] node;
	private static int cowsAdded = 0;

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "walk.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "walk.out"));
		N = in.nextInt();
		K = in.nextInt();
		added = new boolean[N + 1];
		inPQ = new boolean[N + 1];
		node = new Node[N + 1];
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		node[1] = new Node(1,0);
		pq.add(node[1]);
		inPQ[1] = true;
		for (int i = 2; i <= N; i++) {
			node[i] = new Node(i, Integer.MAX_VALUE);
			pq.add(node[i]);
			inPQ[i] = true;
		}
		
//		System.out.println(distance(3, 2));
		//let cow 1 be the root
		Integer[] edges = new Integer[N - 1];
		int index = -1;
		while (!pq.isEmpty()) {
			Node n = pq.remove();
//			System.out.println(n.cow + " " + n.distance);
			int cow = n.cow;
			if (index != -1) {
				edges[index] = n.distance;
			}
			index++;
			added[cow] = true;
			inPQ[cow] = false;
			for (int i = 1; i <= N; i++) {
				if (added[i] || i == cow) continue;
				if (inPQ[i]) {
					if (node[i].distance > distance(cow, i)) {
						pq.remove(node[i]);
						node[i].distance = distance(cow, i);
						pq.add(node[i]);
						
					}
				}
			}
			
		}
		Arrays.sort(edges, Collections.reverseOrder());
		out.println(edges[K - 2]);
//		System.out.println(Arrays.toString(edges));
		out.close();
		

	}
	
	public static int distance(int cow1, int cow2) {
		if (cow2 < cow1) {
			int temp = cow1;
			cow1 = cow2;
			cow2 = temp;
		}
		long one = (2019201913L * cow1) % 2019201997;
		long two = (2019201949L * cow2) % 2019201997;
		return (int) ((one + two) % 2019201997);
	}

}
