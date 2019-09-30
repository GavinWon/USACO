import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class CowHopscotch {
	
	static int r, c;
	static char[][] inputA;

	public static void main(String[] args) throws Exception {
		File input = new File("hopscotch.in");
		Scanner scan = new Scanner(input);
		File output = new File("hopscotch.out");
		
		r = scan.nextInt();
		c = scan.nextInt();
		inputA = new char[r][c];
		
		scan.nextLine();
		for (int i = 0; i < r; i++)
		{
			String s = scan.nextLine();
			System.out.println(s);
			for (int i2 = 0; i2 < c; i2++)
				inputA[i][i2] = s.charAt(i2);
		}
		
		int possibleWays = checkWays(0,0);
		System.out.println(possibleWays);
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(possibleWays + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		
		
	}
	
	public static int checkWays (int row, int col)
	{
		if (row == r - 1 && col == c - 1)
			return 1;
		int sum = 0;
		for (int r1 = row + 1; r1 < r; r1++) {
			for (int c1 = col + 1;c1 < c; c1++)
			{
				if (inputA[row][col] != inputA[r1][c1])
					sum += checkWays(r1, c1);
			}
			
		}
		return sum;
	}


}
