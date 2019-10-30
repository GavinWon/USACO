package Bronze;

import java.util.*;
import java.io.*;

public class Photo {

	static int N, K;
	static class Pair implements Comparable<Pair>{
		int low;
		int high;
		public Pair(int start, int end) {
			low = start;
			high = end;
		}
		public int compareTo(Pair p) {
			return p.low - low; //descending order
		}
	}
	public static void main(String[] args) throws Exception {
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2013April\\src\\Bronze\\photo.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2013April\\src\\Bronze\\photo.out"));
		Scanner in = new Scanner(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new File("photo.out"));
		int N = in.nextInt();
		int K = in.nextInt();
		ArrayList<Pair> input = new ArrayList<Pair>();
		for (int i = 0; i < K; i++) {
			int l = in.nextInt();
			int h = in.nextInt();
			Pair p = new Pair(Math.min(l , h), Math.max(l, h));
			input.add(p);
		}
		
		Collections.sort(input);
		
		TreeSet<Integer> photos = new TreeSet<Integer>();
		photos.add(1);
		for (Pair enemies : input) {
			int photoLow = photos.floor(enemies.low);
			int photoHigh = photos.floor(enemies.high);
			if (photoLow == photoHigh) {
				photos.add(enemies.low + 1);
			}
			
		}
		out.println(photos.size());
		out.close();
	}

}
