import java.util.LinkedList;
import java.util.List;

public class Node {
    public String name;
    public List<Edge> adj;
    public Node prev;
    public Double dist;


    public Node(String nm) {
        name = nm;
        adj = new LinkedList<Edge>();
        reset();
    }
    public void reset() {
        dist = (double) Graph.INFINITY;
        prev = null;
    }

}
