import java.util.*;
import java.io.*;

public class MilkVisits {
	private static void println(Object obj) {
		System.out.println(obj);
	}

	private static void println(String format, Object... args) {
		System.out.println(String.format(format, args));
	}

	private static String DEF_PARENT_DIR = "D:\\Repos\\USACO\\2019DecSilver\\src\\";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}

	private static int N, M;
	private static String milkType;
	private static ArrayList<Integer>[] connections;
	private static boolean visited[];
	private static boolean[][][] hasHG;
	private static final int Milk_H = 0, Milk_G = 1;

	public static void main(String[] args) throws IOException {

		String parentDir = getBaseFilePath();
		BufferedReader in = new BufferedReader(new FileReader(new File(parentDir, "milkvisits.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "milkvisits.out"));

		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 1];
		connections = new ArrayList[N + 1];
		hasHG = new boolean[N + 1][N + 1][2];
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
			boolean cacheHitWithinCalculation = false;
			StringTokenizer s = new StringTokenizer(in.readLine());
//			String trail = "";
			boolean satisfied = false;
			int farm1 = Integer.parseInt(s.nextToken());
			int farm2 = Integer.parseInt(s.nextToken());
			int farmS = Math.min(farm1, farm2);
			int farmD = Math.max(farm1, farm2);
			char desiredMilk = s.nextToken().charAt(0);

			visited = new boolean[N + 1];

			// System.out.println(hasHG[farmS][farmD][0] + " " + hasHG[farmS][farmD][1]);

			if (hasHG[farmS][farmD][Milk_H] || hasHG[farmS][farmD][Milk_G]) {
				boolean yes = hasHG[farmS][farmD][Milk_H] && desiredMilk == 'H'
						|| hasHG[farmS][farmD][Milk_G] && desiredMilk == 'G';
				int result = yes ? 1 : 0;
				println("cache hit: %d --> %d: %d", farmS, farmD, result);
				out.print(result);
				continue;
			}

			// fillout the hasHG for root
			if (milkType.charAt(farmS - 1) == 'H')
				hasHG[farmS][farmS][0] = true;
			else
				hasHG[farmS][farmS][1] = true;

			if (milkType.charAt(farmS - 1) == desiredMilk || milkType.charAt(farmD - 1) == desiredMilk) {
				satisfied = true;
			} else {

				Stack<Integer> stack = new Stack<Integer>();
				stack.push(farmS);

				while (stack.empty() == false) {
					int farm = stack.pop();
//					trail += milkType.charAt(farm - 1);
//					counter++;

					if (farm == farmD && (hasHG[farmS][farmD][Milk_H] && desiredMilk == 'H'
							|| hasHG[farmS][farmD][Milk_G] == true && desiredMilk == 'G')) {
						satisfied = true;
						break;
					} else if (farm == farmD) {
						break;
					}
					visited[farm] = true;

					ArrayList<Integer> currentFarmC = connections[farm];

					for (int f : currentFarmC) {
						if (f == 6) {
//							System.out.println("debug");
						}
						if (visited[f] == false) {
							hasHG[farmS][f][Milk_H] = hasHG[farmS][farm][Milk_H];
							hasHG[farmS][f][Milk_G] = hasHG[farmS][farm][Milk_G];
							// System.out.println(hasHG[farmS][f][Milk_H] + " " + hasHG[farmS][f][Milk_G]);
							if (milkType.charAt(f - 1) == 'H') {
								hasHG[farmS][f][Milk_H] = true;
//								System.out.println(hasHG[farmS][f][0]);
							} else {
								hasHG[farmS][f][Milk_G] = true;
//								System.out.println(hasHG[farmS][f][1]);
							}
							int startingFarm = Math.min(f, farmD);
							int endingFarm = Math.max(f, farmD);
							if (hasHG[startingFarm][endingFarm][Milk_H] == true
									|| hasHG[startingFarm][endingFarm][Milk_G] == true) {
								boolean yes = hasHG[farmS][farmD][Milk_H] && desiredMilk == 'H'
										|| hasHG[farmS][farmD][Milk_G] && desiredMilk == 'G';
								if (hasHG[startingFarm][endingFarm][Milk_H])
									hasHG[farmS][farmD][Milk_H] = true;
								if (hasHG[startingFarm][endingFarm][Milk_G])
									hasHG[farmS][farmD][Milk_G] = true;
								int result = yes ? 1 : 0;
								println("cache hit within calculation: %d --> %d: %d", farmS, farmD, result);
								out.print(result);
								cacheHitWithinCalculation = true;
								break;
							}

							stack.push(f);
						}
//						count++;
//						if (noneVisited && count == currentFarmC.size()) {
//							counter = 0;
//						}
					}
				}

			}

			if (!cacheHitWithinCalculation) {
				int result = satisfied ? 1 : 0;
				println("%d --> %d: %d", farmS, farmD, result);
				out.print(result);
			}
		}
		out.close();
	}

}
