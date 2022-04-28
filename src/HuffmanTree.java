public class HuffmanTree {
    private int bitValue;
    private HuffmanTree left = null;
    private HuffmanTree right = null;

    public HuffmanTree(int bitValue){
        this.bitValue = bitValue;
    }

    public HuffmanTree(HuffmanTree left, HuffmanTree right){
        this.bitValue = -1;
        this.right = right;
        this.left = left;
    }


    public static String[] createHuffmanTable(int[] frequencyTable) {
        return convertHuffmanTreeToTable(huffmanAlg(frequencyTable));
    }

    private static String[] convertHuffmanTreeToTable(HuffmanTree treeRoot) {
        String bitCode = "";
        String[] huffmanTable = new String[256];
        goThroughHuffmanTree(treeRoot, huffmanTable, bitCode);
        return huffmanTable;
    }

    private static void goThroughHuffmanTree(HuffmanTree currentNode, String[] table, String bitCode) {
        if (currentNode != null) {
            goThroughHuffmanTree(currentNode.left(), table, bitCode + "0");
            if (currentNode.left() == null && currentNode.right() == null) {
                table[currentNode.getBitValue()] = bitCode;
            }
            goThroughHuffmanTree(currentNode.right(), table, bitCode + "1");
        }
    }

    private static HuffmanTree huffmanAlg(int[] table) {
        PQHeap tableMinHeap = tableToMinHeap(table);
        int n = table.length;
        for (int i = 0; i < n; i++) {
            Element e1 = tableMinHeap.extractMin();
            Element e2 = tableMinHeap.extractMin();
            tableMinHeap.insert(new Element((e1.getKey() + e2.getKey()), new HuffmanTree(e1.getData(), e2.getData())));
        }
        return tableMinHeap.extractMin().getData();
    }

    private static PQHeap tableToMinHeap(int[] table) {
        PQHeap tableHeap = new PQHeap();
        for (int i = 0; i < table.length; i++)
            tableHeap.insert(new Element(table[i], new HuffmanTree(i)));
        return tableHeap;
    }

















    public int getBitValue() {
        return bitValue;
    }

    public HuffmanTree left() {
        return left;
    }

    public HuffmanTree right(){
        return right;
    }

    public void setBitValue(int bitValue) {
        this.bitValue = bitValue;
    }

    public void setLeft(HuffmanTree left) {
        this.left = left;
    }

    public void setRight(HuffmanTree right) {
        this.right = right;
    }


}
