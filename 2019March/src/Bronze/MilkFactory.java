package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MilkFactory {
	static int[] original;
	static int[] next;
	static int totalStation;
	
	public static void main(String[] args) throws Exception{
		File input = new File("factory.in");
		Scanner scan = new Scanner(input);
		File output = new File("factory.out");
		
		totalStation = scan.nextInt();
		original = new int[totalStation - 1];
		next = new int[totalStation - 1];
		for (int index = 0; index < totalStation - 1; index++)
		{
			original[index] = scan.nextInt();
			next[index] = scan.nextInt();
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			for (int index = 1; index <= totalStation; index++)
			{
				int nodesReach = nodesReachMe(index);
				if (nodesReach == totalStation - 1) {
					writer.write(index + "\n");
					break;
				}
				if (index == totalStation)
				{
					writer.write(-1 + "\n");
					break;
				}
			}
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
	}
	
	public static int nodesReachMe(int me) {
		int counter = 0;
		Set<Integer> s = new HashSet<Integer>();
		for (int index = 0; index < totalStation - 1; index++)
		{
			s.add(original[index]);
		}
		if (s.size() < original.length)
		{
			return -1;
		}
		
		for (int index = 0; index < totalStation - 1; index++) {
			if (next[index] == me)
			{
				counter += 1;
				counter += nodesReachMe(original[index]);
			}
		}
		
		return counter;
	}

}
