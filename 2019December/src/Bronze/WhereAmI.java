package Bronze;
import java.util.*;
import java.io.*;
public class WhereAmI {
	private static int N;
	private static String input;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("whereami.in"));
		PrintWriter out = new PrintWriter(new File("whereami.out"));
		
		N = Integer.parseInt(in.readLine());
		input = in.readLine();
		boolean possible = true;
		int K;
		for (K = N - 1; K >= 1; K--) {
			HashSet<String> set = new HashSet<String>();
			for (int i = 0; i < N; i++) {
				if (K + i > N) break;
				String s = input.substring(i, i + K);
				if (set.contains(s)) {
					possible = false;
					break;
				}
				set.add(s);
			}
			if (possible == false) {
				break;
			}
		}
		System.out.println(K + 1);
		out.println(K + 1);
		out.close();

	}

}
