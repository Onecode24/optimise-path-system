public class Heap {
    Path[] path;
    int heap_size;
    int numberOfNode;

    public Heap(int numberOfNode){
        this.numberOfNode = numberOfNode;
        heap_size = 0;
        path = new Path[this.numberOfNode+1];
        path[0] = new Path("null", (double) Integer.MIN_VALUE);
    }

    public  int size(){
        return heap_size;
    }

    private  int parent( int i){
        return  i >> 1;
    }

    public int left(int i){
        return i << 1;
    }

    public int right(int i) {
        return (2*i + 1);
    }

    private void swap(int i, int j) {
        Path temp;
        temp = path[i];
        path[i] = path[j];
        path[j] = temp;
    }

    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        boolean flag = false;

        //check if i is leaf node
        if (i - 1 >= (heap_size / 2) && i <= heap_size) {
            flag = true;
        } else {
            flag = false;
        }
        if (!flag && heap_size > 0) {
            if(path[l].distance < path[i].distance)
                smallest = l;
            else
                smallest = i;
            if(path[r].distance < path[smallest].distance)
                smallest = r;
            if(smallest !=i) {
                swap(i, smallest);
                minHeapify(smallest);
            }
        }
    }
    public void insert(Path p) {
        path[++heap_size] = p;
        int i = heap_size;
        int parent = parent(i);
        while (path[i].distance < path[parent].distance) {
            swap(i, parent);
            i = parent;
        }
    }
    public Path extractMin() {
        Path root = path[1];
        path[1] = path[heap_size--];
        minHeapify(1);
        return root;
    }


}
