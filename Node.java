import java.util.LinkedList;
import java.util.List;

public class Node {
    public String name;
    public List<Edge> edgeList;
    public Node previous;
    public Double dist;
    public Boolean state;


    public Node(String nm) {
        name = nm;
        edgeList = new LinkedList<Edge>();
        state = true;
        reset();
    }
    public void reset() {
        dist = (double) Graph.INFINITY;
        previous = null;
    }

}
