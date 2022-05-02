import java.io.*;

public class Encode {
    public static void main(String[] args) {
        EncodeAndWriteToFile(new File(args[0]), new File(args[1]));
    }

    public static int[] createFrequencyTableFrom(File file) {
        int[] frequencyTable = new int[256];
        try {
            FileInputStream FIStream = new FileInputStream(file);
            int n = FIStream.read();
            while (n != -1) {
                frequencyTable[n]++;
                n = FIStream.read();
            }
            FIStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return frequencyTable;
    }

    private static void EncodeAndWriteToFile(File inputFile, File outputFile) {
        int[]  frequencyTable = createFrequencyTableFrom(inputFile);
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable);
        try {
            BitOutputStream BOStream = new BitOutputStream(new FileOutputStream(outputFile));
            FileInputStream FIStream = new FileInputStream(inputFile);
            // writes the frequency from frequencyTable to the outputFile
            for (int intToWrite : frequencyTable)
                BOStream.writeInt(intToWrite);
            // Reads ints from the input file and gets the corresponding bitCode
            // from huffmanTable and writes it to the output file
            int n = FIStream.read();
            while (n != -1) {
                for (int i = 0; i < huffmanTable[n].length(); i++)
                    BOStream.writeBit(Character.getNumericValue(huffmanTable[n].charAt(i)));
                n = FIStream.read();
            }
            BOStream.close(); // closes the BIStream and pads with zeroes if need be
            FIStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








