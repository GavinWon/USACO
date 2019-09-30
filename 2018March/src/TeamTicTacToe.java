import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class TeamTicTacToe {
	
	static char[][] inputA = new char[3][3];
	static HashSet<Character> temp = new HashSet<Character>();
	static HashSet<Character> singleW = new HashSet<Character>();
	static HashSet<String> doubleW = new HashSet<String>();

	public static void main(String[] args) throws Exception {
		File input = new File("tttt.in");
		Scanner scan = new Scanner(input);
		File output = new File("tttt.out");
		
		for (int r = 0; r < 3; r++) {
			String s = scan.nextLine();
			inputA[r][0] = s.charAt(0);
			inputA[r][1] = s.charAt(1);
			inputA[r][2] = s.charAt(2);
		}
		
		//Goes through each row
		for (int r = 0; r < 3; r++)
		{
			for (int c = 0; c < 3; c++) {
				temp.add(inputA[r][c]);
			}
			increment();
		}
		
		//Goes through each coloumn
		
		for (int c = 0; c < 3; c++) 
		{
			for (int r = 0; r < 3; r++)
			{
				temp.add(inputA[r][c]);
			}
			increment();
		}
		
		//Goes through diagonal (leftTop to rightBottom)
		for (int i = 0; i < 3; i++)
		{
			temp.add(inputA[i][i]);
		}
		increment();
		
		//Goes through diagnoal (rightTop to rightBottom)
		for (int r = 2; r >= 0; r-- )
		{
			for (int c = 0; c < 2; c++)
			{
				temp.add(inputA[r][c]);
			}
		}
			
			
			
		System.out.println(singleW.size());
		System.out.println(doubleW.size());
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(singleW.size() + "\n");
			writer.write(doubleW.size() + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}

	}
	
	public static void increment()
	{
		if (temp.size() == 1)
		{
			for (char c : temp)
			{
				singleW.add(c);
			}
		}
		else if (temp.size() == 2)
		{
			String next = "";
			for (char c : temp)
			{
				next += c;
			}
//			char c = next.charAt(0);
//			char c1 = next.charAt(1);
//			if (Character.getNumericValue(c) - Character.getNumericValue(c1) > 0)
//			{
//				String next2 = "";
//				next2 += c1;
//				next2 += c;
//				doubleW.add(next2);
//			} else
				doubleW.add(next);
		}
		temp.clear();
	}

}
