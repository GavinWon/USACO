package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class BucketBrigade {

	public static void main(String[] args) throws Exception{
		File input = new File("buckets.in");
		Scanner scan = new Scanner(input);
		File output = new File("buckets.out");
		int bX = 0; 
		int bY = 0;
		int lX = 0;
		int lY = 0;
		int rX = 0;
		int rY = 0;
		char[][] c = new char[10][10];
		
		for (int index = 0; index < 10; index++){
			String s = scan.nextLine();
			for (int index2 = 0; index2 < 10; index2++) {
				c[index][index2] = s.charAt(index2);
				if (c[index][index2] == 'B')
				{
					bX = index2;
					bY = index;
				}
				if (c[index][index2] == 'L')
				{
					lX = index2;
					lY = index;
				}
				if(c[index][index2] == 'R')
				{
					rX = index2;
					rY = index;
				}
			}
		}
		int minimum;
		minimum = Math.abs(lX - bX) + Math.abs(lY - bY) - 1;
		if (bX == lX && bX == rX)
			if ((rY > bY && rY < lY) || (rY < bY && rY > lY))
				minimum += 2;
		if (bY == lY && bY == rY)
			if ((rX > bX && rX < lX) || (rX < bX && rX > lX))
			minimum += 2;
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(minimum + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
	}

}
