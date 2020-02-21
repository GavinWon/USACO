import java.io.*;
import java.util.*;	

public class Berries {
	static int N, K;
	static Integer[] B;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new FileReader(new File("berries.in")));
		PrintWriter out = new PrintWriter(new File("berries.out"));

		/*
		 * 1. Read problem size
		 */
		String line = in.readLine();
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		B = new Integer[N];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			int b = Integer.parseInt(st.nextToken());
			B[i] = b;
		}

		sortBerries(B);

		int totalBerries = 0;
		for (int i = 0; i < N; i++) {
			totalBerries += B[i];
		}

		int basketSize = totalBerries / K + 1;

		while (basketSize > 1) {
			if (splitBerries(basketSize))
				break;
			basketSize--;
		}

		int total = findTotal(basketSize);
		out.print(total);

		in.close();
		out.close();

	}

	public class ReverseComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer a, Integer b) {
			return b - a;
		}
	}

	static <T> void sortBerries(T[] Berries) {
		Arrays.sort(Berries,  Collections.reverseOrder());
	}

	static boolean splitBerries(int numBerries) {
		int numBaskets = 0;
		for (Integer berries : B) {
			numBaskets += berries / numBerries;
		}
		return numBaskets >= K;
	}

	static int findTotal(int maxBasketSize) {
		List<Integer> bb = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			int count = B[i] / maxBasketSize;
			if (count <= 0) {
				bb.add(B[i]);
				continue;
			}

			int backetSize = B[i] / count;
			int leftOver = B[i] % backetSize;

			for (int j = 0; j < count; j++) {
				if (j < leftOver) {
					bb.add(backetSize + 1);
				} else {
					bb.add(backetSize);
				}
			}
		}

		Collections.sort(bb,  Collections.reverseOrder());

		int total = 0;
		for (int i = K/2; i < K; i++) {
			total += bb.get(i);
		}
		return total;
	}
}
