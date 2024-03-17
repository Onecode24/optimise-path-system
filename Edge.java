public class Edge {
    public Node destName;
    public double distance;

    public Edge(Node node, double dist){
        destName = node;
        distance = dist;
    }
}
