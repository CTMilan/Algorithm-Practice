package ctmilan.practice;
import java.util.*;

public class Main {

    // ====== Summary ======
    // --Time Complexity--
    // --Space Complexity--
    // -- Pros --
    // -- Cons --

    public static void main(String[] args) {
        Random random = new Random();

        // Randomized number of vertices being initialized
        int verticesNum =2+random.nextInt(9);

        // Randomized number of edges being initialized
        int edgesNum; // Number of edges
        int min=(verticesNum-1); // Minimum number of edges
        int max=((int)Math.round((((double)verticesNum/2)*min)))-min+1; // The maximum number of edges

        edgesNum = min + random.nextInt(max);
        
        System.out.println("There will be "+verticesNum+" vertices and "+edgesNum+" edges");

        // Initiating the graph object
        GraphUndirected graph = new GraphUndirected(verticesNum, edgesNum);

        // Starting Kruskal's Algorithm for the Minimum Spanning Tree
        graph.kruskals();
    }
}