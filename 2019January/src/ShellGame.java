import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner; 

public class ShellGame {
	
	static int[] swap1;
	static int[] swap2;
	static int[] guess;

	public static void main(String[] args) throws Exception{
		File input = new File("shell.in");
		Scanner scan = new Scanner(input);
		File output = new File("shell.out");
		int swaps = scan.nextInt();
		swap1 = new int[swaps];
		swap2 = new int[swaps];
		guess = new int[swaps];
		for (int index = 0; index < swaps; index++)
		{
			swap1[index] = scan.nextInt();
			System.out.print(swap1[index] + " ");
			swap2[index] = scan.nextInt();
			System.out.print(swap2[index] + " ");
			guess[index] = scan.nextInt();
			System.out.print(guess[index] + " ");
			System.out.println();
		}
		
		int one = scoreCalculator(1);
		int two = scoreCalculator(2);
		int three = scoreCalculator(3);
		int max;
		
		if (one > two && one > three)
		{
			max = one;
		}
		else if (two > three && two > one)
		{
			max = two;
		}
		else
		{
			max = three;
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(max + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}

	}
	
	public static int scoreCalculator(int beginning)
	{
		int countingScore = 0;
		int currentPebble = beginning;
		for (int index = 0; index < swap1.length; index++)
		{
			if (swap1[index] == currentPebble)
				currentPebble = swap2[index];
			else if (swap2[index] == currentPebble)
				currentPebble = swap1[index];
			
			if (guess[index] == currentPebble)
			{
				countingScore++;
			}
		}
		return countingScore;
	}

}
