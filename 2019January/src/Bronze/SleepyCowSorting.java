package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class SleepyCowSorting {

	public static void main(String[] args) throws Exception {
		File input = new File("sleepy.in");
		Scanner scan = new Scanner(input);
		File output = new File("sleepy.out");
		
		int numberCows = scan.nextInt();
		int[] cowOrder = new int[numberCows];
		for (int index = 0; index < numberCows; index++) {
			cowOrder[index] = scan.nextInt();
		}
		
		int counter = numberCows - 1;
		for (int index  = numberCows - 2; index >= 0; index--)
		{
			if (cowOrder[index] < cowOrder[index + 1])
				counter--;
			else
				break;
					
		}
		
		System.out.println(counter);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(counter + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
	}

}
