package ctmilan.practice;
import java.util.*;

public class GraphDirected {

    private static int vertices; // Number of vertices
    private static int edges; // Number of edges
    private static LinkedList<Integer>[] adjacent; // Adjacency list
    
    public GraphDirected(int vertices, int edgesNum) {
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
    
    // Depth First Search Function
    public void dfs(int start)
    {
        // Boolean array to track which vertices have been visited
        boolean[] visited = new boolean[getVertices()];
        // Begin the recursions...
        dfsUtil(visited,start);
        // Print the list of accessible nodes/paths...
        printer(visited);
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
    private void dfsUtil(boolean[] visited, int vertex)
    {
        // Set the current vertex to visited
        visited[vertex]=true;
        System.out.println("Current Node: "+vertex);

        // Cycle through all of the nodes accessible from the current vertex
        for(int x = 0; x<getAdjacent()[vertex].size(); x++)
        {
            int nextVertex = getAdjacent()[vertex].get(x); // Select one
            if(visited[nextVertex]==false) // Check if it has been visited
            {
                dfsUtil(visited, nextVertex); // If it hasn't, visit it via recursive call to dfsUtil()
            }
            // Otherwise, select another adjacent vertex
        }
    }
    private void addEdge(int origin, int destination) // Adds edges to the adjacency list
    {
        getAdjacent()[origin].add(destination);

        // The following line is commented out to make the edges of the graph directed
        //getAdjacent()[destination].add(origin);
    }
    private void printer(boolean[] visited) // Prints the adjacency list
    {
        System.out.println("\nFrom the starting node the following vertices are accessible via the following adjacency list...\n");
        for(int x = 0; x<getAdjacent().length; x++) // Cycles through the vertices
            {
                if(visited[x]==true)
                {
                    System.out.print(x+":"); // Print the selected vertex
                    for(int y = 0; y<getAdjacent()[x].size(); y++) // Cycles through the vertices adjacent to the selected vertex
                        {
                        System.out.print((" "+getAdjacent()[x].get(y))); // Print the adjacent vertex
                        }
                    System.out.println("");
                }
                
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
        GraphDirected.vertices = vertices;
    }
    private void setEdges(int edges) {
        GraphDirected.edges = edges;
    }
    @SuppressWarnings("unchecked")
    private void setAdjacent(int vertices) {
        GraphDirected.adjacent = new LinkedList[vertices];
    }
}
