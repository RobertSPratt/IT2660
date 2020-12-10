public class Edge implements Comparable<Edge> {
    int weight, from, to;

    public Edge(int f, int t, int w) {
        this.from = f;
        this.to = t;
        this.weight = w;
    }

    public int compareTo(Edge e) {
        return e.weight < this.weight ? 1 : -1;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int w) {
        this.weight = w;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
