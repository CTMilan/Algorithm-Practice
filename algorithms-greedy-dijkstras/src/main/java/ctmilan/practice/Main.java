package ctmilan.practice;
import java.util.*;

public class Main {

    // ====== Summary ======
    //
    // --Time Complexity--
    // Best:  O(V+ElogV)
    // Avg :  O(V+ElogV)
    // Worst: O(V+(V^2)logV)
    //
    // --Space Complexity--
    //  Best:   O(V)
    //  Worst:  O(E+V)
    //
    // -- Pros --
    // * Fairly good time complexity (almost linear)
    // * Does not require a single target node and can find the shortest path to multiple target nodes from the root node
    //
    // -- Cons --
    // * Inefficient when graphs are fully or sparsly connected
    // * Graphs must not have negative edge weights

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
        GraphUndirected graph = new GraphUndirected(verticesNum, edgesNum);

        // Randomize the starting node to find the shortest paths from
        int startingNode =random.nextInt(verticesNum);

        // Starting Dijkstra's Algorithm for the shortest path from the selected vertex to all other nodes
        graph.dijkstras(startingNode);
    }
}