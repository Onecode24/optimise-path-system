import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        // scenario 1
        scenario2();
    }

    public static void scenario1() {
        // scenario 1 Every thing is OK
        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 10);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("A", "D", 2);
        graph.addEdge("B", "C", 1);


        graph.Dijkstra(nodeA.name);
        graph.printPath("D"); // A B
        graph.printPath("C"); // A C D
    }

    public static void scenario2(){
        // scenario 2
        // Le cas ou il y a des travaux sur le chemin entre A et B
        Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        graph.addEdge("A", "C", 10);
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("A", "D", 2);
        graph.addEdge("B", "C", 1);

        graph.edgeDown("A", "B");
        System.out.println(graph.isEdgeDown("A", "B"));
        graph.Dijkstra(nodeA.name);
        graph.printPath("D"); // A C D
        graph.printPath("C"); // A C
    }
}
