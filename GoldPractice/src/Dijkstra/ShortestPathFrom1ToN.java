package Dijkstra;

import java.util.*;
import java.io.*;

public class ShortestPathFrom1ToN {
	
	public static class Node implements Comparable<Node>{
		int distance = Integer.MAX_VALUE;
		int value = 0;
		public Node (int val, int distance) {
			value = val;
			this.distance = distance;
		}
		
		public int compareTo(Node n) {
			return distance - n.distance;
		}
	}
	
	private static int[] distances;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException{
		int N = 9;
		distances = new int[N + 1];
		visited = new boolean[N + 1];
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(2, 1));
		q.add(new Node(3, 1));
		visited[1] = true;
		
		while(!q.isEmpty() || q.peek().value != N) {
			Node currentNode = q.remove();
			int val = currentNode.value;
			int distance = currentNode.distance;
			distances[val] = distance;
			visited[val] = true;
			if (val * 3 <= N && (distances[val * 3] == 0 || distances[val * 3] > distances[val] + 1)) {
				distances[val * 3] = distances[val] + 1;
				q.add(new Node(val * 3, distances[val * 3]));
			}
			if (val + 1 <= N && (distances[val + 1] == 0 || distances[val + 1] > distances[val] + 1)) {
				distances[val * 3] = distances[val] + 1;
				q.add(new Node(val + 1, distances[val + 1]));
			}
		}
		System.out.println(distances[N]);
	}

}
