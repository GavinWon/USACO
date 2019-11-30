package Bronze;

import java.util.*;
import java.io.*;

public class BalancedTeams {
	
	private static int[] input = new int[12]; //skills array
	private static int[] lineUp = new int[12];
	private static boolean[] assigned = new boolean[12];
	private static int minDifference = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException{
//		Scanner in = new Scanner(new FileReader("bteams.in"));
//		PrintWriter out = new PrintWriter(new File("bteams.out"));
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2014January\\src\\Bronze\\bteams.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2014January\\src\\Bronze\\bteams.out"));

		for (int index = 0; index < 12; index++) {
			input[index] = in.nextInt(); 
		}
		assign(0);
		
		out.print(minDifference);
		System.out.print(minDifference);
		out.close();

	}
	
	public static void assign(int pos) {
		System.out.println("Position:" + pos);
		if (pos == 12) {
			minDifference = Math.min(minDifference, calcSkillDifference(lineUp));
			return;
		}
		int cowIDToStartAssigningToThisSlot = 0;
		
		if (pos % 3 == 0 && pos > 0) {
			cowIDToStartAssigningToThisSlot = lineUp[pos - 3] + 1;
		} else if (pos % 3 != 0 && pos > 0)
			cowIDToStartAssigningToThisSlot = lineUp[pos - 1] + 1;
		
		System.out.println("CowID: " + cowIDToStartAssigningToThisSlot);
		for (int cowID = cowIDToStartAssigningToThisSlot; cowID < 12; cowID++ ) {
			if (!assigned[cowID]) {
				lineUp[pos] = cowID;
				System.out.println(Arrays.toString(lineUp));
				assigned[cowID] = true;
				assign(pos + 1);
				assigned[cowID] = false;
				lineUp[pos] = 0;
				System.out.println(Arrays.toString(lineUp));
			}
		}
	}
	public static int calcSkillDifference(int[] line) {
		int[] teamTotalSkill = new int[4];
		for (int i = 0; i < 12; i++) {
			teamTotalSkill[i / 3] += input[lineUp[i]];
		}
		int maxSkill = 0; int minSkill = Integer.MAX_VALUE;
		for (int i = 0; i < teamTotalSkill.length; i++) {
			if (teamTotalSkill[i] > maxSkill) maxSkill = teamTotalSkill[i];
			if (teamTotalSkill[i] < minSkill) minSkill = teamTotalSkill[i];
		}
		return maxSkill - minSkill;
//		int team1 = input[line[0]] + input[line[1]] +input[line[2]];
//		int team2 = input[line[3]] + input[line[4]]+ input[line[5]];
//		int team3 = input[line[6]] + input[line[7]] + input[line[8]];
//		int team4 = input[line[9]] + input[line[10]] + input[line[11]];
//		
//		int max1 = Math.max(team1, team2);
//		int max2 = Math.max(team3, team4);
//		int max = Math.max(max1, max2);
//		
//		int min1 = Math.min(team1, team2);
//		int min2 = Math.min(team3, team4);
//		int min = Math.min(min1, min2);
//		return max - min;
	}
}

	
