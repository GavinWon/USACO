package Silver;

import java.util.*;
import java.io.*;

public class PartyInvitations {
	
	static int N, G;
	static HashSet<Integer> invitedCows = new HashSet<Integer>();
	static HashSet<Integer>[] input;
	static int nextCow;

	public static void main(String[] args) throws Exception{
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2013January\\src\\Silver\\invite.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2013January\\src\\Silver\\invite.out"));
		Scanner in = new Scanner(new FileReader("invite.in"));
		PrintWriter out = new PrintWriter(new File("invite.out"));
		N = in.nextInt();
		G = in.nextInt();
		
		input = new HashSet[G];
		for (int i = 0; i < G; i++) {
			int s = in.nextInt();
			input[i] = new HashSet<Integer>();
			for (int j = 0; j < s; j++) {
				input[i].add(in.nextInt());
			}
		}
		
		addCow(1);
		
		while(canAdd()) {
			addCow(nextCow);
		}
		
		out.println(invitedCows.size());
		out.close();
	}
	
	public static boolean canAdd() {
		for (int i = 0; i < G; i++) {
			if (input[i].size() == 1) {
				nextCow = (int) input[i].toArray()[0];
				return true;
			}
		}
		return false;
	}
	
	public static void addCow(int n) {
		invitedCows.add(n);
		for (int i = 0; i < G; i++) {
			if (input[i].contains(n)) {
				input[i].remove(n);
			}
		}
	}

}
