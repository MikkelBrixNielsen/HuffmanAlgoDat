//Mikkel Brix Nielsen (mikke21)
//Nicolai Larsen (dalar21)
//Steffen Bach (stbac21)

import java.util.ArrayList;

public class DictBinTree {
    private BinNode root;

    /**
     * Constructor for an unbalanced search tree
     */
    public DictBinTree() {
        root = null;
    } 

    /**
     * Searches for input value, k, in the tree that the method is called upon
     * @param k - which is the value that the method searches for in this tree
     * @return Boolean value that represents whether k is contained within this tree
     */
    public boolean search(int k) {
        BinNode x = this.root;
        while ( x != null && k != x.key ) {
            if ( k < x.key )
                x = x.left;
            else
                x = x.right;
        }
        return (k == x.key);
    }

    /**
     * Inserts the value k into this tree in its rightful place
     * @param k - which is to be inserted into the tree
     */
    public void insert( int k ) {
        BinNode z = new BinNode(k);
        BinNode y = null;
        BinNode x = this.root;
        while ( x != null ) {
            y = x;
            if ( z.key < x.key )
                x = x.left;
            else
                x = x.right;
        }
        if ( y == null )
            this.root = z;
        else if ( z.key < y.key )
            y.left = z;
        else
            y.right = z;
    }

    /**
     * Returns a sorted arraylist containing the key of each node from tree that the method is called upon
     * @return Arraylist containing keys
     */ 
    public ArrayList<Integer> orderedTraversal() {
        return orderedTraversal(this.root, "");
    }

    /*
     * Traverses this tree by in-order-traversal and returns a sorted arraylist
     */
    private ArrayList<Integer> orderedTraversal(BinNode x, String path) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        if ( x != null ) {
            list.addAll(orderedTraversal(x.left, path + "L"));
            list.add(x.key);
            System.out.println(x.key + ": "  + path);
            list.addAll(orderedTraversal(x.right, path + "R"));
        }
        return list;
    }

    /*
     * Inner class for nodes
     */
    private class BinNode {
        private int key;
        private BinNode left;
        private BinNode right;

        public BinNode(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }
}
