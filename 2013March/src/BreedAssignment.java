import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BreedAssignment {
	
	static int N; static int K; static int count;
	static int[] assignment;
	static char[][] relations;
	

	public static void main(String[] args) throws Exception {
//		File input = new File("/Users/Gavin/Dropbox/USACO/March2013/src/assign.in");
//		Scanner scan = new Scanner(input);
//		File output = new File("/Users/Gavin/Dropbox/USACO/March2013/src/assign.out");
		File input = new File("assign.in");
		Scanner scan = new Scanner(input);
		File output = new File("assign.out");
		
		N = scan.nextInt();
		K = scan.nextInt();
		assignment = new int[N + 1];
		relations = new char[N + 1][N + 1];
		scan.nextLine();
		for (int i = 0; i < K; i++) {
			StringTokenizer s = new StringTokenizer(scan.nextLine());
			char SorD = s.nextToken().charAt(0);
			int cow1 = Integer.parseInt(s.nextToken());
			int cow2 = Integer.parseInt(s.nextToken());
			relations[cow1][cow2] = relations[cow2][cow1] = SorD;
			
		}
		assignment[1] = 1;
		dfs(2);
		count *= 3;
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(output);
			writer.write(count + "\n");
			writer.close();
		}
		catch (Exception e){
			System.out.println(e);
			System.out.println("Error");
			System.exit(1);
		}
		scan.close();
		

	}
	public static void dfs (int currentCow) {
		if (currentCow > N) {
			count++;
			return;
		} 
		HashSet<Integer> possible = new HashSet<Integer>(Arrays.asList(1,2,3));
		for (int i  = 1; i < currentCow; i++) {
			if (relations[currentCow][i] == 'S') {
				for (int b = 1; b <= 3; b++) {
					if (b != assignment[i])
						possible.remove(b);
				}
			} else if (relations[currentCow][i] == 'D')
			{
				possible.remove(assignment[i]);
			}
		}
		for (int b : possible) {
			assignment[currentCow] = b;
			dfs(currentCow + 1);
		}
	}

}
