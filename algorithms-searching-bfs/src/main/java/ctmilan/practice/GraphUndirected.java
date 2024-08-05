package ctmilan.practice;
import java.util.*;

public class GraphUndirected {

    private static int vertices; // Number of vertices
    private static int edges; // Number of edges
    private static LinkedList<Integer>[] adjacent; // Adjacency list
    
    public GraphUndirected(int vertices, int edgesNum) {
        setVertices(vertices);
        setEdges(edgesNum);
        setAdjacent(vertices);

        // Adding each vertex to the adjacency list
        for(int x=0; x<vertices; x++)
        {
            getAdjacent()[x] = new LinkedList<>();
        }
        // Constructing the edges between vertices
        constructEdges();
    }
    
    // Breadth First Search Function
    public void bfs(int start)
    {
        // Queue to track order of the search
        Deque<Integer> dq = new LinkedList<>();
        // Boolean array to track which vertices have been visited
        boolean[] visited = new boolean[getVertices()];

        // Begin by setting 'start' as visited and adding it to the queue
        visited[start]=true;
        dq.add(start);

        // Then loop while the queue isn't empty
        while(dq.isEmpty()==false)
        {
            // Vertex currently being visited
            int vertex = dq.pop();
            System.out.println("Current Node: "+vertex);

            // Loop through all vertices adjacent to the current vertex
            for(int x = 0; x<getAdjacent()[vertex].size(); x++)
            {
                // If the adjacent vertex has not been visited...
                if(visited[getAdjacent()[vertex].get(x)]==false)
                {
                    // Set the adjacent vertex as visited and add it to the queue
                    visited[getAdjacent()[vertex].get(x)]=true;
                    dq.add(getAdjacent()[vertex].get(x));
                }
                // Otherwise, coninue to the next adjacent vertex
            }
        }
        // Once the queue is empty print the adjacency list
        printer();
    }

    // Edge Constructor Function
    private void constructEdges()
    {
        // Initialize edges randomly across the graph. Ensuring that each vertex is connected by a minimum of one edge.
        Random random = new Random();
        int origin=0;
        int destination=0;

        for(int x=0;x<getEdges(); x++)
        {
            if(x<=(getVertices()-1)) // Checking that all vertices have at least one edge connected to it
            {
                origin = x;
                do
                {
                    destination = random.nextInt((getVertices()-1)); // Randomized destination
                }
                while(destination==origin || getAdjacent()[origin].contains(destination)); // Checks for looped edges and repeat edges
            }
            else if(x>(getVertices()-1)) // After all vertices have a minimum one edge connection, the origin can now be randomized
            {
                origin = random.nextInt((getVertices()-1)); // Origin is now radnomized
                do
                {
                    destination = random.nextInt((getVertices()-1)); // Aswell as destination
                }
                while(destination==origin || getAdjacent()[origin].contains(destination)); // Checks for looped edges and repeat edges
            }
            addEdge(origin,destination); // Adds the new edge to the adjacency list
        }
    }

    // Utility Functions
    private void addEdge(int origin, int destination) // Adds edges to the adjacency list
    {
        getAdjacent()[origin].add(destination);
        getAdjacent()[destination].add(origin);
    }
    private void printer() // Prints the adjacency list
    {
        System.out.println("\nAll vertices have now been visited, here is the adjacency list...\n");
        for(int x = 0; x<getAdjacent().length; x++) // Cycles through the vertices
            {
                System.out.print(x+":"); // Print the selected vertex
                for(int y = 0; y<getAdjacent()[x].size(); y++) // Cycles through the vertices adjacent to the selected vertex
                {
                    System.out.print((" "+getAdjacent()[x].get(y))); // Print the adjacent vertex
                }
                System.out.println("");
            }
        System.out.println("");
    }
    
    // Getter Functions
    public int getVertices() {
        return vertices;
    }
    public int getEdges() {
        return edges;
    }
    public LinkedList<Integer>[] getAdjacent() {
        return adjacent;
    }

    // Setter Functions
    private void setVertices(int vertices) {
        GraphUndirected.vertices = vertices;
    }
    private void setEdges(int edges) {
        GraphUndirected.edges = edges;
    }
    @SuppressWarnings("unchecked")
    private void setAdjacent(int vertices) {
        GraphUndirected.adjacent = new LinkedList[vertices];
    }
}
