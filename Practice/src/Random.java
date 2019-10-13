import java.io.*;
import java.util.StringTokenizer;

public class Random {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("//Users//gavinwong//Dropbox//USACO//2011November//src//Bronze//pageant.in"));
		StringTokenizer s1 = new StringTokenizer(in.readLine());
		System.out.println(s1.nextToken());
		System.out.println(s1.nextToken());
		//System.out.println(in.read());
	}

}
