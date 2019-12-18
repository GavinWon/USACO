import java.util.*;
import java.io.*;

public class Meetings {
	
	public static class Cow {
		int weight;
		float position; 
		int dir;
		public Cow (int w, int pos, int d) {
			weight = w;
			position = pos;
			dir = d;
			
		}
	}
	
	public static class positionComparator implements Comparator<Cow> {

		public int compare(Cow arg, Cow arg1) {
			return (int) (arg.position - arg1.position);
		}
		
	}
	
	private static int N, L;
	private static Cow[] cows;
	private static int totalWeight = 0;
	

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("meetings.in"));
		PrintWriter out = new PrintWriter(new File("meetings.out"));
		
		N = in.nextInt();
		L = in.nextInt();
		cows = new Cow[N];
		
		
		for (int i = 0; i < N; i++) {
			int weight = in.nextInt();
			int position = in.nextInt();
			int dir = in.nextInt();
			totalWeight += weight;
			cows[i] = new Cow(weight, position, dir);
			//System.out.println(cows[i].weight + " " + cows[i].position + " " + cows[i].dir);
		}
		Arrays.sort(cows, new positionComparator()); 
		float weightInBarn = 0;
		int meetingsCount = 0;
		int leftMostCowNum = 0;
		int rightMostCowNum = N - 1;
		double halfWeight = totalWeight / 2.0;
		while(weightInBarn < halfWeight) {
			float LcowTime = Integer.MAX_VALUE; float RcowTime = Integer.MAX_VALUE;
			if (cows[leftMostCowNum].dir == -1) LcowTime = cows[leftMostCowNum].position;
			if (cows[rightMostCowNum].dir == 1) RcowTime = L - cows[rightMostCowNum].position;
			float timeTilNextC = Math.min(LcowTime, RcowTime);
			
			for (int i = leftMostCowNum; i <= rightMostCowNum - 1; i++) {
				if (cows[i].dir == 1 && cows[i + 1].dir == -1) {//cow[i] and cow[i + 1] will collide
					timeTilNextC = Math.min(timeTilNextC, (cows[i + 1].position - cows[i].position) / 2.0f);
				}
			}
			//System.out.println("Time untill next collision: " + timeTilNextC);
			
			for (int i = leftMostCowNum; i <= rightMostCowNum; i++) {
				cows[i].position += cows[i].dir * timeTilNextC;
				if (cows[i].position == 0) {
					leftMostCowNum++;
					weightInBarn += cows[i].weight;
				}
				if (cows[i].position == L) {
					rightMostCowNum--;
					weightInBarn += cows[i].weight;
				}
			}
			
			for (int i = leftMostCowNum; i <= rightMostCowNum - 1; i++) {
				if (cows[i].position == cows[i + 1].position) { //check whether its possible for two cows to have same direction + same position
					cows[i].dir = -1;
					cows[i + 1].dir = 1;
					//System.out.println("Cow at " + i + " meet with next cow at " + cows[i].position);
					meetingsCount++;
				}
			}
		}
		
		System.out.println(meetingsCount);
		out.println(meetingsCount);
		out.close();

	}

}
