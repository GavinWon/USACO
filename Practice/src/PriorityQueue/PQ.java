package PriorityQueue;
import java.util.*;

public class PQ {
	public static class cow implements Comparable<cow> {
		int id;
		int startingTime;
		cow (int i, int s) {
			id = i;
			startingTime = s;
		}
		public int compareTo(cow c) {
			return startingTime - c.startingTime;
		}
		
		
	}
	public static void main(String[] args) {
		cow[] cows = new cow[5];
		cows[0] = new cow(1,4);
		cows[1] = new cow(2,5);
		cows[2] = new cow(3,1);
		cows[3] = new cow(4,2);
		cows[4] = new cow(5,7);
		
		
		Arrays.sort(cows);
	}

}
