//package Gold;

import java.io.*;
import java.util.*;

public class CowChecklist {

	private static String DEF_PARENT_DIR = "/Users/gavinwong/Desktop/Repos/USACO/2016December/src/Gold/";

	private static String getBaseFilePath() {
		File dir = new File(DEF_PARENT_DIR);
		return (dir.exists() && dir.isDirectory()) ? DEF_PARENT_DIR : ".";
	}
	
	public static class Point {
		int x; int y;
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static int H, G;
	private static Point[] holsteins;
	private static Point[] guernseys;
	private static long[][] dpH;
	private static long[][] dpG;
	
	public static void main(String[] args) throws Exception {
		String parentDir = getBaseFilePath();
		Scanner in = new Scanner(new FileReader(new File(parentDir, "checklist.in")));
		PrintWriter out = new PrintWriter(new File(parentDir, "checklist.out"));
		
		H = in.nextInt();
		G = in.nextInt();
		holsteins = new Point[H + 1];
		guernseys = new Point[G + 1];
		dpH = new long[H + 1][G + 1];
		dpG = new long[H + 1][G + 1];
		
		for (int i = 1; i <= H; i++) {
			holsteins[i] = new Point(in.nextInt(), in.nextInt());
		}
		
		for (int i = 1; i <= G; i++) {
			guernseys[i] = new Point(in.nextInt(), in.nextInt());
		}
		
		//base cases
		dpG[0][1] = Integer.MAX_VALUE;
		dpG[1][1] = distance(holsteins[1], guernseys[1]);
		
		dpH[1][1] = Integer.MAX_VALUE;
		for (int g = 1; g <= G; g++) {
			dpH[1][g] = Integer.MAX_VALUE;
		}
		
		for (int h = 2; h <= H; h++) {
			dpH[h][0] = dpH[h - 1][0] + distance(holsteins[h], holsteins[h - 1]);
		}
		
		for (int g = 2; g <= G; g++) {
			dpG[1][g] = dpG[1][g - 1] + distance(guernseys[g], guernseys[g - 1]);
		}
		
		for (int h = 2; h <= H; h++) {
			int distanceHH = distance(holsteins[h], holsteins[h - 1]);
			int distanceGH = distance(holsteins[h], guernseys[1]);
			int distanceHG = distance(guernseys[1], holsteins[h]);
			if (h == 2) {
				dpH[h][1] = dpG[h][1] + distanceGH;
				dpG[h][1] = dpH[h - 1][0] + distanceHG;
			} else {
				dpH[h][1] = Math.min(dpH[h - 1][1] + distanceHH, dpG[h - 1][0] + distanceGH);
				dpG[h][1] = dpH[h - 1][0] + distanceHG;
			}
			
		}
		
		for (int g = 2; g <= G; g++ ){
			for (int h = 2; h <= H; h++) {
				int distanceHH = distance(holsteins[h], holsteins[h - 1]);
				int distanceGH = distance(holsteins[h], guernseys[g]);
				int distanceHG = distance(guernseys[g], holsteins[h]);
				int distanceGG = distance(guernseys[g], guernseys[g - 1]);
				dpH[h][g] = Math.min(dpH[h - 1][g] + distanceHH, dpG[h - 1][g] + distanceGH);
//				System.out.println(dpH[h - 1][g] + distanceHH);
//				System.out.println(dpG[h - 1][g] + distanceGH);
//				System.out.println(h + " " + g + " :" + dpH[h][g]);
				dpG[h][g] = Math.min(dpH[h][g - 1] + distanceHG, dpG[h][g - 1] + distanceGG);
//				System.out.println(h + " " + g + " :" + dpG[h][g]);
			}
		}
//		for (int i = 0; i <= H; i++) {
//			System.out.println(Arrays.toString(dpH[i]));
//		}
//		System.out.println("-----------------");
//		for (int i = 0; i <= H; i++) {
//			System.out.println(Arrays.toString(dpG[i]));
//		}
//		System.out.println(dpH[H - 1][G]);
//		System.out.println(dpG[H - 1][G]);
		out.println(dpH[H][G]);
		out.close();
	}
	
	
	public static int distance(Point a, Point b) {
		int x = (b.x - a.x) * (b.x - a.x);
		int y = (b.y - a.y) * (b.y - a.y);
		return x + y;
	}
	

}
