//1) Initialize distances of all vertices as infinite.
//
//2) Create an empty priority_queue pq.  Every item
//   of pq is a pair (weight, vertex). Weight (or 
//   distance) is used used as first item  of pair
//   as first item is by default used to compare
//   two pairs
//
//3) Insert source vertex into pq and make its
//   distance as 0.
//
//4) While either pq doesn't become empty
//    a) Extract minimum distance vertex from pq. 
//       Let the extracted vertex be u.
//    b) Loop through all adjacent of u and do 
//       following for every vertex v.
//
//           // If there is a shorter path to v
//           // through u. 
//           If dist[v] > dist[u] + weight(u, v)
//
//               (i) Update distance of v, i.e., do
//                     dist[v] = dist[u] + weight(u, v)
//               (ii) Insert v into the pq (Even if v is
//                    already there)
//               
//5) Print distance array dist[] to print all shortest
//   paths. 
import java.util.*; 
public class Dijkstra {

	    private int dist[]; 
	    private Set<Integer> settled; 
	    private PriorityQueue<Node> pq; 
	    private int V; // Number of vertices 
	    List<List<Node> > adj; 
	  
	    public Dijkstra(int V) 
	    { 
	        this.V = V; 
	        dist = new int[V]; 
	        settled = new HashSet<Integer>(); 
	        pq = new PriorityQueue<Node>(V, new Node()); 
	    } 
	  
	    // Function for Dijkstra's Algorithm 
	    public void dijkstra(List<List<Node> > adj, int src) 
	    { 
	        this.adj = adj; 
	  
	        for (int i = 0; i < V; i++) 
	            dist[i] = Integer.MAX_VALUE; 
	  
	        // Add source node to the priority queue 
	        pq.add(new Node(src, 0)); 
	  
	        // Distance to the source is 0 
	        dist[src] = 0; 
	        while (settled.size() != V) { 
	  
	            // remove the minimum distance node  
	            // from the priority queue  
	            int u = pq.remove().node; 
	  
	            // adding the node whose distance is 
	            // finalized 
	            settled.add(u); 
	  
	            e_Neighbours(u); 
	        } 
	    } 
	  
	    // Function to process all the neighbours  
	    // of the passed node 
	    private void e_Neighbours(int u) 
	    { 
	        int edgeDistance = -1; 
	        int newDistance = -1; 
	  
	        // All the neighbors of v 
	        for (int i = 0; i < adj.get(u).size(); i++) { 
	            Node v = adj.get(u).get(i); 
	  
	            // If current node hasn't already been processed 
	            if (!settled.contains(v.node)) { 
	                edgeDistance = v.cost; 
	                newDistance = dist[u] + edgeDistance; 
	  
	                // If new distance is cheaper in cost 
	                if (newDistance < dist[v.node]) 
	                    dist[v.node] = newDistance; 
	  
	                // Add the current node to the queue 
	                pq.add(new Node(v.node, dist[v.node])); 
	            } 
	        } 
	    } 
	  
	    // Driver code 
	    public static void main(String arg[]) 
	    { 
	        int V = 5; 
	        int source = 0; 
	  
	        // Adjacency list representation of the  
	        // connected edges 
	        List<List<Node> > adj = new ArrayList<List<Node> >(); 
	  
	        // Initialize list for every node 
	        for (int i = 0; i < V; i++) { 
	            List<Node> item = new ArrayList<Node>(); 
	            adj.add(item); 
	        } 
	  
	        // Inputs for the DPQ graph 
	        adj.get(0).add(new Node(1, 9)); 
	        adj.get(0).add(new Node(2, 6)); 
	        adj.get(0).add(new Node(3, 5)); 
	        adj.get(0).add(new Node(4, 3)); 
	  
	        adj.get(2).add(new Node(1, 2)); 
	        adj.get(2).add(new Node(3, 4)); 
	  
	        // Calculate the single source shortest path 
	        Dijkstra dpq = new Dijkstra(V); 
	        dpq.dijkstra(adj, source); 
	  
	        // Print the shortest path to all the nodes 
	        // from the source node 
	        System.out.println("The shorted path from node :"); 
	        for (int i = 0; i < dpq.dist.length; i++) 
	            System.out.println(source + " to " + i + " is "
	                               + dpq.dist[i]); 
	    } 
	} 
	  
	// Class to represent a node in the graph 
	class Node implements Comparator<Node> { 
	    public int node; 
	    public int cost; 
	  
	    public Node() 
	    { 
	    } 
	  
	    public Node(int node, int cost) 
	    { 
	        this.node = node; 
	        this.cost = cost; 
	    } 
	  
	    @Override
	    public int compare(Node node1, Node node2) 
	    { 
	        if (node1.cost < node2.cost) 
	            return -1; 
	        if (node1.cost > node2.cost) 
	            return 1; 
	        return 0; 
	    } 

}
