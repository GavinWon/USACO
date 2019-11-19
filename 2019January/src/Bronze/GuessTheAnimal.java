package Bronze;
import java.util.*;
import java.io.*;

public class GuessTheAnimal {
	
	private static int N, maxYes = -1;
	private static String[][] input;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("guess.in"));
		PrintWriter out = new PrintWriter(new File("guess.out"));
//		BufferedReader in = new BufferedReader(new FileReader("/Users/gavinwong/Desktop/Repos/USACO/2019January/src/guess.in"));
//		PrintWriter out = new PrintWriter(new File("/Users/gavinwong/Desktop/Repos/USACO/2019January/src/guess.out"));
		
		
		//taking in the input and storing them
		N = Integer.parseInt(in.readLine());
		input = new String[N][];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			String name = st.nextToken();
			int numFeatures = Integer.parseInt(st.nextToken());
			input[i] = new String[numFeatures];
			int j = 0;
			while(st.hasMoreTokens()) {
				input[i][j] = st.nextToken(); 
				System.out.print(input[i][j] + " ");
				j++;
			}
			System.out.println();
		}
		
		//calculating maximum of yes
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				String[] first = input[i];
				String[] second = input[j];
				int similarities = 0;
				for (int index1 = 0; index1 < first.length; index1++) {
					for (int index2 = 0; index2 < second.length; index2++) {
						if (first[index1].equals(second[index2])) similarities++;
					}
				}
				maxYes = Math.max(maxYes, similarities);
				
			}
		}
		
		out.println(maxYes + 1);
		out.close();

	}

}
