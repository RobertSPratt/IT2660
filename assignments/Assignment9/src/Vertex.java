public class Vertex {
    private int vertexNumber;
    private boolean hasBeenVisited;
    private int value;
    private int numberOfEdges;
    private Edge edge[];

    public Vertex(int num, int val, int edges) {
        this.vertexNumber = num;
        this.value = val;
        this.edge = new Edge[edges];
        this.numberOfEdges = 0;
        this.hasBeenVisited = false;
    }

    //constructor to allow for deepCopy method to maintain Edge links
    public Vertex(int num, int val, Edge[] edges, int numOfEdges) {
        this.vertexNumber = num;
        this.value = val;
        this.edge = edges;
        this.numberOfEdges = numOfEdges;
        this.hasBeenVisited = false;
    }

    public String toString() {
        return("Vertex " + vertexNumber + "'s value is: " + value);
    }

    public int getValue() {
        return value;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public Vertex deepCopy() {
        Vertex clone = new Vertex(vertexNumber, value, edge, numberOfEdges);
        return clone;
    }

    public boolean canAddEdge() {
        if(numberOfEdges >= edge.length)
            return false;
        return true;
    }

    public boolean addEdge(Edge e) {
        if(numberOfEdges >= edge.length)
            return false;
        else {
            edge[numberOfEdges] = e;
            numberOfEdges++;
            return true;
        }
    }

    public Edge[] getEdges() {
        return edge;
    }

    public Edge getEdge(int i) {
        if(i <= edge.length)
            return edge[i];
        else
            return null;
    }

    public boolean checkVisited() {
        return hasBeenVisited;
    }

    public void setVisited(boolean b) {
        hasBeenVisited = b;
    }
}
