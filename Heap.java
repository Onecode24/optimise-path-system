public class Heap {
    Path[] paths;
    int heap_size;
    int numberOfNode;

    public Heap(int numberOfNode){
        this.numberOfNode = numberOfNode;
        heap_size = 0;
        paths = new Path[this.numberOfNode+1];
        paths[0] = new Path("null", (double) Integer.MIN_VALUE);
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
        temp = paths[i];
        paths[i] = paths[j];
        paths[j] = temp;
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
            if(paths[l].distance < paths[i].distance)
                smallest = l;
            else
                smallest = i;
            if(paths[r].distance < paths[smallest].distance)
                smallest = r;
            if(smallest !=i) {
                swap(i, smallest);
                minHeapify(smallest);
            }
        }
    }
    public void insert(Path p) {
        paths[++heap_size] = p;
        int i = heap_size;
        int parent = parent(i);
        while (paths[i].distance < paths[parent].distance) {
            swap(i, parent);
            i = parent;
        }
    }
    public Path extractMin() {
        Path root = paths[1];
        paths[1] = paths[heap_size--];
        minHeapify(1);
        return root;
    }


}
