package Gold;

import java.io.*;
import java.util.*;

public class APieForAPie {
	
	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2017December\\src\\Gold";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	public static class Pie implements Comparable<Pie>{
		int B; int E; boolean BIsMaker;
		public Pie (int B, int E, boolean BIsMaker) {
			this.B = B; 
			this.E = E;
			this.BIsMaker = BIsMaker;
		}
		
		public int compareTo (Pie p){
			if (BIsMaker) {
				return this.B - p.B;
			} else {
				return this.E - p.E;
			}
			
		}
	}
	
	
	private static int N, D;
	private static Pie[] bPies;
	private static Pie[] ePies;
	private static LinkedList<Integer>[] adjacencyList;
	

	public static void main(String[] args) throws Exception{
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "piepie.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "piepie.out"));
		
		N = in.nextInt();
		D = in.nextInt();
		bPies = new Pie[N + 1];
		ePies = new Pie[N + 1];
		adjacencyList = new LinkedList[2 * N + 1];
		
		//1 construct the tree
		//2 BFS the tree from different source
		for (int i = 1; i <= N; i++) {
			adjacencyList[i] = new LinkedList<Integer>();
			int b = in.nextInt();
			int e = in.nextInt();
			bPies[i] = new Pie(b, e, true);
//			System.out.println("Pie: " + b + " " + e + " true");
		} 
		
		for (int i = 1; i <= N; i++) {
			adjacencyList[i + N] = new LinkedList<Integer>();
			int b = in.nextInt();
			int e = in.nextInt();
			ePies[i] = new Pie(b, e, false);
//			System.out.println("Pie: " + b + " " + e + " false");
		}
		Arrays.sort(bPies, 1, N + 1);
		Arrays.sort(ePies, 1, N + 1);
		
		// adding all of the ePies to bPies
		for (int i = 1; i <= N; i++) {
			Pie p = bPies[i];
			int index = binarySearch(i, true);
			int index2 = index + 1;
			if (index == 0) continue;
			while(index >= 1 && ePies[index].E >= p.E && ePies[index].E <= p.E + D) {
				adjacencyList[i].add(index + N);
				index--;
			} 
			while(index2 <= N && ePies[index2].E >= p.E && ePies[index2].E <= p.E + D) {
				adjacencyList[i].add(index2 + N);
				index2++;
			}
			
			
		}
		
		//adding all of the bPies to ePies
		for (int i = 1; i <= N; i++) {
			Pie p = ePies[i];
			int index = binarySearch(i, false);
			int index2 = index + 1;
			if (index == 0) continue;
			while(index >= 1 && bPies[index].B >= p.B && bPies[index].B <= p.B + D) {
				adjacencyList[i + N].add(index);
				index--;
			} 
			while(index2 <= N && bPies[index2].B >= p.B && bPies[index2].B <= p.B + D) {
				adjacencyList[i + N].add(index2);
				index2++;
			}
		}
//		
//		for (int i = 1005; i <= 1100; i++) {
//			for (int j : adjacencyList[i]) {
//				System.out.print(j + " ");
//			}
//			System.out.println();
//		}
		
		for (int i = 1; i <= N; i++) {
			int num = bfs(i);
			if (num == Integer.MAX_VALUE) out.println(-1);
			else { out.println(num); }
		}
		out.close();

	}
	
	public static int bfs(int root) {
		int returnVal = Integer.MAX_VALUE;
		int[] distance = new int[2 * N + 1];
		boolean[] added = new boolean[2 * N + 1];
		distance[root] = 1;
		Queue<Integer> q = new LinkedList<Integer>();
//		boolean eRecieving = false;
		q.add(root);
		added[root] = true;
		
		//checking yourself first
		if (bPies[root].E == 0) {
			returnVal = Math.min(returnVal, 1);
		}
		
		while(!q.isEmpty()) {
			int node = q.remove();
			for (int connect : adjacencyList[node]) {
				if (added[connect]) continue;
				added[connect] = true;
				distance[connect] = distance[node] + 1;
//				if (connect == 173) {
//					System.out.println("debug");
//				}
				if (connect <= N && bPies[connect].E == 0) {
					returnVal = Math.min(returnVal, distance[connect]);
				}
				if (connect > N && ePies[connect - N].B == 0) {
					returnVal = Math.min(returnVal, distance[connect]);
				}
				q.add(connect);
			}
		}
		return returnVal;
	}
	
	//{x, y} x-the starting(inclusive) y-the ending(inclusive)
	public static int binarySearch(int pieIndex, boolean BPie) {
		
		int index = 0;
		int startIndex = 1; int endIndex = N;
		if (BPie) {	
			Pie p = bPies[pieIndex];
//			System.out.println("Pie "+ p.B + " " + p.E);
			while(startIndex <= endIndex) {
				int mid = (startIndex + endIndex) / 2;
				if (ePies[mid].E >= p.E && ePies[mid].E <= p.E + D) {
					index = mid;
					break;
				} else {
					if (ePies[mid].E < p.E) {
						startIndex = mid + 1;
					} else {
						endIndex = mid - 1;
					}
				}
			}
		} else {
			Pie p = ePies[pieIndex];
//			System.out.println("Pie "+ p.B + " " + p.E);
			while(startIndex <= endIndex) {
				int mid = (startIndex + endIndex) / 2;
				if (bPies[mid].B >= p.B && bPies[mid].B <= p.B + D) {
					index = mid;
					break;
				} else {
					if (bPies[mid].B < p.B) {
						startIndex = mid + 1;
						
					} else {
						endIndex = mid - 1;
					}
				}
			}
		}
		return index;
	}


}
