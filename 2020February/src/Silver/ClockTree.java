package Silver;
import java.util.*;
import java.io.*;

public class ClockTree {

	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2020February/src/Silver/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	private static int N;
	private static int totalMissing = 0;
	private static int[] initial;
	private static int[] missing;
	private static LinkedList<Integer>[] adjacencyList; 
	private static HashSet<Integer> validRooms = new HashSet<Integer>(); //room ID that works

	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "clocktree.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "clocktree.out"));
		
		N = in.nextInt();
		initial = new int[N + 1];
		missing = new int[N + 1];
		adjacencyList = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			initial[i] = in.nextInt();
			missing[i] = 12 - initial[i];
			totalMissing += missing[i];
			adjacencyList[i] = new LinkedList<Integer>();
		}
		for (int i = 1; i <= N - 1; i++) {
			int room1 = in.nextInt();
			int room2 = in.nextInt();
			adjacencyList[room1].add(room2);
			adjacencyList[room2].add(room1);
		}
		for (int room = 1; room <= N; room++) {
			if (missing[room] == 0) {
				continue;
			}
			if (adjacencyList[room].size() == 1 && validRooms.contains(adjacencyList[room].getFirst())) { 
				System.out.println("EZ Skip");
				continue;
			}
			missing[room]--;
			if (dfs(room, totalMissing - 1)) {
				addRooms(room);
			} 
			missing[room]++;
		}
		System.out.println(validRooms.size());
		for (int i : validRooms) {
			System.out.println(i);
		}
	}
	
	public static boolean dfs(int roomEnter, int missLeft) {
		if (missLeft == 0) {
			return true;
		}
		for (int connected: adjacencyList[roomEnter]) {
			if (missing[connected] > 0) {
				missing[connected]--;
				boolean attempt = dfs(connected, missLeft - 1);
				missing[connected]++;
				if (attempt) return true;
			}
		}
		return false;
	}
	
	public static void addRooms(int room)
	{
		for (int i : adjacencyList[room]) {
			boolean added = validRooms.add(i);
			if (added) System.out.println(i + " is a valid room");
		}
	}
//	public static boolean dfsStack(int firstRoom, int missLeft) {
//		Stack<Integer> stack
//		return true;
//	}
	
	


	
	
}
