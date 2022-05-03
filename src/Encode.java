import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encode {
    public static void main(String[] args) {
        EncodeAndWriteToFile(new File(args[0]), new File(args[1]));
    }

    /**
     * Takes a given input file and encodes its contents and writes it to a given output file
     * @param inputFile the file to be encoded
     * @param outputFile the file which to wirte the encoded data
     */
    public static void EncodeAndWriteToFile(File inputFile, File outputFile) {
        int[] frequencyTable = createFrequencyTableFrom(inputFile); // Creates a frequency table
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable); // Creates a HuffmanTree based on frequencyTable
        try {
            BitOutputStream BOStream = new BitOutputStream(new FileOutputStream(outputFile));
            FileInputStream FIStream = new FileInputStream(inputFile);
            // writes the frequencies from frequencyTable to the outputFile
            for (int intToWrite : frequencyTable)
                BOStream.writeInt(intToWrite);
            // Reads input data as ints from the input file and gets the corresponding bitCode
            // from huffmanTable and writes it to the output file
            int n = FIStream.read();
            while (n != -1) {
                for (int i = 0; i < huffmanTable[n].length(); i++)
                    BOStream.writeBit(Character.getNumericValue(huffmanTable[n].charAt(i)));
                n = FIStream.read();
            }
            BOStream.close(); // closes the BitInputStream when done
            FIStream.close(); // closes the FileInputStream when done
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Auxiliary method for creating a frequency table from a given file
     */
    private static int[] createFrequencyTableFrom(File file) {
        int[] frequencyTable = new int[256];
        // reads a whole file until the end using FIStream.read() which returns
        // an int ranging from -1 to 255, -1 meaning no more input, and counts and
        // records the occurrences of those integers into frequencyTable
        try {
            FileInputStream FIStream = new FileInputStream(file);
            int n = FIStream.read();
            while (n != -1) {
                frequencyTable[n]++; // increases the occurrences read int
                n = FIStream.read(); // reads the next input data and updates n
            }
            FIStream.close(); // closes the stream when done
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return frequencyTable;
    }
}








