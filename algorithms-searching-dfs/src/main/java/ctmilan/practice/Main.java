package ctmilan.practice;
import java.util.Random;

public class Main {
    // ====== Summary ======
    // --Time Complexity--
    //   Overall: O(V+E) (V=#vertices & E=#edges)
    // --Space Complexity--
    //   Average: O(V) (V=#vertices)
    // -- Pros --
    // * Capable of detecting cycles (discovery of back edges during routine)
    // * Capable of basic pathfinding (best with problems with only one solution)
    // -- Cons --
    // * Might not find the shortest paths
    // * Not guarenteed to find a solution
    // * Could get stuck in cycles (if 'visited' array isn't used)
    public static void main(String[] args) {
        Random random = new Random();

        // Randomized number of vertices being initialized
        int verticesNum = 2+random.nextInt(20);
        // Randomized number of edges being initialized
        int edgesNum = (verticesNum-1) + random.nextInt(verticesNum);
        // The vertex number that the DFS starts from
        int start = random.nextInt(verticesNum-1);

        // Initiating the graph object
        GraphDirected graph = new GraphDirected(verticesNum, edgesNum);

        System.out.println("There are "+verticesNum+" total vertices and "+edgesNum+" total edges");
        System.out.println("\n\nBeginning the DFS starting from: "+start+"\n");

        // Starting the DFS
        graph.dfs(start);
    }
}