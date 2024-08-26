package ctmilan.practice;

import java.util.Random;

public class Main {
    // ====== Summary ======
    //  
    // --Time Complexity--
    //  Best:    O(E)
    //  Average: O(V*E)
    //  Worst:   O(V*E)
    //
    // --Space Complexity--
    //  O(V)
    //
    // -- Pros --
    //  * Can detect any negative cycles
    //  * Can work with negative edges (unlike dijkstra's algorithm)
    //
    // -- Cons --
    //  * Cannot work past any negative cycles
    //  * Slower than dijkstra's algorithm

    public static void main(String[] args) {
        Random random = new Random();

        // Randomized number of vertices being initialized
        int verticesNum =  2+random.nextInt(9);

        // Randomized number of edges being initialized
        int edgesNum; // Number of edges
        int min=(verticesNum-1); // Minimum number of edges
        int max=((int)Math.round((((double)verticesNum/2)*min)))-min+1; // The maximum number of edges

        edgesNum = min + random.nextInt(max);
        
        System.out.println("There will be "+verticesNum+" vertices and "+edgesNum+" edges");

        // Initiating the graph object
        GraphDirected graph = new GraphDirected(verticesNum, edgesNum);

        // Randomize the starting node to find the shortest paths from
        int startingNode =random.nextInt(verticesNum);

        // Starting Dijkstra's Algorithm for the shortest path from the selected vertex to all other nodes
        graph.bellmanford(startingNode);
    }
}