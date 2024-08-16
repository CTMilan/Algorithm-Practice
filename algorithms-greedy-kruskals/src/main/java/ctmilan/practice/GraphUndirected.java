package ctmilan.practice;
import java.util.*;

public class GraphUndirected {

    private static int verticesNum; // Number of vertices
    private static int edgesNum; // Number of edges
    private static LinkedList<Integer>[] adjacent; // Adjacency list
    private static ArrayList<Edge> edgeArray = new ArrayList<Edge>(); // The array of edges that the algorithm utilizes
    private static ArrayList<Edge> MST = new ArrayList<Edge>(); // The resulting array of edges that make up the MST (Minimum Spanning Tree)
    
    public GraphUndirected(int vertices, int edgesNum) {
        setVerticesNum(vertices);
        setEdgesNum(edgesNum);
        setAdjacent(vertices);

        // Adding each vertex to the adjacency list
        for(int x=0; x<vertices; x++)
        {
            getAdjacent()[x] = new LinkedList<>();
        }
        // Constructing the edges between vertices
        constructEdges();
    }
    
    // Kruskal's Algorithm for finding the MST (Minimum Spanning Tree) of an Undirected Weighted Graph
    public void kruskals()
    {
        Comparator<Edge> edgeSorter = initSorter(); // Initialize the Comparator for sorting
        getEdgeArray().sort(edgeSorter); // Use the comparator to sort the edge array by ascending (low to high) weight

        System.out.println("This is the list of edges sorted by weight: ");
        arrayPrinter(getEdgeArray()); // Print the array post-sort

        System.out.println("Now clearing the adjacency list...");
        clearAdj(); // Clear the adjacency list so it can be used by the DFS algorithm for cycle detection
        System.out.println("Clearing finished...");
        System.out.println("Building MST...\n");
        int x=0; // counter for the number of edges added to the MST
        int y=0; // index of the current edge selected from the sorted array
        while(x<getVerticesNum()-1) // Loop while there are more edges to add to the MST (Always minimum # of edges: min = #vertices-1)
        {
            Edge newEdge = getEdgeArray().get(y); // Select an edge from the sorted list to try for the MST
            System.out.println("Trying edge: "+newEdge.getOrigin()+" --"+newEdge.getWeight()+"--> "+newEdge.getDestination());

            addEdgeAdj(newEdge.getOrigin(), newEdge.getDestination()); // Add the edge (from both of it's vertices) to the adjacency list

            if(dfsCycleCheck(newEdge.getOrigin(),newEdge.getDestination())==true) // If true, then adding the new edge would create a cycle in the MST
            {
                removeEdgeAdj(newEdge.getOrigin(), newEdge.getDestination()); // Thus remove it from the adjacency list
                System.out.println("Incompatible! (Reason: Cycle Found)");
            }
            else // Otherwise, the edge is safe to add to the MST
            {
                System.out.println("Compatible!");
                addEdgeMST(newEdge); // Add it to the MST array
                x++; // Increment the MST edge counter
            }
            y++; // And select the next edge on the sorted edge array
        }


        System.out.println("This is the resulting MST (Minimum Spanning Tree) from Kruskal's Algorithm...");
        arrayPrinter(getMST()); // Print the final MST edge array
    }
    
    // Depth First Search Functions (checking for cycles)
    private boolean dfsCycleCheck(int origin, int destination)
    {
        // Boolean array to track which vertices have been visited
        boolean[] visited = new boolean[getVerticesNum()];
        
        if(dfsUtil(visited,origin,origin,origin,destination)) // If true, a cycle has been found
        {
            return true;
        }
        else // Otherwise, no cycle was found
        {
            return false;
        }
    }
    private boolean dfsUtil(boolean[] visited, int vertex, int last, int root1, int root2) // last = last vertex visited. root1 & root2 = the vertices attached to the edge.
    {
        // Set the current vertex to visited
        visited[vertex]=true;
        //System.out.println("Current Node: "+vertex);

        // Move through all of the nodes accessible from the current vertex
        for(int x = 0; x<getAdjacent()[vertex].size(); x++)
        {
            int nextVertex = getAdjacent()[vertex].get(x); // Select one
            if(visited[nextVertex]==false) // Check if it has been visited
            {
                dfsUtil(visited, nextVertex, vertex, root1, root2); // If it hasn't, visit it via recursive call to dfsUtil()
            }
            else if((nextVertex==root1 && last!=root1) || (nextVertex==root2 && last!=root2)) // Check for cycle
            {
                return true; // Yes there's a cycle - returning true
            }
            // Otherwise, select another adjacent vertex
        }
        return false; // No cycles found - returning false
    }


    // Edge Constructor Function
    private void constructEdges()
    {
        // Initialize edges randomly across the graph. Ensuring that each vertex is connected by a minimum of one edge.
        Random random = new Random();
            
        int origin=0;
        int destination=0;
        int weight=0;

        for(int x=0;x<getEdgesNum(); x++)
        {
            origin = x;
            weight = 1+random.nextInt(20);
            if( x<(getVerticesNum()-1) || (x==(getVerticesNum()-1) && getAdjacent()[x].size()!=getVerticesNum()-1) ) // Checking that all vertices have at least one edge connected to it
            {
                do
                {
                    destination = random.nextInt(getVerticesNum()); // Randomized destination
                }
                while(destination==origin || getAdjacent()[origin].contains(destination)); // Checks for looped edges and repeat edges
            }
            else if(x>(getVerticesNum()-1) || (x==(getVerticesNum()-1) && getAdjacent()[x].size()==getVerticesNum()-1)) // After all vertices have a minimum one edge connection, the origin can now be randomized
            {
                do
                {
                    origin = random.nextInt(getVerticesNum()); // Origin is now radnomized
                    destination = random.nextInt(getVerticesNum()); // Aswell as destination
                }
                while(destination==origin || getAdjacent()[origin].contains(destination)); // Checks for looped edges and repeat edges
            }
            else
            {
                System.out.println("Error in construction: Unable to find any more unique edges to construct!");
                break;
            }
            addEdgeAdj(origin,destination); // Adds the new edge to the adjacency list
            addEdge(origin,destination,weight); // Adds new edge to the edge array
        }

        System.out.println("This is the list of edges pre-sort: ");
        arrayPrinter(edgeArray); // print array unsorted
    }

    // Create Custom Comparator Object for sorting the edge array
    private Comparator<Edge> initSorter()
    {
        Comparator<Edge> comp = new Comparator<Edge>(){
        @Override public int compare(Edge a, Edge b)
            {
                return a.getWeight()-b.getWeight(); // Sorting edges by ascending weight
            }
        };
        return comp;
    }

    // Utility Functions
    private void arrayPrinter(ArrayList<Edge> array) // Prints the any ArrayList of Edge Objects
    {
        System.out.println("");
        for(int x = 0; x<array.size(); x++) // Cycles through the vertices
            {
                Edge tempEdge = array.get(x);
                System.out.println(tempEdge.getOrigin()+" --"+tempEdge.getWeight()+"--> "+tempEdge.getDestination());
            }
        System.out.println("");
    }

    private void addEdgeAdj(int origin, int destination) // Adds edges to the adjacency list
    {
        getAdjacent()[origin].add(destination);
        // The following line can be commented out to make the edges of the graph directed
        getAdjacent()[destination].add(origin);
    }
    private void removeEdgeAdj(int origin, int destination) // Removes edges from the adjacency list
    {
        getAdjacent()[origin].removeLast();
        // The following line can be commented out to make the edges of the graph directed
        getAdjacent()[destination].removeLast();
    }
    private void clearAdj() // Clears the adjacency list row by row
    {
        for(int x=0; x<getAdjacent().length; x++)
        {
            getAdjacent()[x].clear();
        }
    }

    private void addEdge(int origin, int destination, int weight) // Adds an edge to the edge array
    {
        getEdgeArray().add(new Edge(origin, destination, weight));
    }
    
    private void addEdgeMST(Edge edgeBeingAdded) // Adds an edge to the MST edge array
    {
        getMST().add(edgeBeingAdded);
    }
    
    // Getter Functions
    public int getVerticesNum() {
        return verticesNum;
    }
    public int getEdgesNum() {
        return edgesNum;
    }
    public LinkedList<Integer>[] getAdjacent() {
        return adjacent;
    }
    public static ArrayList<Edge> getEdgeArray() {
        return edgeArray;
    }
    public static ArrayList<Edge> getMST() {
        return MST;
    }

    // Setter Functions
    private void setVerticesNum(int verticesNum) {
        GraphUndirected.verticesNum = verticesNum;
    }
    private void setEdgesNum(int edgesNum) {
        GraphUndirected.edgesNum = edgesNum;
    }
    @SuppressWarnings("unchecked")
    private void setAdjacent(int vertices) {
        GraphUndirected.adjacent = new LinkedList[vertices];
    }
}
