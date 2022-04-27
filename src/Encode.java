import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

public class Encode {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = HuffmanAlg(ByteReader.readBytesAsInt(new File(args[0])));
        System.out.println(huffmanTree.right().left());

    }


    public static HuffmanTree HuffmanAlg(int[] table) {
        PQHeap tableHeap = tableToMinHeap(table);
        int n = table.length;
        for (int i = 0; i < n - 1; i++) {
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
