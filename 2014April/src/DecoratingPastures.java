import java.util.*;
import java.io.*;

public class DecoratingPastures {
	
	static int N, M;
	static HashSet<Integer> connections[];
	static char[] sign;
	static int countJ, countF;
	static int totalJ = 0;;

	public static void main(String[] args) throws IOException {
//		Scanner in = new Scanner(new FileReader("C:\\Users\\Gavin\\Dropbox\\USACO\\2014April\\src\\decorate.in"));
//		PrintWriter out = new PrintWriter((new FileWriter("C:\\Users\\Gavin\\Dropbox\\USACO\\2014April\\src\\decorate.out")));
		Scanner in = new Scanner(new FileReader("decorate.in"));
		PrintWriter out = new PrintWriter(new FileWriter("decorate.out"));
		
		N = in.nextInt();
		M = in.nextInt();
		connections = new HashSet[N + 1];
		sign = new char[N + 1];
		
		for (int i = 1; i <= N; i++ ) {
			connections[i] = new HashSet<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			int past1 = in.nextInt();
			int past2 = in.nextInt();
			connections[past1].add(past2);
			connections[past2].add(past1);
		}
		
		for (int i = 1; i <= N; i++) {
			boolean validA;
			if (sign[i] != 'F' && sign[i] != 'J') { 
				countF = 0;
				countJ = 0;
				validA = floodfill('F', i);
			} else {
				continue;
			}
			
			if (validA) {
				countJ = Integer.max(countF, countJ);
				totalJ += countJ;
			} else {
				totalJ = -1;
				break;
			}
		}
		
		
		out.println(totalJ);
		out.close();
		
		
		
		
		
	}
	
	public static boolean floodfill(char letter, int pastureID) {
		sign[pastureID] = letter;
		char nextLetter;
		if (letter == 'F') { 
			nextLetter = 'J';
			countF++;
		}
		else {
			nextLetter = 'F';
			countJ++;
		}
		for (int index : connections[pastureID]) {	
			if(sign[index] == sign[pastureID])
				return false;
			else if (sign[index] != 'F' && sign[index] != 'J') {
				boolean doable = floodfill(nextLetter, index);
				if(!doable) return false;
			}
		}
		return true;
	}

}
