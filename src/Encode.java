import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Encode {
    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int[] frequencyTable = ByteReader.createFrequencyTableFrom(inputFile);
        String[] huffmanTable = HuffmanTree.createHuffmanTable(frequencyTable);


        ByteWriter.writeIntToOutputFile(frequencyTable, outputFile); // the ByteWriter method is probably not correct / broken
/*        writeEncodedString(encode(inputFile, huffmanTable),outputFile);*/
    }

    // virker ikke helt fordi der ikke bliver padded med 0'er til sidst i filen.
    public static String encode(File file, String[] huffmanTable) {
        String bitCode = "";
        try {
            BitInputStream BIStream = new BitInputStream(new FileInputStream(file));
            int n = BIStream.readInt();
            while (n != -1) {
                bitCode += huffmanTable[n % 256];
                n = BIStream.readInt();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bitCode;
    }

    private  static void writeEncodedString(String encodedString, File outputFile) {
        int[] bitCode = new int[encodedString.length()];
        for (int i = 0; i < bitCode.length; i++)
            bitCode[i] = Character.getNumericValue(encodedString.charAt(i));
        ByteWriter.writeBitToOutputFile(bitCode, outputFile);
    }
}








