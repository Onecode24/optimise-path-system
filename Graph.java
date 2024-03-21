import java.util.*;
import java.lang.*;

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

    private Node getNode(String nodeName) {
        Node node = nodeMap.get(nodeName);
        if (node == null) {
            node = new Node(nodeName);
            nodeMap.put(nodeName, node);
        }
        return node;
    }

    public void addEdge(String sourceName, String destinationName, double distance) {
        Node sourceNode = getNode(sourceName);
        Node destNode = getNode(destinationName);

        Iterator<Edge> listIterator = sourceNode.edgeList.listIterator();
        int i = 0;
        int flag = 0;
        while (listIterator.hasNext()) {
            Edge edge = listIterator.next();
            if (destNode.name.equals(edge.destNode.name)) {
                sourceNode.edgeList.get(i).distance = distance;
                flag = 1;
            }
            i++;
        }
        if (flag == 0)
            sourceNode.edgeList.add(new Edge(destNode, distance));
    }


    public Boolean isEdgeDown(String sourceName, String destinationName) {
        // check if the edge is down ? if down return true else false

        Node sourceNode = nodeMap.get(sourceName);
        Node destNode = nodeMap.get(destinationName);
        if (sourceNode == null || destNode == null) {
            return true;
        }
        for (Edge edge : sourceNode.edgeList) {
            if (edge.destNode.name.equals(destNode.name)) {
                if (edge.state == false)
                    return true;
                else
                    return false;
            }
        }
        return true;
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
            for (Edge key : nodeMap.get(adjacentNode).edgeList) {
                adjacent.add(key.destNode.name);
                hashMap.put(key.destNode.name, key.distance);
            }
            Iterator<String> adjacentIterator = adjacent.iterator();
            while (adjacentIterator.hasNext()) {
                String node = adjacentIterator.next();
                System.out.print("  " + node + " " + hashMap.get(node));
                if (isEdgeDown(adjacentNode, node))
                    System.out.print(" down");
                System.out.println();

            }
        }
    }

    private void clearAll( )
    {
        for( Node node : nodeMap.values( ) )
            node.reset();
    }

    public void Dijkstra(String startNode) {
        clearAll();
        Heap minPriorityQueue = new Heap(nodeMap.size());
        Node initialNode = nodeMap.get(startNode);
        if (initialNode == null){
            System.out.println("Initial vertex is null");
            return;
        }
        if (initialNode.edgeList.size() == 0){
            System.out.println("Initial vertex has no edges");
            return;
        }
        if (initialNode.state == false){
            System.out.println("Initial Node is down");
            return;
        }
        initialNode.dist = 0.0;
        minPriorityQueue.insert(new Path(initialNode.name, initialNode.dist));
        while (minPriorityQueue.size() != 0) {
            Node node = getNode(minPriorityQueue.extractMin().pathname);
            for (Edge edge : node.edgeList) {
                Node dest = edge.destNode;
                if (!dest.state) //check for disabled vertices
                    continue;
                if (isEdgeDown(node.name, dest.name)) //check for disabled edges
                    continue;
                double weight = edge.distance;
                if (weight < 0)
                    throw new RuntimeException("Graph has negative edge");
                if (dest.dist > node.dist + weight) {
                    dest.dist = node.dist + weight;
                    dest.previous = node;
                    minPriorityQueue.insert(new Path(dest.name, dest.dist));
                }
            }
        }
    }

    private void printPath(Node dest) {
        Node previous = dest.previous;
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

    public void edgeDown(String source, String destination) {
        /*Iterator<Pair> iterator = edgeList.iterator();
        int flag = 1;
        if (edgeList.size() != 0) {
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.key() == source && pair.value() == destination) {
                    flag = 0;
                    break;
                }
            }
        }
        if (flag == 1)
            edgeList.add(new Pair(source, destination)); */
        // get Edge from source to destination and set state to false
        Iterator<Edge> listIterator = nodeMap.get(source).edgeList.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            Edge edge = listIterator.next();
            if (destination.equals(edge.destNode.name)) {
                nodeMap.get(source).edgeList.get(i).state = false;
                break;
            }
            i++;
        }

    }

    public void edgeUp(String source, String destination) {
        /* Iterator<Pair> iterator = edgeList.iterator();
        int flag = 0;
        Pair pair = null;
        if (edgeList.size() != 0) {
            while (iterator.hasNext()) {
                pair = iterator.next();
                if (source.equals(pair.key()) && destination.equals(pair.value())) {
                    flag = 1;
                    break;
                }
            }
        }
        if (flag == 1)
            edgeList.remove(pair); */
        // get Edge from source to destination and set state to true
        Iterator<Edge> listIterator = nodeMap.get(source).edgeList.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            Edge edge = listIterator.next();
            if (destination.equals(edge.destNode.name)) {
                nodeMap.get(source).edgeList.get(i).state = true;
                break;
            }
            i++;
        }
    }

    public void deleteEdge(String sourceName, String destinationName) {
        Node sourceNode = getNode(sourceName);
        Node destNode = getNode(destinationName);
        int flag = 0;
        Iterator<Edge> listIterator = sourceNode.edgeList.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            Edge edge = listIterator.next();
            if (destNode.name.equals(edge.destNode.name)) {
                flag = 1;
                break;
            }
            i++;
        }
        if (flag == 1)
            sourceNode.edgeList.remove(i);
    }

}

