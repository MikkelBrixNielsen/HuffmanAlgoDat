import java.io.File;

public class Encode {
    public static void main(String[] args) {

        // make frequency table for the file and save it for later
        // convert frequency table first to PQHeap -> huffmanTree -> huffmanTable
        // convert frequency to String[] containing frequency followed by contents of huffmanTable
        // write contents of String[] containing frequency and huffmanTable to output file

        int[] frequencyTable = ByteReader.readBytesAsInt(new File(args[0]));
        String[] huffmanTable = convertHuffmanTreeToTable(huffmanAlg(frequencyTable));

        String[] stringsToWrite = new String[frequencyTable.length + huffmanTable.length];
        for (int i = 0; i < frequencyTable.length; i++)
            stringsToWrite[i] = "" + frequencyTable[i];
        for (int i = 0; i < huffmanTable.length; i++) {
            stringsToWrite[i + frequencyTable.length] = huffmanTable[i];
            System.out.println(huffmanTable[i]);
        }
        ByteWriter.writeStringToOutputFile(stringsToWrite, args[1]);
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
}
