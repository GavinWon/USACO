package Silver;
import java.util.*;
import java.io.*;

public class MilkVisits {

	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2019December\\src\\Silver";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}

	private static int N, M;
	private static String milkType;
	private static char[] milkTypes;
	private static LinkedList<Integer>[] connections;
	private static boolean visited[];
	private static int[] group;
	private static int nextGroupNum = 1;

	public static void main(String[] args) throws IOException {

		String parentDir = getBaseFilePath();
		BufferedReader in = new BufferedReader(new FileReader(new File(parentDir, "milkvisits.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "milkvisits.out"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		connections = new LinkedList[N + 1];
		milkTypes = new char[N + 1];
		group = new int[N + 1];
		visited = new boolean[N + 1];
		
		milkType = in.readLine();
		for (int i = 1; i <= N; i++) {
			connections[i] = new LinkedList<Integer>();
			milkTypes[i] = milkType.charAt(i - 1);
		}
		
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int farm1 = Integer.parseInt(s.nextToken());
			int farm2 = Integer.parseInt(s.nextToken());
			connections[farm1].add(farm2);
			connections[farm2].add(farm1);
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i, nextGroupNum);
				nextGroupNum++;
			}
		}
		System.out.println(Arrays.toString(group)); 
		
		for (int i = 1; i <= M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int farm1 = Integer.parseInt(s.nextToken());
			int farm2 = Integer.parseInt(s.nextToken());
			char type = s.nextToken().charAt(0);
			if (group[farm1] != group[farm2]) {
				out.print(1);
			} else {
				if (milkTypes[farm1] == type) {
					out.print(1);
				} else {
					out.print(0);
				}
			}
		}
		out.close();
	}
	public static void dfs(int i, int groupNum) {
		if (visited[i]) return;
		visited[i] = true;
		group[i] = groupNum;
		for (int farm : connections[i]) {
			if (milkTypes[farm] == milkTypes[i]) {//seperate into different grouping
				dfs(farm, groupNum);
			}
		}
	}

}
