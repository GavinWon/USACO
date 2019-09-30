import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MilkingOrder {

	static int[] position;
	static int[] status;
	static int l;
	
	public static void main(String[] args) throws IOException{
		File input = new File("milkorder.in");
		Scanner scan = new Scanner(input);
		File output = new File("milkorder.out");
		
		l = scan.nextInt();
		position = new int[l + 1];
		status = new int[scan.nextInt()];
		int K = scan.nextInt();
		for (int i = 0; i < status.length; i++)
		{
			status[i] = scan.nextInt();
		}
		
		for (int i = 0; i < K; i++)
		{
			int cow = scan.nextInt();
			position[scan.nextInt()] = cow;
		}
		
		
		
		int p = 1;
		while (!satisfyAllConditions(p))
			p++;
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(output);
			writer.print(p);
			System.out.println(p);
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		

	}
	
	public static boolean satisfyAllConditions(int p)
	{
		int[] tempArray = new int[l + 1];
		System.arraycopy(position, 0, tempArray, 0, l);
		if (tempArray[p] > 1)
		{
			return false;
		}
		tempArray[p] = 1;
		int nextSpace = 1;
		for (int rank = 0; rank < status.length; rank++)
		{
			boolean cont = true;
			int nextCow = status[rank];
			if (nextCow == 1)
				continue;
			for (int i = 1; i < position.length; i++) {
				if (nextCow == tempArray[i]) {
					nextSpace = i + 1;
					cont = false;
				}
			}
			if (cont) {
				while (nextSpace <= position.length && tempArray[nextSpace] != 0) 
					nextSpace++;
				if (nextSpace > l)
					return false;
				tempArray[nextSpace] = nextCow;
			}

		}
		
		int pos = 1;
		for (int rank = 0; rank < status.length; rank++) {
			while (pos < position.length && tempArray[pos] != status[rank])
				pos++;
			if (pos >= position.length)
				return false;
		}
		
		
		return true;
	}

}
