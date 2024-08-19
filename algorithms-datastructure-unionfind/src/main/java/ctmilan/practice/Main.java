package ctmilan.practice;
import java.util.Random;
import java.util.Arrays;

public class Main {
    
    // ====== Summary ======
    // This is a union-find implementation (by rank & with path compression)
    //
    // --Time Complexity--
    //   Worst:     O(logn)
    //   Amortized: O(a(n)) ~ O(1)
    //
    //  a(n) is the inverse Ackermann function and very nearly approaches constant time
    //
    // --Space Complexity--
    //   Overall: O(n)
    //
    // -- Pros --
    // * Near constant time complexity
    // * Excels at disjoint set problems (ie. Kruskal's Algorithm)
    // * Very efficient at tracking connections between different data/nodes
    // * Fairly simple to implement
    //
    // -- Cons --
    // * Only works on undirected graphs
    // * Not the best space complexity
    //

    public static void main(String[] args)
    {
        
    Random rand = new Random();
    
    // Initialize the universal set at a random size (between 2-10)
    int size = 2+rand.nextInt(9);
    DisjointSet set = new DisjointSet(size);


    // Printing the arrays before creating subsets
    System.out.println("\nNodes before set creation:");
    System.out.print("node:     ");
    for(int x=0; x<size; x++)
    {
        System.out.print(x+"  ");
    }
    System.out.print("\nparent:  ");
    System.out.println(Arrays.toString(set.getParent()));
    System.out.print("rank:    ");
    System.out.println(Arrays.toString(set.getRank()));
    System.out.println("\n");


    // Randomized subset creation
    for(int x=0; x<size; x++)
    {
        set.union(rand.nextInt(size),rand.nextInt(size));
    }


    // Printing arrays after subset creation
    System.out.println("Nodes after set creation:");
    System.out.print("node:     ");
    for(int x=0; x<size; x++)
    {
        System.out.print(x+"  ");
    }
    System.out.print("\nparent:  ");
    System.out.println(Arrays.toString(set.getParent()));
    System.out.print("rank:    ");
    System.out.println(Arrays.toString(set.getRank()));
    System.out.println("\n");

    for(int x=0; x<size; x++)
    {
        System.out.print(x+"'s parent is ");
        System.out.println(set.find(x));
    }
    System.out.println("");

    }




}


