package ctmilan.practice;
import java.util.Random;

public class Main {
    // ====== Summary ======
    // --Time Complexity--
    //   Overall: O(V+E) (V=#vertices & E=#edges)
    // --Space Complexity--
    //   Average: O(V) (V=#vertices)
    // -- Pros --
    // * Will always find a solution
    // * Won't get stuck in cycles
    // -- Cons --
    // * Space complexity can potentially be very bad
    //   if the depth of the graph is large
    //   and if parent nodes have many children
    public static void main(String[] args) {
        Random random = new Random();

        // Randomized number of vertices being initialized
        int verticesNum = 2+random.nextInt(20);
        // Randomized number of edges being initialized
        int edgesNum = (verticesNum-1) + random.nextInt(verticesNum);
        // The vertex number that the BFS starts from
        int start = random.nextInt(verticesNum-1);

        // Initiating the graph object
        GraphUndirected graph = new GraphUndirected(verticesNum, edgesNum);

        System.out.println("\n\nBFS starting from: "+start+"\n");

        // Starting the BFS
        graph.bfs(start);
    }
}