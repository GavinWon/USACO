package TreeSet;
import java.util.TreeSet;

public class TreeSetPractice {
	public static class Cow implements Comparable<Cow>{
		int rank;
		int herp;
		Cow(int rank, int herp) {
			this.rank = rank;
			this.herp = herp;
		}
		public int compareTo(Cow arg0) {
			return rank - arg0.rank;
		}
		
	}
	public static void main(String args[]) {
		TreeSet<String> myTreeSet = new TreeSet<String>();	
		myTreeSet.add("A");
		myTreeSet.add("E");
		myTreeSet.add("Z");
		myTreeSet.add("Z");
		myTreeSet.add("J");
		System.out.println(myTreeSet);
		TreeSet<String> herp = (TreeSet<String>) myTreeSet.tailSet("E", false);
		System.out.println(herp);
		
		TreeSet<Cow> myCowSet = new TreeSet<Cow>();
		myCowSet.add(new Cow(5, 2));
		myCowSet.add(new Cow(5, 5));
		myCowSet.add(new Cow(10, 2));
		myCowSet.add(new Cow(1, 3));
		myCowSet.first().rank = 100;
		myCowSet.last().rank = 0;
		for (Cow c : myCowSet) {
			System.out.println(c.rank + " " + c.herp);
		}
	}
}
