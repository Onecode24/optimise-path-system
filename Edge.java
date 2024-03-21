public class Edge {
    public Node destNode;
    public double distance;

    public Boolean state;

    public Edge(Node node, double dist){
        destNode = node;
        distance = dist;
        state = true;
    }
}
