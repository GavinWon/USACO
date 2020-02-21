import java.util.*;
import java.io.*;

public class Wormhole {
	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2020January/src/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	public static class Edge{
		int node, width;
		public Edge (int node, int width){
			this.node = node;
			this.width = width;
		}
	}

	private static int N, M;
	private static int[] positions;
	private static LinkedList<Edge>[] connections;
	//private static int[][] minWidth; //min width from index to other index node
	private static HashSet<Integer> nonBFSNodes;
	private static int[] minEdge;
	private static int[] maxEdge;
	private static int[] minWidth;
	private static int minDistance = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "wormsort.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "wormsort.out"));
		N = in.nextInt();
		M = in.nextInt();
		positions = new int[N + 1];
		connections = new LinkedList[N + 1];
//		minWidth = new int[N + 1][N + 1];
		nonBFSNodes = new HashSet<Integer>();
		minEdge = new int[N + 1];
		maxEdge = new int[N + 1];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		for (int i = 1; i <= N; i++) {
			positions[i] = in.nextInt();
			if (i == positions[i]) {
				nonBFSNodes.add(i);
			}
			connections[i]= new LinkedList<Edge>();
		}
		//keep track of which cow has the lowest maximum edge
		int n = 1;
		for (int i = 1; i <= M; i++) {
			int node1 = in.nextInt();
			int node2 = in.nextInt();
			int width = in.nextInt();
			connections[node1].add(new Edge(node2, width));
			connections[node2].add(new Edge(node1, width));
			minEdge[node1] = Math.min(width, minEdge[node1]);
			maxEdge[node1] = Math.max(width, maxEdge[node1]);
			minEdge[node2] = Math.min(width, minEdge[node2]);
			maxEdge[node2] = Math.max(width, maxEdge[node2]);
		}
		int lowestMax = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			if (lowestMax > maxEdge[i]) {
				lowestMax = maxEdge[i];
				n = i;
			}
		}
//		System.out.println(Arrays.toString(minEdge));
//		System.out.println(Arrays.toString(maxEdge));
		
		bfs(n);
		checkIfMin(n);
//		System.out.println(n);
		for (int node = 1; node <= N; node++)  {
			if (nonBFSNodes.contains(node) || minEdge[node] >= minDistance || node == n) continue;
			bfs(node);
			checkIfMin(node);
			System.out.println(minDistance);
			System.out.println(Arrays.toString(minWidth));
		}
		

		if (minDistance == Integer.MAX_VALUE) {
				out.println(-1);
		}
		else {
			out.println(minDistance);
		}
		out.close();
		
//		System.out.println();
//		for (int i = 1; i <= N; i++) {
//			System.out.println(Arrays.toString(minWidth[i]));
//		}
		

	}
	public static void checkIfMin(int node) {
		int cow = positions[node];
		if (cow == node) return; //already sorted
		if (minWidth[cow] == 0) return;
		int distance = minWidth[cow];
		minDistance = Math.min(minDistance, distance);
	}
	public static void bfs(int n) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		minWidth = new int[N + 1];
		q.add(n);
		while(!q.isEmpty()){
			int node = q.remove();
			for (Edge connectNode : connections[node]) {
				int nodeNum = connectNode.node;
				int width = connectNode.width;
				if (nodeNum == n) continue;
				if (minWidth[nodeNum] == 0) {
					if (minWidth[node] == 0) {
						minWidth[nodeNum] = width;
						q.add(nodeNum);
					} else {
						int trail = Math.min(minWidth[node], width);
						minWidth[nodeNum] = trail;
						q.add(nodeNum);
					}
				} else {
					int trail = Math.min(minWidth[node], width);
					if (trail > minWidth[nodeNum]) {
						minWidth[nodeNum] = Math.max(minWidth[nodeNum], trail);
						q.add(nodeNum);
					}
					
				}
				
			}
		}
//			System.out.println(Arrays.toString(widths));
		
	}

	
	
}
