import java.util.*;


public class Tree {
	
	public static void main (String args[]) {
		TreeMap<String, Integer> tm3 = new TreeMap();
		tm3.put("Hello", 100);
		tm3.put("TreeMap", 89);
		tm3.put("HashSet", 40);
		tm3.put("Arraylist", 30);
		tm3.put("Hello", 200);
		
		System.out.println(tm3);
		
		System.out.println("Iteratoring through the treemap using entrySet");
		
		for (Map.Entry<String, Integer> e : tm3.entrySet()) {
			System.out.print("an entry e: ");
			System.out.print(e.getKey() + " -- ");
			System.out.println(e.getValue());
		}
		System.out.println("------------");
		
		System.out.println("Iteratoring through treemap using keySet");
		
		for (String aKey : tm3.keySet()) {
			System.out.print("an entry e: ");
			System.out.print(aKey + " -- ");
			System.out.println(tm3.get(aKey));
		}
	}
}
