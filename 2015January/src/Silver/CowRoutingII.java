package Silver;

import java.util.*;
import java.io.*;

public class CowRoutingII {
	
	private static int cityA, cityB, N;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("cowroute.in"));
		PrintWriter out = new PrintWriter(new File("cowroute.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2015January\\src\\Silver\\cowroute.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2015January\\src\\Silver\\cowroute.out"));
		
		//Declare all variables
		cityA = in.nextInt();
		cityB = in.nextInt();
		N = in.nextInt();
		
		
		//Key - City, Value - Cost
		HashMap<Integer, Integer> fromA = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> toB = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			int cost = in.nextInt();
			int numCities = in.nextInt();
			int aPos = -1, bPos = -1; //remember the position of city a and b position in the route
			int[] cities = new int[numCities];
			for (int j = 0; j < numCities; j++) {
				cities[j] = in.nextInt();
				if (cities[j] == cityA) aPos = j;
				if (cities[j] == cityB) bPos = j;
			}
		
			//adding cities that can be reached from city A into HashSet
			if (aPos != -1) {
				for (int index = aPos + 1; index < numCities; index++) {
					if (fromA.containsKey(cities[index])) {
						fromA.put(cities[index], Math.min(cost, fromA.get(cities[index])));
					} else {
						fromA.put(cities[index], cost);
					}
				}
			}
			
			//adding cities that can go to city B into HashSet
			if (bPos != -1) {
				for (int index = 0; index < bPos; index++) {
					if (toB.containsKey(cities[index])) {
						toB.put(cities[index], Math.min(cost, toB.get(cities[index])));
					} else {
						toB.put(cities[index], cost);
					}
				}
			}
		}
		
		toB.put(cityB, 0); //Needs to be added in the case that there is ONE connecting flight between the two cities
		int totalCost = Integer.MAX_VALUE;
		//Calculating the lowest price possible
		for (int city : fromA.keySet()) {
			if (toB.containsKey(city)) {
				int costFirst = fromA.get(city);
				int costSecond = toB.get(city);
				totalCost = Math.min(totalCost, costFirst + costSecond);
			}
			
		}
		if (totalCost == Integer.MAX_VALUE)
			out.print(-1);
		else
			out.print(totalCost);
		
		out.close();

	}

}
