import java.util.*;
import java.io.*;

public class MilkVisits {
	

	private static int N, M;
	private static String milkType;
	private static ArrayList<Integer>[] connections;
	private static boolean visited[];
	private static int[] output;

	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("D:\\Repos\\USACO\\2019DecSilver\\src\\milkvisits.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2019DecSilver\\src\\milkvisits.out"));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		output = new int[M + 1];
		visited = new boolean[N + 1];
		connections =  new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			connections[i] = new ArrayList<Integer>();
		}
		milkType = in.readLine();
		
		for (int i = 1; i <= N - 1; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			int farm1 = Integer.parseInt(s.nextToken());
			int farm2 = Integer.parseInt(s.nextToken());
			connections[farm1].add(farm2);
			connections[farm2].add(farm1);
		}
		

		for (int i = 1; i <= M; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			String trail = "";
			boolean satisfied = false;
			int farm1 = Integer.parseInt(s.nextToken());
			int farm2 = Integer.parseInt(s.nextToken());
			visited = new boolean[N + 1];
			char letter = s.nextToken().charAt(0); 	
			if (milkType.charAt(farm1 - 1) == letter) {
				satisfied = true;
			} else {
				Stack<Integer> stack = new Stack<Integer>();
				stack.push(farm1);
				while (stack.empty() == false) {
					int farm = stack.pop();
					trail += milkType.charAt(farm - 1);
					if (farm == farm2 && trail.indexOf(letter) != -1) {
						satisfied = true;
						break;
					} else if (farm == farm2 && trail.indexOf(letter) == -1) {
						break;
					}
					visited[farm] = true;
					
					ArrayList<Integer> currentFarmC = connections[farm];
					
					boolean noneVisited = true;
					for (int f : currentFarmC) {
						
						if (visited[f] == false) {
							noneVisited = false;
							stack.push(f);
						}
						if (noneVisited) {
							trail.substring(0, trail.length() - 1);
						}
					}
				}
			
			}
			
			if (satisfied) {
				output[i] = 1;
				System.out.println(output[i]);
				out.print(output[i]);
			} else {
				output[i] = 0;
				System.out.println(output[i]);
				out.print(output[i]);
			}
			
		}
		
		
		out.close();

	}

}
