package ctmilan.practice;

public class DisjointSet {

    private int[] parent,rank; // Arrays for keeping track of parent nodes and each node's rank
    private int size; // Size of the given array/universe

    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        this.size=size;

        for(int x=0; x<size; x++) // Filling the arrays with initial values
        {
            parent[x]=x; // Each node starts as a parent of itself
            rank[x]=0;
        }
    }


    // Find Function - Returns the parent of the target node
    public int find(int target)
    {
        if(getParent()[target]!=target)
        {
            // Makes recursive calls to find the target's root node
            getParent()[target]=find(getParent()[target]);
        }
        return getParent()[target];
    }

    // Union Function - Takes two nodes and connects them, returning nothing
    public void union(int x, int y)
    {
        int rootx = find(x); // Get x's root node
        int rooty = find(y); // Get y's root node

        if(rootx==rooty) // If they have the same root...
        {
            return; // They're already connected, thus return void
        }
        // Otherwise continue

        if(getRank()[rootx]<getRank()[rooty]) // If x's parent's rank is higher than y's parent's rank...
        {
            parent[rootx]=rooty; // x's parent becomes y's parent
        }
        else if(getRank()[rootx]>getRank()[rooty]) // If y's parent's rank is higher than x's parent's rank...
        {
            parent[rooty]=rootx; // y's parent becomes x's parent
        }
        else // Otherwise...
        {
            parent[rooty]=rootx; // Assign one of the nodes (either will do) to the parent of the other
            getRank()[rootx]++; // Then increase the rank of that parent
        }

    }

    public int[] getParent() {
        return parent;
    }
    public int[] getRank() {
        return rank;
    }
    public int getSize() {
        return size;
    }
}
