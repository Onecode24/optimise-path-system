import java.util.*;
import java.lang.*;
import java.io.*;

class Pair {
    String str1;
    String str2;

    public Pair(String s1, String s2) {
        str1 = s1;
        str2 = s2;
    }
    public String key() {
        return str1;
    }
    public String value() {
        return str2;
    }
}

public class Graph {
    public static final int INFINITY = Integer.MAX_VALUE;
    private Map<String, Node> nodeMap = new HashMap<String, Node>();
    private LinkedList<Pair> edgeList = new LinkedList<Pair>();

    private Node getVertex(String vertexName) {
        Node node = nodeMap.get(vertexName);
        if (node == null) {
            node = new Node(vertexName);
            nodeMap.put(vertexName, node);
        }
        return node;
    }

    public void addEdge(String sourceName, String destinationName, double distance) {
        Node sourceNode = getVertex(sourceName);
        Node destNode = getVertex(destinationName);

        Iterator<Edge> listIterator = sourceNode.adj.listIterator();
        int i = 0;
        int flag = 0;
        while (listIterator.hasNext()) {
            Edge edge = listIterator.next();
            if (destNode.name.equals(edge.destName.name)) {
                sourceNode.adj.get(i).distance = distance;
                flag = 1;
            }
            i++;
        }
        if (flag == 0)
            sourceNode.adj.add(new Edge(destNode, distance));
    }


    private void print() {
        TreeSet<String> treeSet = new TreeSet<String>();
        System.out.println();
        for (String key : nodeMap.keySet()) {
            treeSet.add(key);
        }
        Iterator<String> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            String adjacentNode = iterator.next();
            System.out.print(adjacentNode);
            System.out.println();
            TreeSet<String> adjacent = new TreeSet<String>();
            HashMap<String, Double> hashMap = new HashMap<String, Double>();
            for (Edge key : nodeMap.get(adjacentNode).adj) {
                adjacent.add(key.destName.name);
                hashMap.put(key.destName.name, key.distance);
            }
            Iterator<String> adjacentIterator = adjacent.iterator();
            while (adjacentIterator.hasNext()) {
                String node = adjacentIterator.next();
                System.out.print("  " + node + " " + hashMap.get(node));
            }
        }
    }

    private void clearAll( )
    {
        for( Node node : nodeMap.values( ) )
            node.reset();
    }

    public void dijkstras(String startNode) {
        clearAll();
        Heap minPriorityQueue = new Heap(nodeMap.size());
        Node initialNode = nodeMap.get(startNode);
        if (initialNode == null){
            System.out.println("Initial vertex is null");
            return;
        }
        initialNode.dist = 0.0;
        minPriorityQueue.insert(new Path(initialNode.name, initialNode.dist));
        while (minPriorityQueue.size() != 0) {
            Node v = getVertex(minPriorityQueue.extractMin().pathname);
            for (Edge edge : v.adj) {
                Node w = edge.destName;
                double weight = edge.distance;
                if (weight < 0)
                    throw new RuntimeException("Graph has negative edge");
                if (w.dist > v.dist + weight) {
                    w.dist = v.dist + weight;
                    w.prev = v;
                    minPriorityQueue.insert(new Path(w.name, w.dist));
                }
            }
        }
    }

    private void printPath(Node dest) {
        Node previous = dest.prev;
        String name = dest.name;
        if (previous != null) {
            printPath(previous);
            System.out.print(" ");
        }
        System.out.print(name);
    }


    public void printPath(String destinationName) {
        Node node = nodeMap.get(destinationName);
        if (node == null){
            System.out.println("Destination vertex not found");
            return;
        }
        else if (node.dist == INFINITY)
            System.out.println(destinationName + " is unreachable");
        else {
            System.out.println();
            printPath(node);
            System.out.printf(" %.2f", node.dist);
            System.out.println();
        }
    }


}

