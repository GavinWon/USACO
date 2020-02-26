package Silver;
import java.util.*;
import java.io.*;

public class ClockTreeImproved {


	
	private static int N;
	private static LinkedList<Integer>[] adjacencyList; 
	private static int[] initial;
	private static int[] missingOG;
	private static int[] missing;
	private static HashSet<Integer> validRooms = new HashSet<Integer>();
	
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader(new File("clocktree.in")));
		PrintWriter out = new PrintWriter(new File("clocktree.out"));
		
		N = in.nextInt();
		initial = new int[N + 1];
		missingOG = new int[N + 1];
		adjacencyList = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			initial[i] = in.nextInt();
			missingOG[i] = 12 - initial[i];
			adjacencyList[i] = new LinkedList<Integer>();
		}
		for (int i = 1; i <= N - 1; i++) {
			int room1 = in.nextInt();
			int room2 = in.nextInt();
			adjacencyList[room1].add(room2);
			adjacencyList[room2].add(room1);
		}
		for (int room = 1; room <= N; room++) {
			if (adjacencyList[room].size() == 1 && validRooms.contains(adjacencyList[room].getFirst())) { 
				continue;
			}
			missing = missingOG.clone();
			missing[room]--;
			int number = dfs(room, -1);
			if (number == 0 || number == 11 || number == 1) {
				addRooms(room);
				//System.out.println(room + " number = " + number);
			} 
			else {
				//System.out.println(room + " number = " + number);
			}
		}
		out.println(validRooms.size());

	}
	
	public static int dfs(int roomEntered, int parent) {
//		int val = missing[roomEntered];
		for (int connected : adjacencyList[roomEntered]) {
			//if (adjacencyList[roomEntered].size() == 1 && adjacencyList[roomEntered].getFirst() == parent) missing[roomEntered]++;
			if (connected == parent) continue;
			missing[roomEntered] -= dfs(connected, roomEntered);
			if (missing[roomEntered] < 0) missing[roomEntered] += 12;
		}
		return missing[roomEntered];
		
	}
	
	public static void addRooms(int room)
	{
		for (int i : adjacencyList[room]) {
			boolean added = validRooms.add(i);
			//if (added) System.out.println(i + " is a valid room");
		}
	}
	
	
}