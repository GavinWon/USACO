package Bronze;

import java.util.*;
import java.io.*;

public class MixingMilk {
	
	private static int c1, m1;
	private static int c2, m2;
	private static int c3, m3;

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new FileReader("mixmilk.in"));
		PrintWriter out = new PrintWriter(new File("mixmilk.out"));
//		Scanner in = new Scanner(new FileReader("D:\\Repos\\USACO\\2018December\\src\\Bronze\\mixmilk.in"));
//		PrintWriter out = new PrintWriter(new File("D:\\Repos\\USACO\\2018December\\src\\Bronze\\mixmilk.out"));
		
		c1 = in.nextInt();
		m1 = in.nextInt();
		c2 = in.nextInt();
		m2 = in.nextInt();
		c3 = in.nextInt();
		m3 = in.nextInt();
		
		for (int i = 1; i <= 33; i++) {
			pour1To2();
			pour2To3();
			pour3To1();
		}
		
		pour1To2();
		
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		out.println(m1);
		out.println(m2);
		out.println(m3);
		out.close();

	}
	
	public static void pour1To2() {
		int empty = c2 - m2;
		if (m1 > empty) {
			m1 -= empty;
			m2 = c2;
		} else {
			m2 += m1;
			m1 = 0;
		}
	}
	
	public static void pour2To3() {
		int empty = c3 - m3;
		if (m2 > empty) {
			m2 -= empty;
			m3 = c3;
		} else {
			m3 += m2;
			m2 = 0;
		}
	}
	
	public static void pour3To1() {
		int empty = c1 - m1;
		if (m3 > empty) {
			m3 -= empty;
			m1 = c1;
		} else {
			m1 += m3;
			m3 = 0;
		}
	}

}
