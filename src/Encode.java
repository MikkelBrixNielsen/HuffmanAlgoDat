import java.io.File;

public class Encode {
    public static void main(String[] args) {
        int[] test = ByteReader.readBytesAsInt(new File("C:\\Users\\mikke\\OneDrive\\Dokumenter\\SDU\\DM507 - Algoritmer og datastrukture\\HuffmanAlg\\src\\x21_ScardoviaWiggsiae.dna"));
        goThroughHuffTree(huffmanAlg(test),"");
    }

    public static void goThroughHuffTree(HuffmanTree currentNode, String str) {
        if (currentNode != null) {
            goThroughHuffTree(currentNode.left(), str + "L");
            if (currentNode.left() == null ||currentNode.right() == null)
                System.out.println(currentNode.getBitValue() + ": " + str);
            goThroughHuffTree(currentNode.right(), str + "R");
        }
    }

    public static HuffmanTree huffmanAlg(int[] table) {
        PQHeap tableHeap = tableToMinHeap(table);
        int n = table.length;
        for (int i = 0; i < n; i++) {
            Element e1 = tableHeap.extractMin();
            Element e2 = tableHeap.extractMin();
            tableHeap.insert(new Element((e1.getKey() + e2.getKey()),
                    new HuffmanTree((HuffmanTree) e1.getData(),(HuffmanTree) e2.getData())));
        }
        return (HuffmanTree) tableHeap.extractMin().getData();
    }

    private static PQHeap tableToMinHeap(int[] table) {
        PQHeap tableHeap = new PQHeap();
        for (int i = 0; i < table.length; i++)
            tableHeap.insert(new Element(table[i], new HuffmanTree(i)));
        return tableHeap;
    }
}
