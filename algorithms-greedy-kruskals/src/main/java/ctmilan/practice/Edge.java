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

    public boolean contains(int vertex)
    {
        if(getOrigin()==vertex || getDestination()==vertex)
        {
            return true;
        }
        return false;
    }

    
}
