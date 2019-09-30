package Silver;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Piggyback {
	static int B, E, P, N, M;
	static int d1, d2, d3;
	//static ArrayList<Integer> connections[];
	static HashSet<Integer> connections[];
	static int minEnergy;
	public static void main (String[] args) throws IOException {
		Scanner input = new Scanner(new File("C://Users//Gavin//Dropbox//USACO//2014December//src//piggyback.in"));
		PrintWriter out = new PrintWriter(new File("C://Users//Gavin//Dropbox//USACO//2014December//src//piggyback.out"));
//		Scanner input = new Scanner(new File("piggyback.in"));
//		PrintWriter out = new PrintWriter(new File("piggyback.out"));
		B = input.nextInt();
		E = input.nextInt();
		P = input.nextInt();
		N = input.nextInt();
		M = input.nextInt();
		//connections = new ArrayList[N + 1];
		connections = new HashSet[N + 1];
		for (int index  = 1; index <= N; index++)
			//connections[index] = new ArrayList<Integer>();
			connections[index] = new HashSet<Integer>();
		for (int index = 1; index <= M; index++) {
			int input1 = input.nextInt();
			int input2 = input.nextInt();
			connections[input1].add(input2);
			connections[input2].add(input1);
		}
		
		minEnergy = Integer.MAX_VALUE;
		int[] d1 = new int[N + 1];
		int[] d2 = new int[N + 1];
		int[] d3 = new int[N + 1];
		
		bfsFrom(1,d1);
		bfsFrom(2,d2);
		bfsFrom(N,d3);
		
		for(int i = 1; i <= N; i++) {	
			minEnergy = Integer.min(minEnergy, B * d1[i] + E * d2[i] + P * d3[i]);
		}
		out.println(minEnergy);
		out.close();
		
	}
	
	public static void bfsFrom(int field1, int[] distance) {
		Arrays.fill(distance, -1);
		distance[field1] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(field1);
		while (!q.isEmpty()) {
			int f = (int) q.remove();
			for (int input : connections[f]) {
				if (distance[input] == -1) {
					q.add(input);
					distance[input] = distance[f] + 1;
				}
			}
		}
			
	}
	
}

