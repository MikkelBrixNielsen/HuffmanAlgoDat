import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;

public class Encode {
    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int[] frequencyTable = createFrequencyTableFrom(inputFile);
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable);
    }

    public static int[] createFrequencyTableFrom(File file) {
        int[] frequencyTable = new int[256];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int n = fileInputStream.read();
            while (n != -1) {
                frequencyTable[n]++;
                n = fileInputStream.read();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return frequencyTable;
    }

    // virker ikke helt fordi der ikke bliver padded med 0'er til sidst i filen.
    public static String encodeFile(File file, String[] huffmanTable) {
        String bitCode = "";
        try {
            FileInputStream FIStream = new FileInputStream(file);
            int n = FIStream.read();
            System.out.println(n != -1 ? "enter" : "");
            while (n != -1) {
                bitCode += huffmanTable[n];
                System.out.println();
                n = FIStream.read();
            }
            System.out.println("exit");
            FIStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bitCode;
    }

    private  static void writeEToFile(int[] frequencyTable, String encodedString, File outputFile) {
        int[] bitCode = new int[encodedString.length()];
        for (int i = 0; i < bitCode.length; i++)
            bitCode[i] = Character.getNumericValue(encodedString.charAt(i));
        try {
            BitOutputStream BIStream = new BitOutputStream(new FileOutputStream(outputFile));
            // writes frequency table to outputFile
            for (int intToWrite : frequencyTable)
                BIStream.writeInt(intToWrite);
            // writes encoded huffman bits to outputFile
            for (int bitsToWrite : bitCode)
                BIStream.writeBit(bitsToWrite);
            BIStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








