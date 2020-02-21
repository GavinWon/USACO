import java.util.*;
import java.io.*;
public class Race {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new FileReader(new File("race.in")));
		PrintWriter out = new PrintWriter(new File("race.out"));
		int K = in.nextInt();
		int N = in.nextInt();
		int[] sum = new int[50000001];
		for (int i = 1; i <= 50000000; i++) {
			sum[i] = sum[i - 1] + i;
		}
		for (int i = 1; i <= N; i++) {
			int X = in.nextInt();
			int maxSpeed;
			for (maxSpeed = 1; maxSpeed <= 5000000; maxSpeed++) {
				if (sum[maxSpeed] + sum[maxSpeed - 1] - sum[X - 1] >= K) break;
			}
			int distance = sum[maxSpeed] + sum[maxSpeed - 1] - sum[X - 1];
			int remain = K - distance;
			int extraTime = (int) Math.ceil((remain + 0.0) / maxSpeed);
			out.println(maxSpeed + maxSpeed - 1 - (X - 1) + extraTime);
		}
		out.close();
		
		
		
		

	}

}
