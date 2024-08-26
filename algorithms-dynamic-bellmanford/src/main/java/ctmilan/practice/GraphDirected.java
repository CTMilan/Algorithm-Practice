package ctmilan.practice;
import java.util.*;

public class GraphDirected {

    private int numNodes; // Number of vertices
    private int numEdges; // Number of edges

    private static Edge[][] edgeAdjacency; // Edges adjacent to each node
    private static Node[] nodeArray; // An array of all nodes in the graph

    private static ArrayList<Edge>[] edgeArrayList; // The array of edges that the algorithm utilizes

    @SuppressWarnings("unchecked")
    public GraphDirected(int numNodes, int numEdges) {
        this.numNodes=numNodes;
        this.numEdges=numEdges;
        setEdgeAdjacency(numNodes);
        setNodeArray(numNodes);
        edgeArrayList=new ArrayList[numNodes];

        for(int x=0; x<numNodes;x++)
        {
            // Initialize an edge arraylist for each node
            getEdgeArrayList()[x]=new ArrayList<Edge>();
            // Initialize each node in the node array with the maximum value
            getNodeArray()[x]=new Node(x, Integer.MAX_VALUE);
        }

        // Constructing the edges between vertices
        constructEdges();
    }

    // Bellman-Ford Algorithm
    public void bellmanford(int root)
    {
        // Set the root node's weight to zero
        getNodeArray()[root].setWeight(0);

        // Loop through each node
        for(int x=0; x<getNumNodes(); x++)
        {
            // And loop through each edge departing from each node
            for(int y=0; y<getEdgeArrayList()[x].size(); y++)
            {
                int origin = getEdgeArrayList()[x].get(y).getOrigin(); // Copy the origin
                int destination = getEdgeArrayList()[x].get(y).getDestination(); // Copy the destination
                int weight = getEdgeArrayList()[x].get(y).getWeight(); // Copy the weight of the edge

                // If origin node's weight isn't infinite and the sum of it's weight and the weight of the edge is less than the weight of the destination node...
                if(getNodeArray()[origin].getWeight()!= Integer.MAX_VALUE && getNodeArray()[origin].getWeight()+weight < getNodeArray()[destination].getWeight())
                {
                    // Then this edge is part of the shortest path, therefore update the destination node's weight
                    getNodeArray()[destination].setWeight(getNodeArray()[origin].getWeight() + weight);
                }
            }
        }

        // To check for negative cycles in the graph we must repeat the last loop, if any shorter paths are found they are likely negative loops
        for(int x=0; x<getNumNodes(); x++)
        {
            for(int y=0; y<getEdgeArrayList()[x].size(); y++)
            {
                int origin = getEdgeArrayList()[x].get(y).getOrigin();
                int destination = getEdgeArrayList()[x].get(y).getDestination();
                int weight = getEdgeArrayList()[x].get(y).getWeight();

                if(getNodeArray()[origin].getWeight()!= Integer.MAX_VALUE && getNodeArray()[origin].getWeight()+weight < getNodeArray()[destination].getWeight())
                {
                    if(weight==0) // This helps prevent false alarms with zero weighted edges and allows destination nodes to update their weights
                    {
                        // This shouldn't affect negative cycle detection as zero weighted edges can't contribute to a negative weighted cycle
                        getNodeArray()[destination].setWeight(getNodeArray()[origin].getWeight() + weight);
                        // An alternative to this approach would be to search for the shortest path twice to 
                        // get a more accurate solution before checking for negative cycles.
                    }
                    else
                    {
                        System.out.println("Negative weight cycle exists near edge: "+origin+"-("+weight+")->"+destination);
                        return;
                    }
                }
            }
        }

        // Print out the smallest possible weights from the root node to all other possible nodes
        System.out.println("The following is the shortest distances from the root node "+root+" to every other node...\n");
        for(int x=0; x<getNumNodes();x++)
        {
            if(getNodeArray()[x].getWeight()==Integer.MAX_VALUE && x!=root)
            {
                System.out.println("Node: "+getNodeArray()[x].getId()+"\tWeight: Unreachable");
            }
            else
            {
                System.out.println("Node: "+getNodeArray()[x].getId()+"\tWeight:"+getNodeArray()[x].getWeight());
            }
        }
        System.out.println("");
    }


    // Edge Constructor Function
    private void constructEdges()
    {
        // Initialize edges randomly across the graph. Ensuring that each vertex is connected by a minimum of one edge.
        Random random = new Random();
            
        int origin=0;
        int destination=0;
        int weight=0;

        for(int x=0;x<getNumEdges(); x++)
        {
            origin = x;
            weight = -3+random.nextInt(10);
                if( x<(getNumNodes()-1) || (x==(getNumNodes()-1) && getEdgeArrayList()[x].size()!=getNumNodes())) // Checking that all vertices have at least one edge connected to it
                {
                    do
                    {
                        destination = random.nextInt(getNumNodes()); // Randomized destination
                    }
                    while(destination==origin || getEdgeAdjacency()[origin][destination]!=null); // Checks for looped edges and repeat edges
                }
                else if(x>(getNumNodes()-1) || (x==(getNumNodes()-1) && getEdgeArrayList()[x].size()==getNumNodes())) // After all vertices have a minimum one edge connection, the origin can now be randomized
                {
                    do
                    {
                        origin = random.nextInt(getNumNodes()); // Origin is now randomized
                        destination = random.nextInt(getNumNodes()); // Aswell as destination
                    }
                    while(destination==origin || getEdgeAdjacency()[origin][destination]!=null); // Checks for looped edges and repeat edges
                }
                else
                {
                    System.out.println("Error in construction: Unable to find any more unique edges to construct!");
                    break;
                }
            addEdge(origin,destination,weight); // Adds the new edge to its adjacency lists
        }

        System.out.println("This is the list of edges attached to each node: ");
        arrayPrinter(getEdgeArrayList());
    }


    //----- Utility Functions -----//

     // Prints an ArrayList of Edge Objects
    private void arrayPrinter(ArrayList<Edge>[] array)
    {
        
        for(int x = 0; x<getNumNodes(); x++) // Cycles through each vertice
        {
            System.out.println("");
            for(int y = 0; y<array[x].size(); y++) // Cycles through the destinations
                {
                    Edge tempEdge = array[x].get(y);
                    if(tempEdge.getOrigin()==x)
                    {
                        System.out.println(tempEdge.getOrigin()+" --("+tempEdge.getWeight()+")--> "+tempEdge.getDestination());
                    }
                    else
                    {
                        System.out.println(tempEdge.getDestination()+" --("+tempEdge.getWeight()+")--> "+tempEdge.getOrigin());
                    }
                    
                }
            System.out.println("");
        }
    }

    // Add a new Edge to the edge arraylist and the edge adjacency list
    private void addEdge(int origin, int destination, int weight) // Adds an edge to the edge array
    {
        Edge newEdge = new Edge(origin, destination, weight);
        getEdgeAdjacency()[origin][destination]=newEdge;
        getEdgeArrayList()[origin].add(newEdge);

        // The lines below are commented out to make the graph directed. If an undirected graph is desired
        // simply remove the commenting. WARNING: Not all algorithms support negatively weighted undirected graphs!

        //getEdgeAdjacency()[destination][origin]=newEdge;
        //getEdgeArrayList()[destination].add(newEdge);
    }


    //----- Getters & Setters -----//
    
    public int getNumNodes() {
        return numNodes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public static Edge[][] getEdgeAdjacency() {
        return edgeAdjacency;
    }

    public static Node[] getNodeArray() {
        return nodeArray;
    }

    public static ArrayList<Edge>[] getEdgeArrayList() {
        return edgeArrayList;
    }


    public static void setEdgeAdjacency(int numNodes) {
        GraphDirected.edgeAdjacency = new Edge[numNodes][numNodes];
    }

    public static void setNodeArray(int numNodes) {
        GraphDirected.nodeArray = new Node[numNodes];
    }

    

}
