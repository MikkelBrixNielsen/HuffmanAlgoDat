import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {
    public static void main(String[] args) {
        decode(new File(args[0]), new File(args[1]));
    }

    private static void decode(File inputFile, File outputFile) {
        int[] frequencyTable = new int[256];
        int totalFrequency = 0;
        try {
            BitInputStream BIStream = new BitInputStream(new FileInputStream(inputFile));
            FileOutputStream FOStream = new FileOutputStream(outputFile);
            int n = 0;
            while (n < 256) {
                frequencyTable[n] = BIStream.readInt();
                totalFrequency += frequencyTable[n];
                n++;
            }
            HuffmanTree huffmanTree = HuffmanTree.createHuffmanTree(frequencyTable);
            HuffmanTree HTPointer = huffmanTree;
            n = 1;
            while (n < totalFrequency) {
                int bit = BIStream.readBit();
                if (HTPointer.left() == null) {
                    FOStream.write(HTPointer.getBitValue());
                    HTPointer = huffmanTree;
                } else {
                    if (bit == 0)
                        HTPointer = HTPointer.left();
                    else
                        HTPointer = HTPointer.right();
                }
                n++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








}
