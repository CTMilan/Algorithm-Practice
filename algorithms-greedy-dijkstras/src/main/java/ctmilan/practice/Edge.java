package ctmilan.practice;

public class Edge {

    private int origin;
    private int destination;
    private int weight;
    
    public Edge(int origin, int destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }


    // Getters & Setters
    public int getOrigin() {
        return origin;
    }
    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }
    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean contains(int vertex) // Used to check edges for specific vertices/nodes. Helpful when working with undirected graphs so that duplicate edges aren't needed
    {
        if(getOrigin()==vertex || getDestination()==vertex)
        {
            return true;
        }
        return false;
    }

    public int otherNode(int nodeID) // Takes the ID of one of the nodes attached to an edge instance and returns the node ID opposite to the one given
    {
        if(nodeID == getOrigin())
        {
            return getDestination();
        }
        else if(nodeID == getDestination())
        {
            return getOrigin();
        }
        // Otherwise the node being searched for either doesn't exist or is not connected via this edge instance
        return Integer.MAX_VALUE; // return max_value as an error
    }
    
}
