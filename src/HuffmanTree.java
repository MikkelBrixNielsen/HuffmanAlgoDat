//Mikkel Brix Nielsen (mikke21)
//Nicolai Larsen (dalar21)
//Steffen Bach (stbac21)

public class HuffmanTree {
    private final int bitValue;
    private final HuffmanTree left;
    private final HuffmanTree right;

    /**
     * Constructor for creating a HuffmanTree representing a combination of different "leafs"
     * which are also of the HuffmanTree type
     * @param left the left subtree HuffmanTree
     * @param right the right subtree of type HuffmanTree
     */
    public HuffmanTree(HuffmanTree left, HuffmanTree right){
        this.bitValue = -1;
        this.right = right;
        this.left = left;
    }
    /**
     * Constructor for creating a "leaf"
     * @param bitValue the path to this "leaf" represented by bits
     */
    public HuffmanTree(int bitValue){
        this.bitValue = bitValue;
        left = null;
        right = null;
    }

    /**
     * Returns a string array containing the Huffman codes in accordance the
     * frequencies found in the int array given as argument
     * @param frequencyTable an int array containing frequencies
     * @return a string array containing the Huffman codes in accordance the
     * frequencies found in frequencyTable
     */
    public static String[] createHuffmanTable(int[] frequencyTable) {
        return convertHuffmanTreeToTable(createHuffmanTree(frequencyTable));
    }

    /**
     * Returns a string array containing the bitcode corresponding to the
     * integer you would get from calling FileInputStream.read() based on
     * the frequency of which that integer occurs in a file
     * @param treeRoot The beginning of a HuffmanTree
     * @return a string array containing the bit codes for the HuffmanTree given as input
     */
    public static String[] convertHuffmanTreeToTable(HuffmanTree treeRoot) {
        String bitCode = "";
        String[] huffmanTable = new String[256];
        goThroughHuffmanTree(treeRoot, huffmanTable, bitCode);
        return huffmanTable;
    }

    /**
     * Returns a HuffmanTree created from the int array received as argument
     * @param frequencyTable an int array containing frequencies
     * @return a HuffmanTree created from the int array received as argument
     */
    public static HuffmanTree createHuffmanTree(int[] frequencyTable) {
        // Converts frequency table from int array to PQHeap containing objects of the Element type
        PQHeap frequencyTableMinHeap = frequencyTableToMinHeap(frequencyTable);
        // Uses the Huffman algorithm to combine the least frequent elements from frequencyTableMinHeap
        // to a new Element containing the resulting huffman tree from combining those elements
        int n = frequencyTable.length;
        for (int i = 0; i < n; i++) {
            Element e1 = frequencyTableMinHeap.extractMin();
            Element e2 = frequencyTableMinHeap.extractMin();
            frequencyTableMinHeap.insert(new Element((e1.getKey() + e2.getKey()), new HuffmanTree(e1.getData(), e2.getData())));
        }
        return frequencyTableMinHeap.extractMin().getData();
    }

    /*
     * Auxiliary method that recursively visits all elements contained in the HuffmanTree and its subtrees
     * and records the path to each element
     */
    private static void goThroughHuffmanTree(HuffmanTree currentNode, String[] table, String bitCode) {
        if (currentNode != null) {
            goThroughHuffmanTree(currentNode.left(), table, bitCode + "0");
            if (currentNode.left() == null && currentNode.right() == null)
                table[currentNode.getBitValue()] = bitCode;
            goThroughHuffmanTree(currentNode.right(), table, bitCode + "1");
        }
    }

    /*
     * Auxiliary method that takes an int array and converts it to a PQHeap
     */
    private static PQHeap frequencyTableToMinHeap(int[] table) {
        PQHeap tableHeap = new PQHeap();
        for (int i = 0; i < table.length; i++)
            tableHeap.insert(new Element(table[i], new HuffmanTree(i)));
        return tableHeap;
    }

    /**
     * Returns the int stored in bitValue
     * @return the in stored in bitValue
     */
    public int getBitValue() {
        return bitValue;
    }

    /**
     * Returns the HuffmanTree contained in the left child of this HuffmanTree
     * @return the HuffmanTree contained in the left child of this HuffmanTree
     */
    public HuffmanTree left() {
        return left;
    }

    /**
     * Returns the HuffmanTree contained in the right child of this HuffmanTree
     * @return the HuffmanTree contained in the right child of this HuffmanTree
     */
    public HuffmanTree right(){
        return right;
    }

}
