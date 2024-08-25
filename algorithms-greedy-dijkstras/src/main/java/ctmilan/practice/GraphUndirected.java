package ctmilan.practice;
import java.util.*;

public class GraphUndirected {

    private int numNodes; // Number of vertices
    private int numEdges; // Number of edges

    private static Edge[][] edgeAdjacency; // Edges adjacent to each node
    private static Node[] nodeArray; // An array of all nodes in the graph

    private static ArrayList<Edge>[] edgeArrayList; // The array of edges that the algorithm utilizes

    @SuppressWarnings("unchecked")
    public GraphUndirected(int numNodes, int numEdges) {
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

    public void dijkstras(int root)
    {
        // Initialize the Comparator for sorting
        Comparator<Node> nodeSorter = nodeWeightSorterInit(); 

        // Initialize the priority queue with the nodeSorter sorting nodes by their ascending weight
        PriorityQueue<Node> pq = new PriorityQueue<>(nodeSorter); 

        // Add the given root node to the priority queue
        pq.add(getNodeArray()[root]);
        // and set the root node's weight to zero
        getNodeArray()[root].setWeight(0);

        // Initialize the visited[] boolean array to track which nodes have been visited
        boolean visited[] = new boolean[getNumNodes()];
        // and set the root node to true
        visited[root]=true;

        // Begin looping while the priority queue contains nodes
        while (!pq.isEmpty())
        {
            // Initialize a temporary node and assign it the next node from the priority queue
            Node nextNode = pq.poll();
            int nextNodeID = nextNode.getId();

            // Loop through all of the edges adjacent to the last node added
            for(int x=0; x<getEdgeArrayList()[nextNodeID].size();x++)
            {
                // Initialize integers for the neighboring node attached to the currently selected edge
                int neighbor = getEdgeArrayList()[nextNodeID].get(x).otherNode(nextNodeID);
                // and the neighbor's weight
                int weight = getEdgeArrayList()[nextNodeID].get(x).getWeight();
                // Add the weight of the last added node to it's neighbor's weight
                weight = getNodeArray()[nextNodeID].getWeight()+weight;

                // If the neighboring node hasn't been visited and this edge is the shortest path thus far...
                if(visited[neighbor]==false && weight<getNodeArray()[neighbor].getWeight())
                {
                    // Update the neighboring node's weight
                    getNodeArray()[neighbor].setWeight(weight);
                    // Add the node to the priority queue
                    pq.add(getNodeArray()[neighbor]);
                }
            }
            // After all all adjacent edges have been assessed, mark this node as being visited
            visited[nextNodeID]=true;
        }

        // Print out the smallest possible weights from the root node to all other possible nodes
        System.out.println("The following is the shortest distances from the root node "+root+" to every other node...\n");
        for(int x=0; x<getNumNodes();x++)
        {
            System.out.println("Node: "+getNodeArray()[x].getId()+"\tWeight:"+getNodeArray()[x].getWeight());
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
            weight = 1+random.nextInt(10);
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

        System.out.println("This is the list of edges pre-sort: ");
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
                        System.out.println(tempEdge.getOrigin()+" --"+tempEdge.getWeight()+"--> "+tempEdge.getDestination());
                    }
                    else
                    {
                        System.out.println(tempEdge.getDestination()+" --"+tempEdge.getWeight()+"--> "+tempEdge.getOrigin());
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
        getEdgeAdjacency()[destination][origin]=newEdge;
        getEdgeArrayList()[origin].add(newEdge);
        getEdgeArrayList()[destination].add(newEdge);
    }

    // Create Custom Comparator Object for sorting the node array
    private Comparator<Node> nodeWeightSorterInit()
    {
        Comparator<Node> comp = new Comparator<Node>(){
        @Override public int compare(Node a, Node b)
            {
                return a.getWeight()-b.getWeight(); // Sorting edges by ascending weight
            }
        };
        return comp;
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
        GraphUndirected.edgeAdjacency = new Edge[numNodes][numNodes];
    }

    public static void setNodeArray(int numNodes) {
        GraphUndirected.nodeArray = new Node[numNodes];
    }

    

}
