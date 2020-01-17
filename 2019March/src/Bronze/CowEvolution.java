package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class CowEvolution {
	private static String[][] characteristics = new String[25][25];
	private static HashSet<String> allCharacteristics = new HashSet<String>();
	private static Object[] allChar = new String[allCharacteristics.size()];
	private static int subPop;
	private static int[] numChar;

	public static void main(String[] args) throws Exception {
		File input = new File("evolution.in");
		Scanner scan = new Scanner(input);
		File output = new File("evolution.out");
		
		subPop = scan.nextInt();
		numChar = new int[subPop];
		for (int index = 0; index < subPop; index++)
		{
			// The number of characteristics for a certain population
			int numC = scan.nextInt();
			numChar[index] = numC;
			for (int c = 0; c < numC; c++)
			{
				//stores next Characteristic in c and adds it appropriately
				String nextC = scan.next();
				characteristics[index][c] = nextC;
				allCharacteristics.add(nextC);
			}
			allChar = allCharacteristics.toArray();
		}
		
		boolean proper = true;
		//calls crossing for each combination of two characteristics
		for (int index = 0; index < allChar.length; index++)
		{
			for (int index2 = index + 1; index2 < allChar.length; index2++)
			{
				if (crossing(index, index2))
				{
					proper = false;
				}
			}
		}
		String answer;
		if (proper)
			answer = "yes";
		else
			answer = "no";
		System.out.println(answer);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(answer);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
	}
	
	/**
	 * 
	 * @param a one of the characteristic, index for allCha..
	 * @param b one of the characteristic, index for allCha
	 * @return true if these two characteristics are cross, false if not
	 */
	public static boolean crossing(int a, int b)
	{
		int A = 0, B = 0, AB = 0;
		String char1 = (String) allChar[a];
		String char2 = (String) allChar[b];
		boolean hasA, hasB;
		for (int row = 0; row < subPop; row++)
		{
			hasA = false;
			hasB = false;
			for (int col = 0; col < numChar[row]; col++)
			{
				if (characteristics[row][col].equals(char1))
				{
					hasA = true;
				}
				if (characteristics[row][col].equals(char2))
				{
					hasB = true;
				}
			}
			
			if (hasA && hasB)
				AB++;
			else if(hasA)
				A++;
			else if (hasB)
				B++;
		}
		return AB > 0 && A > 0 && B > 0;
		
		
	}

}

// 1. "cc bb aa" ==> [cc, bb, aa]
// 2. sort above array
// 3. convert array back to stirng
// 4. now compare
