package Silver;
import java.util.*;
import java.io.*;

public class Triangles {

	public static void main(String[] args) throws IOException {
		init();
		readInput();
		solve();
		cleanup();
	}

	public static Comparator<Point> cmpX = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			int diff = o1.x - o2.x;
			return diff != 0 ? diff : o1.y - o2.y;
		}
	};
	public static Comparator<Point> cmpY = new Comparator<Point>() {
		@Override
		public int compare(Point o1, Point o2) {
			int diff = o1.y - o2.y;
			return diff != 0 ? diff : o1.x - o2.x;
		}
	};

	public static int solve() {
		// 0: process input
		Posts.sort(cmpX);
		List<Point> sortedPostsX = new ArrayList<>(Posts);
		List<Point> sortedPostsY = new ArrayList<>(Posts);
		sortedPostsY.sort(cmpY);

		printPosts("Posts", Posts);
		printPosts("sortedPostsX", sortedPostsX);
		printPosts("sortedPostsY", sortedPostsY);

		assert N == Posts.size();
		assert N == sortedPostsX.size();
		assert N == sortedPostsY.size();

		int count = 0;
		int totalAreaX2 = 0;

		// 1. find all triangles
		for (int p1 = 0; p1 < N; p1++) {
			for (int p2 = p1 + 1; p2 < N; p2++) {
				for (int p3 = p2 + 1; p3 < N; p3++) {
					Point P1 = Posts.get(p1);
					Point P2 = Posts.get(p2);
					Point P3 = Posts.get(p3);
					if (isValid(P1, P2, P3)) {
						count++;
						println("T%d: %s, %s, %s,  valid: %s", count, P1, P2, P3, true);
						totalAreaX2 += getAreaX2(P1, P2, P3);
					}
				}
			}
		}

		println("# of triangles: %d, total area X2: %d", count, totalAreaX2);
		out.print(totalAreaX2);

		return 0;
	}

	public static boolean isValid(int a, int b, int c) {
		boolean ok = a == b;
		ok ^= b == c;
		ok ^= c == a;
		return ok;
	}

	public static boolean isValid(Point a, Point b, Point c) {
		return isValid(a.x, b.x, c.x) && isValid(a.y, b.y, c.y);
	}

	public static int getAreaX2(Point a, Point b, Point c) {
		int x = a.x != b.x ? a.x - b.x : a.x - c.x;
		int y = a.y != b.y ? a.y - b.y : a.y - c.y;
		int area = x * y;
		return area > 0 ? area : -area;
	}

	public static void printPosts(String title, List<Point> posts) {
		// println("%s", Arrays.toString(posts));
		println("%s:  %s", title, posts);
	}

	public static int N;
	public static List<Point> Posts;

	public static boolean readInput() throws IOException {
		String line;
		if ((line = in.readLine()) == null) return false;


		// 1. N
		StringTokenizer st = new StringTokenizer(line);
		N = Integer.parseInt(st.nextToken());

		Posts = new ArrayList<>();

		// 2. N x Posts
		for (int i = 0; i < N; i++) {
			if ((line = in.readLine()) == null) return false;
			st = new StringTokenizer(line);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Posts.add(new Point(x, y));
		}

		return true;
	}

	/*******************************************************************************************************/
	public static class Point {
		public int x, y;
		public Point(int x, int y) { this.x = x; this.y = y; }
		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}

		@Override
		public boolean equals(Object other) {
			Point that = (Point) other;
			if (this == that) return true;
			return this.x == that.x && this.y == that.y;
		}
	}

	/*******************************************************************************************************
	 * Utility functions
	 */
	public static BufferedReader in;
	public static PrintWriter out;

	public static void init() throws IOException {
		in = new BufferedReader(new FileReader(new File("triangles.in")));
		out = new PrintWriter(new File("triangles.out"));
	}

	public static void cleanup() {
		try {
			if (in != null) in.close();
			if (out != null) out.close();
		} catch (IOException e) {
			// ignore error
		}
	}

	public static boolean log = true;
	static void println(String format, Object... args) { if (log) System.out.println(String.format(format, args)); }
	static void print(String format, Object... args) { 	if (log) System.out.print(String.format(format, args)); }

	static boolean within(int x, int min, int max) { return min <= x && x <= max; }
}
