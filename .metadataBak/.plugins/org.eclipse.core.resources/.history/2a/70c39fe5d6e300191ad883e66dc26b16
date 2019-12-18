package Bronze;

import java.util.*;
import java.io.*;

public class BeautyPageant {
	
	public static int rows; static int cols;
	private static int[][] hide;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("//Users//gavinwong//Dropbox//USACO//2011November//src//Bronze//pageant.in"));
		PrintWriter out = new PrintWriter(new File("pageant.out"));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		rows = Integer.parseInt(st.nextToken());
		cols = Integer.parseInt(st.nextToken());
		hide = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			String line = in.readLine();
			for (int j = 0; j < cols; j++) {
				hide[i][j] = line.charAt(j);
			}
		}
		System.out.print(rows + " " + cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(hide[i][j]);
			}
			System.out.println("Like what the f, i steped in tihs");
		}
	}

	}

