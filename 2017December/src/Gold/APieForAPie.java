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
	private static LinkedList<Integer>[] adjacencyList;
	private static Pie[] ePies;

	public static void main(String[] args) throws Exception{
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "piepie.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "pump.out"));
		
		N = in.nextInt();
		D = in.nextInt();
		bPies = new Pie[N];
		ePies = new Pie[N];
		adjacencyList = new LinkedList[N];
		
		//1 construct the tree
		//2 BFS the tree from different source
		for (int i = 0; i < N; i++) {
			adjacencyList[i] = new LinkedList<Integer>();
			int b = in.nextInt();
			int e = in.nextInt();
			bPies[i] = new Pie(b, e, true);
		}
		
		for (int i = 0; i < N; i++) {
			adjacencyList[i + N] = new LinkedList<Integer>();
			int b = in.nextInt();
			int e = in.nextInt();
			ePies[i] = new Pie(b, e, false);
		}
		Arrays.sort(bPies);
		Arrays.sort(ePies);
		
		//index -- Bessie Pies || value -- Elssie Pies
		for (int i = 0; i < N; i++) {
			int[] set = binarySearch(i);
			for (int j = set[0]; j <= set[1]; j++) {
				adjacencyList[i].add(j + N);
			}
		}
		
		//index -- Elssie Pies || value -- Bessie Pies
		for (int i = N; i < 2 * N; i++) {
			int[] set = binarySearch(i);
			for (int j = set[0]; j <= set[1]; j++) {
				adjacencyList[i].add(j);
			}
		}
		
		for (LinkedList<Integer> l : adjacencyList) {
			for (int pie : l) {
				System.out.print(pie);
			}
			System.out.println();
		}

	}
	
	//{x, y} x-the starting(inclusive) y-the ending(inclusive)
	public static int[] binarySearch(int pieIndex) {
		return null;
	}

}
