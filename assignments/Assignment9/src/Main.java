import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int[] numGenerator(int[] n) {
        Random r = new Random();
        n = r.ints(n.length, 1, 2000 + 1).toArray();
        return n;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Graph graph = new Graph(1000);
        Scanner scanner = new Scanner(System.in);
        int values[] = new int[1000];
        int targetValue;

        values = numGenerator(values);
        for(int i = 0; i < values.length; i++) {
            graph.addVertex(i, new Vertex(i, values[i], rand.nextInt(5) + 1));
        }

        for(int i = 0; i < graph.max; i++) {
            int targetsTried[] = new int[graph.max];

            while(graph.getVertex(i).canAddEdge()) {
                int newEdgeTarget = rand.nextInt(1000);

                //newEdgeTarget is already known to be full
                if(targetsTried[newEdgeTarget] == 1)
                    continue;

                //newEdgeTarget was filled with last edge added
                else if(!graph.getVertex(newEdgeTarget).canAddEdge()) {
                    targetsTried[newEdgeTarget] = 1;
                    continue;
                }

                //add edge bi-directional edge to edge matrix
                graph.insertEdge(i, newEdgeTarget);
                graph.insertEdge(newEdgeTarget, i);
            }
        }

        System.out.print("Enter a value between 1 and 2000 to search for: ");
        targetValue = scanner.nextInt();

        graph.breadthFirstTraversal(targetValue);
        System.out.println();
        graph.depthFirstTraversal(targetValue);
        System.out.println();
        graph.dijkstraAlgorithm(targetValue);
    }
}
