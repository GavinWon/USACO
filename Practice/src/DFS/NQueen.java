package DFS;

public class NQueen {
	static int count = 0;
	static int[] rowA = new int[9]; // start from index of 1

	public static void main(String[] args) {
		dfs(1);
		System.out.println(count);

	}
	
	public static int dfs(int col) {
		if (col > 8) {
			count++;
		}
		else {
			for (int row = 1; row < 9; row++) {
				if (!isAttacked(row, col)) {
					rowA[col] = row;
					dfs(col + 1);
				}
			}
		}
		return 0;

	}
	
	public static boolean isAttacked(int row, int col) {
		for (int c = 0; c < col; c++)
		{
			if (rowA[c] == row) return true;
			if (col - c == row - rowA[c] ) return true;
			if (col - c == -(row - rowA[c])) return true;
			
		}
		return false;
	}

}
