package Gold;

import java.util.*;
import java.io.*;
public class HaybaleFeast {
	
	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2018December/src/Gold/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	public static class hay {
		long flavor;
		long spicy;
		public hay (long flavor, long spicy) {
			this.flavor = flavor;
			this.spicy = spicy;
			
		}
	}
	
	private static int N;
	private static long M;
	private static hay[] haybales;

	public static void main(String[] args) throws IOException{
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "hayfeast.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "hayfeast.out"));
		N = in.nextInt();
		M = in.nextLong();
		haybales = new hay[N];
		for (int i = 0; i < N; i++) {
			haybales[i] = new hay(in.nextInt(), in.nextInt());
		}
		long start = 1; long end = 1000000000;
		while (start <= end) {
			long mid = (start + end) / 2;
			if (attempt(mid)) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		out.println(start);
		out.close();
		
		

	}

	
	public static boolean attempt(long spiciness) {
		long sum = 0;
		for (hay h : haybales) {
			if (h.spicy > spiciness) {
				sum = 0;
			}
			sum += h.flavor;
			if (sum >= M) return true;
		}
		return false;
	}
}

