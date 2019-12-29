import java.util.*;
public class HaybaleFeast {
	
	public class hay {
		int flavor;
		int spicy;
		public hay (int flavor, int spicy) {
			this.flavor = flavor;
			this.spicy = spicy;
			
		}
	}
	
	private static int N, M;
	private static hay[] haybales;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		int start = 1; int end = 1000000000;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (attempt(mid)) {
				end = 1;
			}
		}
		
		

	}

	
	public static boolean attempt(int spiciness) {
		return false;
	}
}
