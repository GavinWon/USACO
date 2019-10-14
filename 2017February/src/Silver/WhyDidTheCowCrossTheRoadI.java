package Silver;
import java.util.*;
import java.io.*;

public class WhyDidTheCowCrossTheRoadI {
	
	static class Animal implements Comparable<Animal>{
		int start, end, type;
		//Cow = 1, Chicken = 2
		public Animal (int s,int e,int t) {
			start = s;
			end = e;
			type = t;
		}
		public int compareTo(Animal a) {
			if (start != a.start)
				return start - a.start;
			else
				return type - a.type; //Cows will always be first
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2017February\\src\\Silver\\helpcross.in"));
		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2017February\\src\\Silver\\helpcross.out"));
//		Scanner in = new Scanner(new FileReader("helpcross.in"));
//		PrintWriter out = new PrintWriter(new File("helpcross.out"));
		
		
		int C = in.nextInt();
		int N = in.nextInt();
		Animal[] animals = new Animal[C + N];
		
		for (int i = 0; i < C; i++) { //putting the chickens into the array
			int time = in.nextInt();
			animals[i] = new Animal(time, time, 2); 
		}
		
		for (int i = C; i < C + N; i++) { //putting the cows in the array
			int startingTime = in.nextInt();
			int endingTime = in.nextInt();
			animals[i] = new Animal(startingTime, endingTime, 1);
		}
		
		Arrays.sort(animals);
		int pairCountings = 0;
		PriorityQueue<Integer> endTimeQ = new PriorityQueue<Integer>();
		for (int i = 0; i < C + N; i++) {
			if (animals[i].type == 1) 
				endTimeQ.add(animals[i].end);
			else {
				while(!endTimeQ.isEmpty()) {
					int nextCowEnd = endTimeQ.remove();
					if (nextCowEnd >= animals[i].end) {
						pairCountings++;
						break;
					}
				}
			}
		}
		out.println(pairCountings);
		out.close();

	}

}
