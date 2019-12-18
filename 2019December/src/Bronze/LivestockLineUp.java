package Bronze;
import java.util.*;
import java.io.*;

public class LivestockLineUp {
	private static int N;
	private static int[][] restrictions;
	private static HashMap<String, Integer> nameMapToNum = new HashMap<String, Integer>();
	private static String[] names = new String[]{"Beatrice", "Belinda", "Bella", "Bessie", "Betsy", "Blue", "Buttercup", "Sue"};
	private static int[] bestLineUp = new int[] {9, 9, 9, 9, 9, 9, 9, 9};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lineup.in"));
		PrintWriter out = new PrintWriter(new File("lineup.out"));
		
		nameMapToNum.put("Beatrice", 1);
		nameMapToNum.put("Belinda", 2);
		nameMapToNum.put("Bella", 3);
		nameMapToNum.put("Bessie", 4);
		nameMapToNum.put("Betsy", 5);
		nameMapToNum.put("Blue", 6);
		nameMapToNum.put("Buttercup", 7);
		nameMapToNum.put("Sue", 8);
		
		//1 - Beatrice, 2 - Belinda, 3 - Bella, 4- Bessie, 5 - Betsy, 6 - Blue, 7 - Buttercup, 8- Sues
		N = Integer.parseInt(in.readLine());
		restrictions = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine());
			restrictions[i][0] = nameMapToNum.get(s.nextToken());
			s.nextToken(); s.nextToken(); s.nextToken(); s.nextToken();
			restrictions[i][1] = nameMapToNum.get(s.nextToken());
		}
		for (int i1 = 1; i1 <= 8; i1++) {
			for (int i2 = 1; i2 <= 8; i2++) {
				
				if (i2 == i1) continue;
				
				for (int i3 = 1; i3 <= 8; i3++) {
			
					if (i3 == i2 || i3 == i1) continue;
					
					for (int i4 = 1; i4 <= 8; i4++) {
						
						if (i4 == i3 || i4 == i2 || i4 == i1) continue;
						
						for (int i5 = 1; i5 <= 8; i5++) {
							
							if (i5 == i4||i5 == i3||i5 == i2||i5 == i1) continue;
							
							for (int i6 = 1; i6 <= 8; i6++) {
								
								if (i6 == i5||i6 == i4||i6 == i3||i6 == i2 ||i6 == i1) continue;
								
								for (int i7 = 1; i7 <= 8; i7++) {
									
									if (i7 == i6||i7 == i5||i7 == i4||i7 == i3 ||i7 == i2 || i7 == i1) continue;
									
									for (int i8 = 1; i8 <= 8; i8++) {
										
										if (i8 == i7||i8 == i6||i8 == i5||i8 == i4 ||i8 == i3 || i8 == i2|| i8 == i1) continue;
	
					int[] order = new int[]{i1, i2, i3, i4, i5, i6, i7, i8};
					boolean pass = passes(order);
					if (pass) {
						checkIfBetter(order);
					}
					
										
										
									}
								}
							}
						}
					}
				}
			}
		}
		

		out.println(names[bestLineUp[0] - 1]);
		out.println(names[bestLineUp[1] - 1]);
		out.println(names[bestLineUp[2] - 1]);
		out.println(names[bestLineUp[3] - 1]);
		out.println(names[bestLineUp[4] - 1]);
		out.println(names[bestLineUp[5] - 1]);
		out.println(names[bestLineUp[6] - 1]);
		out.println(names[bestLineUp[7] - 1]);
		out.close();

	}
	public static boolean passes(int[] order) {
		for (int row = 0; row < N; row++) {
			int cow1 = restrictions[row][0];
			int cow2 = restrictions[row][1];
			boolean casePass = false;
			for (int i = 0; i < 7; i++) {
				if (order[i] == cow1 && order[i + 1] == cow2 || order[i] == cow2 && order[i + 1] == cow1)
					casePass = true;
			}
			if (!casePass) return false;
		}
		return true;
	}
	
	public static void checkIfBetter(int[] order) {
		for (int i = 0; i < 8; i++) {
			if (order[i] < bestLineUp[i]) {
				bestLineUp = order;
				return;
			} else if (bestLineUp[i] < order[i]) {
				return;
			}
		}
	}

}
