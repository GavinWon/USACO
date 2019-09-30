import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner; 
/*
ID: gavinmw1
LANG: JAVA
TASK: ride
*/
public class ride {

	public static void main(String[] args) throws Exception{
		File input = new File("C:\\Users\\gwong676\\Dropbox\\USACO\\Practice\\src\\ride.in");
		Scanner scan = new Scanner(input);
		File output = new File("C:\\Users\\gwong676\\Dropbox\\USACO\\Practice\\src\\ride.out");
		String comet = scan.nextLine();
		String group = scan.nextLine();
		
		int cometValue = 0;
		for (int index = 0; index < comet.length(); index++)
		{
			char temp = comet.charAt(index);
			cometValue += Character.getNumericValue(temp) - Character.getNumericValue('A') + 1;
		}
		
		int groupValue = 0;
		for (int index = 0; index < group.length(); index++)
		{
			char temp = group.charAt(index);
			groupValue += Character.getNumericValue(temp) - Character.getNumericValue('A') + 1;
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			if (cometValue % 47 == groupValue % 47) {
				writer.write("GO" + '\n');
				System.out.println("GO");
			}
			else {
				writer.write("STAY" + "\n");
				System.out.println("STAY");
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
