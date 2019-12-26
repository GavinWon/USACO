package Dijkstra;

import java.util.*;
import java.io.*;

public class ShortestPathFrom1ToN { 
	
	private static int[] distances;
	private static boolean[] addedToQueue; //similar to visited

	public static void main(String[] args) throws IOException{
		
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for (int i = 0; i < T; i++) {
			int N = in.nextInt();
			distances = new int[N + 1];
			Arrays.fill(distances, Integer.MAX_VALUE);
			distances[1] = 0;
			addedToQueue = new boolean[N + 1];
			PriorityQueue<Integer> q = new PriorityQueue<Integer>();
			q.add(1);
			addedToQueue[1] = true;
			
			while(!q.isEmpty() && q.peek() != N) {
				int currentNode = q.remove();
				int add1 = currentNode + 1;
				int times3 = currentNode * 3;
				if (add1 <= N) {
					distances[add1] = Math.min(distances[add1], distances[currentNode] + 1);
					if (!addedToQueue[add1]) {
						q.add(add1);
						addedToQueue[add1] = true;
					}
				}
				if (times3 <= N) {
					distances[times3] = Math.min(distances[times3], distances[currentNode] + 1);
					if (!addedToQueue[times3]) {
						q.add(times3);
						addedToQueue[times3] = true;
					}
				}
				
			}
			System.out.println(distances[N]);
		}
	}
}
