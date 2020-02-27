import java.util.*;
import java.io.*;
public class DFS {
	
	static LinkedList<Integer>[] adj;
	static int V;

	public static void main(String[] args) {
        // prints all not yet visited vertices reachable from s 
        

	}
	
	public static void DFS(int s) 
    { 
        // Initially mark all vertices as not visited 
        Vector<Boolean> visited = new Vector<Boolean>(V); 
        for (int i = 0; i < V; i++) 
            visited.add(false); 
  
        // Create a stack for DFS 
        Stack<Integer> stack = new Stack<>(); 
          
        // Push the current source node 
        stack.push(s); 
          
        while(stack.empty() == false) 
        { 
            // Pop a vertex from stack and print it 
            s = stack.peek(); 
            stack.pop(); 
              
            // Stack may contain same vertex twice. So 
            // we need to print the popped item only 
            // if it is not visited. 
            if(visited.get(s) == false) 
            { 
                System.out.print(s + " "); 
                visited.set(s, true); 
            } 
              
            // Get all adjacent vertices of the popped vertex s 
            // If a adjacent has not been visited, then push it 
            // to the stack. 
            Iterator<Integer> itr = adj[s].iterator(); 
              
            while (itr.hasNext())  
            { 
                int v = itr.next(); 
                if(!visited.get(v)) 
                    stack.push(v); 
            } 
              
        } 
    }

}
