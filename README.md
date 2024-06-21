
# Optimise Path System

## Description
This project is a simple path optimisation system that takes a node and each edge between nodes and find the shotest path between the start node and the end node. The project is implemented in Java and uses the Dijkstra algorithm to find the shortest path.

We can simulate a case when one of the Edge is blocked (like traffic accident 😅) and the system will find the shortest path between the start node and the end node.

## How to run with IntelliJ IDEA
1. Clone the project
2. Open the project with IntelliJ IDEA
3. Run the project by clicking on the green arrow in the top right corner

## How to run with Command Line
1. Clone the project
2. Open the terminal
3. Go to the project directory
4. Run the following command:
```bash
javac Main.java
java Main
```

## Example
Go to main file and update the following code:
```java
Graph graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        graph.addEdge("A", "C", 10); // link from A to C with his distance or time (10)
        graph.addEdge("C", "B", 5);
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 3);
        graph.addEdge("A", "D", 2);
        graph.addEdge("D", "B", 3);
        graph.addEdge("B", "C", 1);

        graph.edgeDown("A", "B"); // simulate a case when the edge between A and B is blocked 
        graph.Dijkstra(nodeA.name);
        graph.printPath("B"); // print the shortest path between A and B
        graph.printPath("C"); // print the shortest path between A and C
```

Enjoy 🎉 and let me know if you have any questions or suggestions. 😊