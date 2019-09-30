import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner; 
public class Herding {
	static int pos1, pos2, pos3;
	
	public static void main(String[] args) throws Exception {
		File herd = new File("herding.in");
		Scanner scan = new Scanner(herd);
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();
		
		
		
		if (third > first && third > second) {
			pos3 = third;
			if (second > first) {
				pos2 = second;
				pos1 = first;
			}
			else {
				pos2 = first;
				pos1 = second;
			}
		}
		else if (second > third && second > first) {
			pos3 = second;
			if (third > first) {
				pos2 = third;
				pos1 = first;
			}
			else {
				pos2 = first;
				pos1 = third;
			}
		}
		else if (first > third && first > second)
		{
			pos3 = first;
			if (second > third)
			{
				pos2 = second;
				pos1 = third;
			}
			else {
				pos2 = third;
				pos1 = second;
			}
		}
		
		int min, max = 0;
		
		if (pos3 - pos2 == 1 && pos2 - pos1 == 1)
		{
			min = 0;
		}
		else if (pos3 - pos2 == 2 || pos2 - pos1 == 2)
		{
			min = 1;
		}
		else
		{
			min = 2;
		}
		
		max = Math.max(pos3 - pos2 - 1, pos2 - pos1 - 1);
		
		System.out.println(min);
		System.out.println(max);
		
		File f = new File("herding.out");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
			writer.write(min + "\n");
			writer.write(max + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		
		
		
	}
	
	
}


