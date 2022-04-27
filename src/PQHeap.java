/*
Made by:
- Steffen Bach (stbac21)
- Danny Nicolai Larsen (dalar21)
- Mikkel Brix Nielsen (mikke21)
 */



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PQHeap implements PQ {

    private List<Element> array;

    /**
     * constructor for the PQHeap
     */
    public PQHeap() {
        array = new ArrayList<>();
    }

    /**
     * find the parent to a given child be it left or right
     * @param child representing the index of the node which is a left child to some parent
     * @return parent to a given child
     */
    public int parent(int child) {
        return (child - 1) / 2;
    }

    /**
     * Finds the left child to a parent
     * @param parent representing the index of the node which is a parent to some child
     * @return the index for the left child to a given parent
     */
    public int left(int parent) {
        return parent * 2 + 1;
    }

    /**
     * Finds the right child to a parent
     * @param parent representing the index of the node which is a right child to some parent
     * @return the index for the right child to a given parent
     */
    public int right(int parent) {
        return parent * 2 + 2;
    }

    /**
     * Returns the smallest element in the PQHeap
     * @return the smallest element in the PQHeap
     */
    @Override
    public Element extractMin() {
        Element min = array.get(0);
        if (array.size() > 1) {
            array.set(0, array.remove(array.size() - 1));
            MinHeapify(array, 0);
        }
        return min;
    }

    /**
     * Reorders the PQHeap to follow the minHeap structure
     * @param a the list containing the elements
     * @param i the element to be correctly placed in the PQHeap
     */
    private void MinHeapify(List<Element> a, int i) {
        int l = left(i);
        int r = right(i);
        int smallest;
        if (l < a.size() && a.get(l).getKey() < a.get(i).getKey())
            smallest = l;
        else smallest = i;
        if (r < a.size() && a.get(r).getKey() < a.get(smallest).getKey())
            smallest = r;
        if (smallest != i) {
            Collections.swap(a, i, smallest);
            MinHeapify(a, smallest);
        }
    }

    /**
     * inserts an element into the PQHeap while maintaining minHeap order
     * @param e an element
     */
    @Override
    public void insert(Element e) {
        int i = array.size();
        array.add(e);
        while (i > 0 && array.get(parent(i)).getKey() > array.get(i).getKey()) {
            Collections.swap(array, i, parent(i));
            i = parent(i);
        }
    }
}
