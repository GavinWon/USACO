package Bronze;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class MeasuringTraffic {

	public static void main(String[] args) throws Exception {
		File traffic = new File("traffic.in");
		Stack<String[]> beginning = new Stack<String[]>();
		Stack<String[]> last = new Stack<String[]>();
		int min1 = 0, max1 = 0, min2 = 0, max2 = 0;
		Scanner scan = new Scanner(traffic);
		int numberMiles = scan.nextInt();

		String[][] input = new String[numberMiles][3];
		for (int index = 0; index < numberMiles; index++) {
			for (int i = 0; i < 3; i++) {
				input[index][i] = scan.next();
				System.out.println(input[index][i]);
			}
		}

		for (int index = 1; index < numberMiles; index++) {
			if (input[index][0].equals("none") && input[index - 1][0].equals("none")) {
				int newMin = Math.max(Integer.parseInt(input[index][1]), Integer.parseInt(input[index - 1][1]));
				int newMax = Math.min(Integer.parseInt(input[index][2]), Integer.parseInt(input[index - 1][2]));
				input[index][1] = newMin + "";
				input[index][2] = newMax + "";
				input[index - 1][1] = newMin + "";
				input[index - 1][2] = newMax + "";
			}
		}
		
		for (int index = 0; index < numberMiles; index++) {
			if (!input[index][0].equals("none")) {
				beginning.push(input[index]);
			} else {
				min1 = Integer.parseInt(input[index][1]);
				max1 = Integer.parseInt(input[index][2]);
				
				while (!beginning.isEmpty()) {
					String[] temp = beginning.pop();
					
					if (temp[0].equals("off")) {
						min1 += Integer.parseInt(temp[1]);
						max1 += Integer.parseInt(temp[2]);
					}
					else
					{
						min1 -= Integer.parseInt(temp[2]);
						max1 -= Integer.parseInt(temp[1]);
					}
				}
				break;
			}
		}
		System.out.println(min1);
		System.out.println(max1);
		
		for (int index = numberMiles - 1; index >= 0; index--) {
			if (!input[index][0].equals("none")) {
				last.push(input[index]);
			} else {
				min2 = Integer.parseInt(input[index][1]);
				max2 = Integer.parseInt(input[index][2]);
				
				while (!last.isEmpty()) {
					String[] temp = last.pop();
					
					if (temp[0].equals("off")) {
						min2 -= Integer.parseInt(temp[2]);
						max2 -= Integer.parseInt(temp[1]);
					}
					else
					{
						min2 += Integer.parseInt(temp[1]);
						max2 += Integer.parseInt(temp[2]);
					}
				}
				break;
			}
		}
		System.out.println(min2);
		System.out.println(max2);
		
		File f = new File("traffic.out");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(f);
			writer.write(min1 + " " + max1 + "\n");
			writer.write(min2 + " " + max2);
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		
	}

}
