package Silver;
import java.io.*;
import java.util.*;

public class MilkPails {
	static class state {
		int op, x, y;
		state (int o, int l, int r) {
			op = o;
			x = l;
			y = r;
		}
	}
	static int X, Y, K, M;	
	static boolean[][] visited;
	static int minDelta;
	public static void main(String[] args) throws Exception{
//		Scanner input = new Scanner(new File("C://Users//Gavin//Dropbox//USACO//2016February//src//Silver//pails.in"));
//		PrintWriter out = new PrintWriter(new File("C://Users//Gavin//Dropbox//USACO//2016February//src//Silver//pails.out"));
		Scanner input = new Scanner(new File("pails.in"));
		PrintWriter out = new PrintWriter(new File("pails.out"));
		X = input.nextInt();
		Y = input.nextInt();
		K = input.nextInt();
		M = input.nextInt();
		visited = new boolean[X + 1][Y + 1];
		minDelta = Integer.MAX_VALUE;
		bfs();
		
		out.println(minDelta);
		out.close();
		

	}
	
	public static void bfs() {
		Queue<state> q = new LinkedList<state>();
		state initial = new state(0,0,0);
		q.add(initial);
		while (!q.isEmpty()) {
			state next = q.remove();
			if(!visited[next.x][next.y] && next.op <= K) {
				visited[next.x][next.y] = true;
				minDelta = Integer.min(minDelta, Math.abs(next.x + next.y - M));
				
				q.add(new state(next.op + 1, 0, next.y)); // empty out X bucket
				q.add(new state(next.op + 1, next.x, 0)); //empty out Y bucket
				q.add(new state(next.op + 1, X, next.y)); //fill up X bucket
				q.add(new state(next.op + 1, next.x, Y)); //fill up Y bucket
				
				//put X into Y
				if (Y - next.y >= next.x)
					q.add(new state(next.op + 1, 0, next.x + next.y)); //There is more empty space in Y than there is milk in X
				else
					q.add(new state(next.op + 1, next.x - (Y - next.y), Y)); 
				
				//put Y into X
				if (X - next.x >= next.y)
					q.add(new state(next.op + 1, next.x + next.y, 0)); //There is more empty space in X than there is milk in Y
				else
					q.add(new state(next.op + 1, X , next.y - (X - next.x)));
				}
			}
			
		
		
	}

}
