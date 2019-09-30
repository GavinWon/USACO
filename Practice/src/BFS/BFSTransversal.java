package BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSTransversal {
	static class Node {
		int value;
		Node left; Node right;
		Node (int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(22);
		root.left.right = new Node(12);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);
		bfs1(root);
		System.out.println();
		bfs2(root);

	}
	//WITH LINKEDLIST
	public static void bfs1(Node root) { 
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while(q.size() != 0) {
			Node n = q.poll();
			System.out.print(n.value + " ");
			if (n.left != null)
				q.add(n.left);
			if (n.right != null)
				q.add(n.right);
		}
	}
	
	//WITH ARRAYLIST
	public static void bfs2(Node root) {
		ArrayList<Node> q = new ArrayList<Node>();
		q.add(root);
		while(q.size() != 0) {
			Node n = q.remove(0);
			System.out.print(n.value + " ");
			if (n.left != null)
				q.add(n.left);
			if (n.right != null) 
				q.add(n.right);
		}
		
	}
	

}
