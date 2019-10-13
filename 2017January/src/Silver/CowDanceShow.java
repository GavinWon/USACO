package Silver;
import java.util.*;
import java.io.*;

public class CowDanceShow {
	
	static int N, maxT;
	static int[] duration;

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2017January\\src\\Silver\\cowdance.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2017January\\src\\Silver\\cowdance.out"));
//		Scanner in = new Scanner(new FileReader("cowdance.in"));
//		PrintWriter out = new PrintWriter(new File("cowdance.out"));
		
		//Reading Inputs in
		N = in.nextInt();
		maxT = in.nextInt();
		duration = new int[N];
		for (int i = 0; i < N; i++) {
			duration[i] = in.nextInt();
		}
		
		int K = 1;
		while (K < N) {
			if (!canDance(K))
				K++;
			else
				break;
		}
		
		out.println(K);
		out.close();

	}
	public static boolean canDance(int k) {
		int clock = 0;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < k; i++) {
			q.add(duration[i] + clock);
		}
		int nextCow = k;
		while(!q.isEmpty()) {
			clock = q.remove();
			if (clock > maxT)
				return false;
			if (nextCow < N) {
				q.add(duration[nextCow] + clock);
				nextCow += 1;
			}
		}
		return true;
	}

}
