package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Revegetate {

	public static void main(String[] args) throws Exception {
		File revegetate = new File("revegetate.in");
		Scanner scan = new Scanner(revegetate);
	
		int numPastures = scan.nextInt();
		int numCows = scan.nextInt();
		int[] outPut = new int[numPastures];
		for (int index = 0; index < outPut.length; index++) {
			outPut[index] = 1;
		}
		
		int[][] favPast = new int[numCows][2];
		
		for (int i = 0; i < numCows; i++)
		{
			for (int index = 0; index < 2; index++)
			{
				favPast[i][index] = scan.nextInt();
			}
		}
		
		//Arrays.asList(favPast[i]).contains(index) && Arrays.asList(favPast[i]).contains(ind)
		//The constant pasture
		for (int ind = 1; ind <= numPastures; ind++)
		{
			//Changing pasture
			for (int index = ind + 1; index <= numPastures; index++)
			{
				//Iterating through each array in double array
				for (int i = 0; i < numCows; i++)
				{
					int first = favPast[i][0];
					int second = favPast[i][1];
					if (first == index && second == ind || first == ind && second == index)
					{
						if (outPut[index - 1] == outPut[ind - 1])
						{
							outPut[index - 1] = outPut[ind - 1] + 1;
							break;
						}
	
					}
				}
			}
			for (int seed : outPut)
			{
				System.out.print(seed);
			}
			System.out.println();
		}
		
		
		File f = new File("revegetate.out");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
			for (int seed : outPut)
			{
				writer.write(seed + "");
			}
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		
		
		
		
		
		

	}

}
