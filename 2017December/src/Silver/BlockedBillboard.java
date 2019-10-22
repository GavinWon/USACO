package Silver;

import java.util.*;
import java.io.*;

public class BlockedBillboard {

	public static void main(String[] args) throws IOException{
		
//		Scanner in = new Scanner(new FileReader("//Users//gavinwong//Desktop//Repos//USACO//2017December//src/Silver//billboard.in"));
//		PrintWriter out = new PrintWriter(new File("//Users//gavinwong//Desktop//Repos//USACO//2017December//src/Silver//billboard.out"));
		Scanner in = new Scanner(new FileReader("billboard.in"));
		PrintWriter out = new PrintWriter(new File("billboard.out"));
		
		int oneX1 = in.nextInt();
		int oneY1 = in.nextInt();
		int oneX2 = in.nextInt();
		int oneY2 = in.nextInt();
		int twoX1 = in.nextInt();
		int twoY1 = in.nextInt();
		int twoX2 = in.nextInt();
		int twoY2 = in.nextInt();
		int blockX1 = in.nextInt();
		int blockY1 = in.nextInt();
		int blockX2 = in.nextInt();
		int blockY2 = in.nextInt();
		
		int totalArea = (oneX2 - oneX1) * (oneY2 - oneY1) + (twoX2 - twoX1)  * (twoY2 - twoY1);
		
		//Calculate area blocked in first sign and subtracting it from total area
		int leftSideX = Math.max(oneX1, blockX1);
		int rightSideX = Math.min(oneX2, blockX2);
		int bottomSideY = Math.max(oneY1, blockY1);
		int topSideY = Math.min(oneY2, blockY2);
		int area = (rightSideX - leftSideX) * (topSideY - bottomSideY);
		
		if (leftSideX < rightSideX && bottomSideY < topSideY) {
			totalArea -= area;
		}
		
		//Calculate area blocked in the second sign and subtracting it from total area
		leftSideX = Math.max(twoX1, blockX1);
		rightSideX = Math.min(twoX2, blockX2);
		bottomSideY = Math.max(twoY1, blockY1);
		topSideY = Math.min(twoY2, blockY2);
		area = (rightSideX - leftSideX) * (topSideY - bottomSideY);
		
		if (leftSideX < rightSideX && bottomSideY < topSideY) {
			totalArea -= area;
		}
		out.print(totalArea);
		out.close();
		
		

	}

}
