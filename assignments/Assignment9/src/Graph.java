import java.util.*;

public class Graph {
    Vertex[] vertex;
    int[][] edge;
    int max, numberOfVertices;

    public Graph(int n) {
        this.vertex = new Vertex[n];
        this.edge = new int[n][n];
        this.max = n;
        this.numberOfVertices = 0;
    }

    public boolean addVertex(int vertexNumber, Vertex newVertex) {
        if(vertexNumber > max)
            return false;
        vertex[vertexNumber] = newVertex.deepCopy();
        numberOfVertices++;
        return true;
    }

    public Vertex getVertex(int i) {
        return vertex[i];
    }

    public void showVertex(int vertexNumber) {
        System.out.println(vertex[vertexNumber].toString());
    }

    public boolean insertEdge(int fromVertex, int toVertex) {
        if(vertex[fromVertex] == null || vertex[toVertex] == null)
            return false;
        edge[fromVertex][toVertex] = 1;
        return true;
    }

    public void ShowEdges(int vertexNumber) {
        System.out.print("Edges exist between: ");
        for(int i = 0; i < numberOfVertices; i++) {
            if(edge[vertexNumber][i] >= 1)
                System.out.println(vertexNumber + ", " + i);
        }
    }

    public void breadthFirstTraversal(int targetValue) {
        int v = 0;
        int numOfVerticesVisited = 0;
        int shortestPathLength = 0;
        int[] prevVertex = new int[edge.length];
        boolean wasFound = false;
        Queue queue = new LinkedList<Integer>();

        for(int i = 0; i < numberOfVertices; i++) {
            if(vertex[i] != null)
                vertex[i].setVisited(false);
        }
        queue.add(v);
        vertex[v].setVisited(true);
        prevVertex[v] = -1; //set impossible value to designate start of path;

        while(!queue.isEmpty()) {
            v = (Integer)queue.poll();

            if(vertex[v].getValue() == targetValue){
                wasFound = true;
                break;
            }

            for(int i = 0; i < numberOfVertices; i++) {
                if(edge[v][i] == 1 && !vertex[i].checkVisited()) {
                    queue.add(i);
                    vertex[i].setVisited(true);
                    prevVertex[i] = v;
                    numOfVerticesVisited++;
                }
            }
        }

        while(prevVertex[v] != -1) {
            shortestPathLength++;
            v = prevVertex[v];
        }

        System.out.println("Breadth-First Traversal Algorithm" +
                "\nWas successful: " + wasFound +
                "\nLength of the shortest path (edges traversed): " + shortestPathLength +
                "\nTotal number of vertices visited: " + numOfVerticesVisited);

        if(!wasFound)
            for(int i = 0; i < vertex.length; i++)
                if(!vertex[i].checkVisited())
                    System.out.println("Vertex " + i + " was not visited, value: " + vertex[i].getValue());
    }

    public void depthFirstTraversal(int targetValue) {
        int v = 0;
        int numOfVerticesVisited = 0;
        int shortestPathLength = 0;
        int[] prevVertex = new int[edge.length];
        boolean wasFound = false;
        Stack<Integer> stack = new Stack();

        for(int i = 0; i < numberOfVertices; i++) {
            if(vertex[i] != null) {
                vertex[i].setVisited(false);
            }
        }
        stack.push(v);
        vertex[v].setVisited(true);
        prevVertex[v] = -1;

        while(!stack.empty()) {
            v = stack.pop();

            if(vertex[v].getValue() == targetValue) {
                wasFound = true;
                break;
            }

            for(int i = 0; i < numberOfVertices; i++) {
                if(edge[v][i] == 1 && !vertex[i].checkVisited()) {
                    stack.push(i);
                    vertex[i].setVisited(true);
                    prevVertex[i] = v;
                    numOfVerticesVisited++;
                }
            }
        }

        while(prevVertex[v] != -1) {
            shortestPathLength++;
            v = prevVertex[v];
        }

        System.out.println("Depth-First Traversal Algorithm" +
                "\nWas successful: " + wasFound +
                "\nLength of the shortest path (edges traversed): " + shortestPathLength +
                "\nTotal number of vertices visited: " + numOfVerticesVisited);

        if(!wasFound)
                for(int i = 0; i < vertex.length; i++)
                    if(!vertex[i].checkVisited())
                        System.out.println("Vertex " + i + " was not visited, value: " + vertex[i].getValue());
    }

    public void dijkstraAlgorithm(int targetValue) {
        int numOfEdges = edge.length;
        Queue<Edge> pathOptions = new PriorityQueue<>(numOfEdges);
        boolean wasFound = false;
        int curVertex = 0;
        int curWeight = 0;
        int[] prevVertex = new int[edge.length]; //previous vertex along shortest path
        int numOfVerticesVisited = -1; //number of vertices visited during search minus starting one950

        for(int i = 0; i < numberOfVertices; i++) {
            if(vertex[i] != null)
                vertex[i].setVisited(false);
        }

        prevVertex[0] = -1; //set impossible value to designate start of path

        for(int i = 0; i < vertex[curVertex].getEdges().length; i++) {
            for(int j = 0; j < vertex[curVertex].getEdges().length; j++)
                prevVertex[vertex[curVertex].getEdge(j).getTo()] = curVertex;
            pathOptions.add(vertex[curVertex].getEdge(i));
        }

        while(!pathOptions.isEmpty()){
            curVertex = pathOptions.peek().getTo();
            curWeight = pathOptions.poll().getWeight();

            if(vertex[curVertex].checkVisited())
                continue;

            //check if this was the requested vertex
            if(vertex[curVertex].getValue() == targetValue) {
                wasFound = true;
                break;
            }

            for(int i = 0; i < vertex[curVertex].getEdges().length; i++) {
                for (int j = 0; j < vertex[curVertex].getEdges().length; j++) {
                    vertex[curVertex].getEdge(j).setWeight(
                            vertex[curVertex].getEdge(j).getWeight() + curWeight);
                }
                prevVertex[vertex[curVertex].getEdge(i).getTo()] = curVertex;
                pathOptions.add(vertex[curVertex].getEdge(i));
            }

            //mark the currentVertex as visited
            vertex[curVertex].setVisited(true);
            numOfVerticesVisited++;
        }

        System.out.println("Dijkstra's Algorithm" +
                "\nWas successful: " + wasFound +
                "\nLength of the shortest path (sum of path weights): " + curWeight +
                "\nTotal number of vertices visited: " + numOfVerticesVisited);

        if(!wasFound)
            for(int i = 0; i < vertex.length; i++)
                if(!vertex[i].checkVisited())
                    System.out.println("Vertex " + i + " was not visited, value: " + vertex[i].getValue());
    }
}